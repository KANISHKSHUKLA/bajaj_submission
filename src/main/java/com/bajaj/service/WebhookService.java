package com.bajaj.service;

import com.bajaj.model.WebhookRequest;
import com.bajaj.model.WebhookResponse;
import com.bajaj.model.SolutionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebhookService {
    
    private static final String GENERATE_WEBHOOK_URL = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";
    private static final String TEST_WEBHOOK_URL = "https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA";
    
    @Autowired
    private RestTemplate restTemplate;
    
    public WebhookResponse generateWebhook() {
        WebhookRequest request = new WebhookRequest("John Doe", "REG12347", "john@example.com");
        HttpEntity<WebhookRequest> entity = new HttpEntity<>(request);
        
        ResponseEntity<WebhookResponse> response = restTemplate.exchange(
            GENERATE_WEBHOOK_URL, 
            HttpMethod.POST, 
            entity, 
            WebhookResponse.class
        );
        
        return response.getBody();
    }
    
    public void submitSolution(String webhookUrl, String accessToken, String finalQuery) {
        SolutionRequest request = new SolutionRequest(finalQuery);
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);
        headers.set("Content-Type", "application/json");
        
        HttpEntity<SolutionRequest> entity = new HttpEntity<>(request, headers);
        
        restTemplate.exchange(
            webhookUrl, 
            HttpMethod.POST, 
            entity, 
            String.class
        );
    }
}
