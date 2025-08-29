package com.bajaj.runner;

import com.bajaj.model.WebhookResponse;
import com.bajaj.service.WebhookService;
import com.bajaj.service.SqlProblemSolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements ApplicationRunner {
    
    @Autowired
    private WebhookService webhookService;
    
    @Autowired
    private SqlProblemSolver sqlProblemSolver;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            WebhookResponse response = webhookService.generateWebhook();
            
            String regNo = "REG12347";
            String solution = sqlProblemSolver.solveProblem(regNo);
            
            webhookService.submitSolution(response.getWebhook(), response.getAccessToken(), solution);
            
            System.out.println("Application flow completed successfully!");
            System.out.println("Generated SQL Query: " + solution);
            
        } catch (Exception e) {
            System.err.println("Error in application flow: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
