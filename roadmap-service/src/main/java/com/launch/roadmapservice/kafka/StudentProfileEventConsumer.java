package com.launch.roadmapservice.kafka;

import java.util.Map;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StudentProfileEventConsumer {

    private final ObjectMapper objectMapper;

    public StudentProfileEventConsumer(
            ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(
            topics = "student.profile.updated.v1",
            groupId = "${KAFKA_GROUP_ID:launch-roadmap-service}"
    )
    public void consume(String payload) {

        try {
            Map<String, Object> event =
                    objectMapper.readValue(
                            payload,
                            new TypeReference<Map<String, Object>>() {
                            }
                    );

            Long studentId = Long.valueOf(
                    String.valueOf(event.get("studentId"))
            );

            String selectedCareer = String.valueOf(
                    event.get("selectedCareer")
            );

            System.out.println(
                    "KAFKA EVENT RECEIVED: studentId=" +
                    studentId +
                    ", selectedCareer=" +
                    selectedCareer +
                    ", payload=" +
                    payload
            );

            /*
             * Do not generate the roadmap here until the
             * Kafka connectivity test succeeds.
             *
             * After success, call your existing roadmap method:
             *
             * roadmapService.regenerateForCareer(
             *     studentId,
             *     selectedCareer
             * );
             */
        } catch (Exception exception) {
            System.err.println(
                    "KAFKA EVENT PROCESSING FAILED: " +
                    payload
            );

            exception.printStackTrace();

            // Throwing causes the listener to treat this as a failure.
            throw new IllegalStateException(
                    "Unable to process student profile event",
                    exception
            );
        }
    }
}