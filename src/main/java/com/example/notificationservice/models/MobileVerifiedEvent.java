package com.example.notificationservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MobileVerifiedEvent {
    String companyMobileNumber;
    String customerMobileNumber;
    String message;
}
