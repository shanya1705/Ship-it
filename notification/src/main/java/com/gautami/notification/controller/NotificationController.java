package com.gautami.notification.controller;

import com.gautami.notification.model.NotificationRequest;
import com.gautami.notification.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendNotification")
    @ResponseStatus(HttpStatus.OK)
    public String sendNotification(@RequestBody NotificationRequest request) {
        emailService.sendNotification(request.getEmail(), "Order confirmation- ShipIt", request.getMessage());
        return "Notification sent successfully!";
    }
}
