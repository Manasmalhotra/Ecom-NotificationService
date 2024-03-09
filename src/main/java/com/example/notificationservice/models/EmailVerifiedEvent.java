package com.example.notificationservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailVerifiedEvent {
    String to;
    String from;
    String subject;
    String body;
}
