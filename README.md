
# E-commerce Notification Service

Welcome to the E-commerce Notification Service! This service provides functionalities for forwarding important notifications to users through email and sms services.


## Tech Stack

**Programming Language:** Java

**Framework/Libraries:** Spring Boot, Kafka

**Other Technologies:** Twilio API and Java Mail API


## Problem Statement

Every customer likes to feel valued and always be updated with the recent notifications from their favorite shopping sites.

Thus, we required a service that can push required notifications to the users on their mobile phones and emails, without slowing down the user flow through the application.

## Solution

To build a fast, robust, and fault-tolerant notification service, the key concern was that its processing time should not be visible to the user and at the same time, important messages like SMS and email after user registration or order confirmation should not be dropped due to any downtime in our system.

To fulfill these requirements I used Kafka to receive messages for notification service and 3rd part APIs of Twilio and Java Mail to notify the users.

Kafka provides us with fast and asynchronous communication and as pushing notifications to different users needed to be fast and the order does not matter much, I finalized Kafka. Kafka also offers us reliability in communication between microservices because as soon as we push anything in Kafka Queue it is assured that it will reach the destination service.
