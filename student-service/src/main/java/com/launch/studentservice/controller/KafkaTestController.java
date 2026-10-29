package com.launch.studentservice.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.launch.studentservice.kafka.StudentProfileEventProducer;

@RestController
@RequestMapping("/api/kafka")
public class KafkaTestController {

    private final StudentProfileEventProducer eventProducer;

    public KafkaTestController(
            StudentProfileEventProducer eventProducer) {
        this.eventProducer = eventProducer;
    }

    @PostMapping("/test")
    public ResponseEntity<Map<String, Object>> testKafka(
            @RequestParam Long studentId,
            @RequestParam String career) {

        eventProducer.publish(studentId, career);

        return ResponseEntity.accepted().body(
                Map.of(
                        "message", "Kafka event accepted",
                        "studentId", studentId,
                        "career", career
                )
        );
    }
}