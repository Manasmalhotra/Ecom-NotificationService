package com.example.notificationservice.service.impl;

import com.example.notificationservice.models.MobileVerifiedEvent;
import com.example.notificationservice.service.SendMobileVerificationConsumer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SendMobileVerificationServiceImpl implements SendMobileVerificationConsumer {
    ObjectMapper objectMapper;
    @Value(value="${app.TWILIO_ACCOUNT_SID}")
    String accountSid;
    @Value(value="${app.TWILIO_AUTH_TOKEN}")
    String authToken;
    public SendMobileVerificationServiceImpl(ObjectMapper objectMapper){
        this.objectMapper=objectMapper;
    }
    @Override
    @KafkaListener(topics="mobileVerificationSuccess",groupId="mobileVerificationService")
    public void sendMobileVerificationMessage(String message){
        try {
            MobileVerifiedEvent mobileVerifyEvent = objectMapper.readValue(message, MobileVerifiedEvent.class);
            Twilio.init(accountSid, authToken);
            Message sms = Message
                    .creator(new PhoneNumber(mobileVerifyEvent.getCustomerMobileNumber()),
                            new PhoneNumber(mobileVerifyEvent.getCompanyMobileNumber()),
                            mobileVerifyEvent.getMessage())
                    .create();
        }
        catch(Exception e){
           System.out.println("Something went wrong!");
        }
    }
}
