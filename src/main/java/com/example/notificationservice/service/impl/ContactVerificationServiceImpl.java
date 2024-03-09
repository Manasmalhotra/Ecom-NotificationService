package com.example.notificationservice.service.impl;

import com.example.notificationservice.Exceptions.VerificationFailedException;
import com.example.notificationservice.models.PhoneVerificationModel;
import com.example.notificationservice.service.ContactVerificationService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;

public class ContactVerificationServiceImpl implements ContactVerificationService {
    @Value(value="${app.TWILIO_ACCOUNT_SID}")
    String accountSid;
    @Value(value="${app.TWILIO_AUTH_TOKEN}")
    String authToken;
    @Override
    public String sendOtpToMobileNumber(String phoneNumber) {
        Twilio.init(accountSid,authToken);

        Verification verification = Verification.creator(
                        "VA3d401c55ae5fc3435800487ab7177fba", // this is your verification sid
                        phoneNumber, //this is your Twilio verified recipient phone number
                        "sms") // this is your channel type
                .create();
        Message message = Message
                .creator(new PhoneNumber(phoneNumber),new PhoneNumber("+918368455166"),"Hello")
                .create();
        System.out.println(verification.getStatus());
        return "Your OTP has been sent to your phone number";
    }

    @Override
    public String sendOtpToEmail(String email) {
        return null;
    }

    @Override
    public String verifyMobileNumber(PhoneVerificationModel verifyObject,int userId) {
        Twilio.init(accountSid,authToken);

        try {

            VerificationCheck verificationCheck = VerificationCheck.creator(
                            "VA3d401c55ae5fc3435800487ab7177fba")
                    .setTo(verifyObject.getPhoneNumber())
                    .setCode(verifyObject.getOtp())
                    .create();

            System.out.println(verificationCheck.getStatus());

        } catch (Exception e) {
            throw new VerificationFailedException("Mobile Number Verification Failed");
        }

        return "This user's verification has been completed successfully";
    }
}
