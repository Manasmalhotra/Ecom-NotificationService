package com.example.notificationservice.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.kafka.annotation.KafkaListener;

public interface SendMobileVerificationConsumer {
    @KafkaListener(topics="mobileVerificationSuccess",groupId="mobileVerificationService")
    void sendMobileVerificationMessage(String message) throws JsonProcessingException;
}
