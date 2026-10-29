package com.launch.studentservice.kafka;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StudentProfileEventProducer {

    public static final String TOPIC = "student.profile.updated.v1";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public StudentProfileEventProducer(
            KafkaTemplate<String, String> kafkaTemplate,
            ObjectMapper objectMapper) {

        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void publish(
            Long studentId,
            String selectedCareer) {

        Map<String, Object> event = new LinkedHashMap<>();

        event.put("eventType", "STUDENT_PROFILE_UPDATED");
        event.put("studentId", studentId);
        event.put("selectedCareer", selectedCareer);

        final String payload;

        try {
            payload = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException exception) {
            throw new IllegalStateException(
                    "Unable to serialize student profile event",
                    exception
            );
        }

        kafkaTemplate.send(
                TOPIC,
                String.valueOf(studentId),
                payload
        ).whenComplete((result, exception) -> {

            if (exception != null) {
                System.err.println(
                        "KAFKA SEND FAILED: " +
                        exception.getMessage()
                );

                exception.printStackTrace();
                return;
            }

            System.out.println(
                    "KAFKA SEND SUCCESS: topic=" +
                    result.getRecordMetadata().topic() +
                    ", partition=" +
                    result.getRecordMetadata().partition() +
                    ", offset=" +
                    result.getRecordMetadata().offset() +
                    ", payload=" +
                    payload
            );
        });
    }
}