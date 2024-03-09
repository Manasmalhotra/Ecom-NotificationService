package com.example.notificationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.kafka.annotation.KafkaListener;

public interface SendEmailVerificationConsumer {
    @KafkaListener(topics="emailVerificationSuccess",groupId="emailVerificationService")
    void sendEmailVerificationMessage(String message) throws JsonProcessingException;
}
