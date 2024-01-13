package com.gautami.shipit.comminicatior;

import com.gautami.shipit.dto.OrderItemDto;
import com.gautami.shipit.model.NotificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class Notification {
    private final RestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8081";


    @Autowired
    public Notification(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    public void sendEmail(String email, List<OrderItemDto> orderItemDto) {
        NotificationRequest request = new NotificationRequest();
        request.setEmail(email);
        String message = "Your order has been placed for the following orders \n";
        for (int i = 0; i < orderItemDto.size(); i++) {
            message = message + orderItemDto.get(i).toString()+"\n";
        }
        request.setMessage(message);
        sendNotification(request);
    }


    public void sendNotification(NotificationRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<NotificationRequest> requestEntity = new HttpEntity<>(request, headers);
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = BASE_URL + "/sendNotification";
        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                System.out.println("Notification sent successfully!");
            } else {
                throw new HttpClientErrorException(responseEntity.getStatusCode());
            }
        } catch (HttpStatusCodeException e) {
            System.err.println("Error sending notification: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Error sending notification: " + e.getMessage());
            throw new RuntimeException("Failed to send notification", e);
        }
    }
}

