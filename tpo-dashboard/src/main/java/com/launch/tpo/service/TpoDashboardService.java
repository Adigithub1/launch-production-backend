package com.launch.tpo.service;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.launch.tpo.dto.DashboardResponse;

@Service
public class TpoDashboardService {
	private final NamedParameterJdbcTemplate jdbc;

	public TpoDashboardService(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	public DashboardResponse dashboard(long collegeId, Long requestedCycleId) {
		var cycle = cycle(collegeId, requestedCycleId);
		var p = new MapSqlParameterSource().addValue("collegeId", collegeId).addValue("cycleId", cycle.id());
		return new DashboardResponse(cycle, kpis(p), funnel(p), readiness(p), faculties(p), drives(p), atRisk(p),
				actions(p));
	}

	private DashboardResponse.Cycle cycle(long collegeId, Long requestedCycleId) {

	    String sql;

	    MapSqlParameterSource params = new MapSqlParameterSource()
	            .addValue("collegeId", collegeId);

	    if (requestedCycleId == null) {

	        sql = """
	                SELECT id, name
	                FROM placement_cycles
	                WHERE college_id = :collegeId
	                ORDER BY active DESC, start_date DESC
	                LIMIT 1
	                """;

	    } else {

	        sql = """
	                SELECT id, name
	                FROM placement_cycles
	                WHERE college_id = :collegeId
	                  AND id = :cycleId
	                """;

	        params.addValue("cycleId", requestedCycleId);
	    }

	    List<DashboardResponse.Cycle> cycles = jdbc.query(
	            sql,
	            params,
	            (rs, rowNum) -> new DashboardResponse.Cycle(
	                    rs.getLong("id"),
	                    rs.getString("name")
	            )
	    );

	    if (cycles.isEmpty()) {

	        if (requestedCycleId != null) {
	            throw new IllegalArgumentException(
	                    "Placement cycle " + requestedCycleId
	                            + " not found for college " + collegeId
	            );
	        }

	        throw new IllegalStateException(
	                "No placement cycle configured for college " + collegeId
	        );
	    }

	    return cycles.get(0);
	}

	private DashboardResponse.Kpis kpis(MapSqlParameterSource p) {
		String sql = """
				select
				 (select count(*) from student_placement_profiles s where s.college_id=:collegeId and s.cycle_id=:cycleId) registered,
				 (select count(*) from student_placement_profiles s where s.college_id=:collegeId and s.cycle_id=:cycleId and s.verification_status='VERIFIED') eligible,
				 (select count(*) from student_placement_profiles s where s.college_id=:collegeId and s.cycle_id=:cycleId and s.readiness_score>=75) ready,
				 (select count(distinct a.student_profile_id) from applications a join opportunities o on o.id=a.opportunity_id where o.college_id=:collegeId and o.cycle_id=:cycleId and a.stage in ('OFFERED','JOINED')) placed,
				 (select count(*) from offers f join applications a on a.id=f.application_id join opportunities o on o.id=a.opportunity_id where o.college_id=:collegeId and o.cycle_id=:cycleId and f.status not in ('REVOKED','REJECTED')) offers,
				 (select coalesce(round(avg(f.ctc_lpa),2),0) from offers f join applications a on a.id=f.application_id join opportunities o on o.id=a.opportunity_id where o.college_id=:collegeId and o.cycle_id=:cycleId and f.status not in ('REVOKED','REJECTED')) avg_ctc,
				 (select coalesce(max(f.ctc_lpa),0) from offers f join applications a on a.id=f.application_id join opportunities o on o.id=a.opportunity_id where o.college_id=:collegeId and o.cycle_id=:cycleId and f.status not in ('REVOKED','REJECTED')) max_ctc,
				 (select count(*) from placement_drives d where d.college_id=:collegeId and d.cycle_id=:cycleId and d.status in ('SCHEDULED','ACTIVE')) active_drives
				""";
		return jdbc.queryForObject(sql, p,
				(rs, n) -> new DashboardResponse.Kpis(rs.getLong("registered"), rs.getLong("eligible"),
						rs.getLong("ready"), rs.getLong("placed"), rs.getLong("offers"), rs.getBigDecimal("avg_ctc"),
						rs.getBigDecimal("max_ctc"), rs.getLong("active_drives")));
	}

	private List<DashboardResponse.FunnelItem> funnel(MapSqlParameterSource p) {
		String sql = """
				with counts as (
				 select 'REGISTERED' stage, count(*) n from student_placement_profiles where college_id=:collegeId and cycle_id=:cycleId
				 union all select 'ELIGIBLE', count(*) from student_placement_profiles where college_id=:collegeId and cycle_id=:cycleId and verification_status='VERIFIED'
				 union all select 'APPLIED', count(distinct a.student_profile_id) from applications a join opportunities o on o.id=a.opportunity_id where o.college_id=:collegeId and o.cycle_id=:cycleId
				 union all select 'SHORTLISTED', count(distinct a.student_profile_id) from applications a join opportunities o on o.id=a.opportunity_id where o.college_id=:collegeId and o.cycle_id=:cycleId and a.stage in ('SHORTLISTED','ASSESSMENT','INTERVIEW','OFFERED','JOINED')
				 union all select 'INTERVIEW', count(distinct a.student_profile_id) from applications a join opportunities o on o.id=a.opportunity_id where o.college_id=:collegeId and o.cycle_id=:cycleId and a.stage in ('INTERVIEW','OFFERED','JOINED')
				 union all select 'OFFERED', count(distinct a.student_profile_id) from applications a join opportunities o on o.id=a.opportunity_id where o.college_id=:collegeId and o.cycle_id=:cycleId and a.stage in ('OFFERED','JOINED')
				 union all select 'JOINED', count(distinct a.student_profile_id) from applications a join opportunities o on o.id=a.opportunity_id where o.college_id=:collegeId and o.cycle_id=:cycleId and a.stage='JOINED')
				select stage,n from counts
				""";
		return jdbc.query(sql, p, (rs, n) -> new DashboardResponse.FunnelItem(rs.getString("stage"), rs.getLong("n")));
	}

	private DashboardResponse.Readiness readiness(MapSqlParameterSource p) {
		String sql = """
				select coalesce(round(avg(readiness_score)),0) avg_score,
				 count(*) filter(where readiness_score>=75) ready,
				 count(*) filter(where readiness_score between 60 and 74) almost_ready,
				 count(*) filter(where readiness_score<60) needs_intervention,
				 count(*) filter(where profile_score<70) incomplete_profile
				from student_placement_profiles where college_id=:collegeId and cycle_id=:cycleId
				""";
		return jdbc.queryForObject(sql, p,
				(rs, n) -> new DashboardResponse.Readiness(rs.getInt("avg_score"), rs.getLong("ready"),
						rs.getLong("almost_ready"), rs.getLong("needs_intervention"),
						rs.getLong("incomplete_profile")));
	}

	private List<DashboardResponse.FacultyPlacement> faculties(MapSqlParameterSource p) {
		String sql = """
				select s.faculty, count(*) filter(where s.verification_status='VERIFIED') eligible,
				 count(distinct s.id) filter(where a.stage in ('OFFERED','JOINED')) placed
				from student_placement_profiles s left join applications a on a.student_profile_id=s.id
				where s.college_id=:collegeId and s.cycle_id=:cycleId group by s.faculty order by placed desc, s.faculty
				""";
		return jdbc.query(sql, p, (rs, n) -> {
			long e = rs.getLong("eligible"), placed = rs.getLong("placed");
			return new DashboardResponse.FacultyPlacement(rs.getString("faculty"), e, placed,
					e == 0 ? 0 : (int) Math.round(placed * 100.0 / e));
		});
	}

	private List<DashboardResponse.UpcomingDrive> drives(MapSqlParameterSource p) {
		String sql = """
				select d.id,c.name company,o.title role,d.drive_date,d.current_stage,
				 (select count(*) from student_placement_profiles s where s.college_id=d.college_id and s.cycle_id=d.cycle_id and s.verification_status='VERIFIED' and (o.min_cgpa is null or s.cgpa>=o.min_cgpa) and (o.max_backlogs is null or s.active_backlogs<=o.max_backlogs)) eligible,
				 count(a.id) applied
				from placement_drives d join opportunities o on o.id=d.opportunity_id join companies c on c.id=o.company_id left join applications a on a.drive_id=d.id
				where d.college_id=:collegeId and d.cycle_id=:cycleId and d.drive_date>=now() and d.status in ('SCHEDULED','ACTIVE')
				group by d.id,c.name,o.title,d.drive_date,d.current_stage,o.min_cgpa,o.max_backlogs order by d.drive_date limit 5
				""";
		return jdbc.query(sql, p,
				(rs, n) -> new DashboardResponse.UpcomingDrive(rs.getLong("id"), rs.getString("company"),
						rs.getString("role"), rs.getObject("drive_date", java.time.OffsetDateTime.class),
						rs.getString("current_stage"), rs.getLong("eligible"), rs.getLong("applied")));
	}

	private List<DashboardResponse.AtRiskStudent> atRisk(MapSqlParameterSource p) {
		String sql = """
				select id,full_name,course,branch,readiness_score,
				 case least(profile_score,aptitude_score,technical_score,project_score,resume_score,interview_score)
				  when profile_score then 'PROFILE' when aptitude_score then 'APTITUDE' when technical_score then 'TECHNICAL'
				  when project_score then 'PROJECTS' when resume_score then 'RESUME' else 'INTERVIEW' end primary_gap
				from student_placement_profiles where college_id=:collegeId and cycle_id=:cycleId and readiness_score<60
				order by readiness_score asc,full_name limit 8
				""";
		return jdbc.query(sql, p,
				(rs, n) -> new DashboardResponse.AtRiskStudent(rs.getLong("id"), rs.getString("full_name"),
						rs.getString("course"), rs.getString("branch"), rs.getInt("readiness_score"),
						rs.getString("primary_gap")));
	}

	private List<DashboardResponse.ActionItem> actions(MapSqlParameterSource p) {
		String sql = "select id,severity,title,description,due_at from tpo_action_items where college_id=:collegeId and cycle_id=:cycleId and status='OPEN' order by case severity when 'CRITICAL' then 1 when 'HIGH' then 2 when 'MEDIUM' then 3 else 4 end,due_at nulls last limit 8";
		return jdbc.query(sql, p,
				(rs, n) -> new DashboardResponse.ActionItem(rs.getLong("id"), rs.getString("severity"),
						rs.getString("title"), rs.getString("description"),
						rs.getObject("due_at", java.time.OffsetDateTime.class)));
	}
}
