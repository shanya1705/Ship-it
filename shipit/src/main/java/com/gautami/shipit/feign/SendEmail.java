package com.gautami.shipit.feign;


import com.gautami.shipit.model.NotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service")
public interface SendEmail {

    @PostMapping("/sendNotification")
    public String sendNotification(@RequestBody NotificationRequest request);
}
