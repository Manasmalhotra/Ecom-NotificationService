package com.example.notificationservice.controller;

import com.example.notificationservice.models.PhoneVerificationModel;
import com.example.notificationservice.service.ContactVerificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/verify")
public class ContactVerification {
    ContactVerificationService contactVerificationService;
    public ContactVerification(ContactVerificationService contactVerificationService){
        this.contactVerificationService=contactVerificationService;
    }
    @GetMapping("/mobileNumber")
    public ResponseEntity<String> generateOTP(@RequestBody String phoneNumber) {
        return new ResponseEntity<>(contactVerificationService.sendOtpToMobileNumber(phoneNumber),HttpStatus.OK);
    }
    @GetMapping("/verifyMobileOTP")
    public ResponseEntity<String> verifyUserOTP(@PathVariable int userId, @RequestBody PhoneVerificationModel verifyObject) throws Exception {
           return ResponseEntity.ok(contactVerificationService.verifyMobileNumber(verifyObject,userId));
    }
}
