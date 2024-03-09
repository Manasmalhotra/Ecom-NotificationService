package com.example.notificationservice.service.impl;

import com.example.notificationservice.models.EmailVerifiedEvent;
import com.example.notificationservice.service.SendEmailVerificationConsumer;
import com.example.notificationservice.util.EmailUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Service
public class SendEmailVerificationServiceImpl implements SendEmailVerificationConsumer {
    ObjectMapper objectMapper;
    public SendEmailVerificationServiceImpl(ObjectMapper objectMapper){
        this.objectMapper=objectMapper;
    }
    @Override
    @KafkaListener(topics="emailVerificationSuccess",groupId = "emailVerificationService")
    public void sendEmailVerificationMessage(String message) throws JsonProcessingException {
        EmailVerifiedEvent emailVerifyEvent=objectMapper.readValue(message,EmailVerifiedEvent.class);
        final String fromEmail = emailVerifyEvent.getFrom(); //requires valid gmail id
        final String password = "xyz"; // correct password for gmail id

        System.out.println("SSLEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
        props.put("mail.smtp.port", "465"); //SMTP Port

        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getDefaultInstance(props, auth);
        System.out.println("Session created");
        EmailUtil.sendEmail(session,emailVerifyEvent.getTo(),emailVerifyEvent.getSubject(),emailVerifyEvent.getBody());
    }
}
