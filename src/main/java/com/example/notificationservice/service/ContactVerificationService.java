package com.example.notificationservice.service;

import com.example.notificationservice.models.PhoneVerificationModel;
import org.springframework.stereotype.Service;

@Service
public interface ContactVerificationService {
     String sendOtpToMobileNumber(String phoneNumber);
     String sendOtpToEmail(String email);
     String verifyMobileNumber(PhoneVerificationModel verifyObject,int userId);
}
