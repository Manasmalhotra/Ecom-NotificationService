package com.example.notificationservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneVerificationModel {
    String phoneNumber;
    String otp;
}
