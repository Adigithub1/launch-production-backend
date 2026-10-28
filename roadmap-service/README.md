# LAUNCH dynamic roadmap-service

Adds dynamic student endpoints while preserving `/api/roadmap` CRUD.

## Endpoints
- `POST /api/roadmap/student/{studentId}/generate`
- `GET /api/roadmap/student/{studentId}`

## Example generate payload
```json
{"faculty":"Engineering","degree":"B.Tech","branch":"Electronics and Communication","specialization":"VLSI","targetRole":"VLSI Engineer","currentSkills":["Digital Electronics"]}
```

Update `application.yml`/environment variables for your PostgreSQL credentials. This starter generates different domain/specialization phase content from the submitted student profile and stores the generated phases in PostgreSQL.
