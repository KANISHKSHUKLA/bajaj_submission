# Bajaj Finserv Health Qualifier Application

## Overview
Spring Boot application that automatically generates webhooks, solves SQL problems, and submits solutions on startup.

## Features
- Automatic webhook generation on application startup
- SQL problem solving based on registration number
- JWT token authentication for API calls
- Automatic solution submission

## Technology Stack
- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- H2 Database
- RestTemplate for HTTP calls
- Maven for build management

## Project Structure
```
src/main/java/com/bajaj/
├── Application.java              # Main Spring Boot application
├── config/
│   └── RestTemplateConfig.java  # RestTemplate configuration
├── model/
│   ├── WebhookRequest.java      # Webhook request model
│   ├── WebhookResponse.java     # Webhook response model
│   └── SolutionRequest.java     # Solution submission model
├── runner/
│   └── ApplicationRunner.java   # Application startup runner
└── service/
    ├── WebhookService.java      # Webhook operations service
    └── SqlProblemSolver.java    # SQL problem solving service
```

## Build Instructions

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

### Build Steps
1. Clone the repository
2. Navigate to project directory
3. Run: `mvn clean package`
4. Find JAR file in `target/` directory

### Run Application
```bash
java -jar target/bajaj-finserv-health-1.0.0.jar
```

## Application Flow
1. **Startup**: Application automatically runs on startup
2. **Webhook Generation**: Sends POST request to generate webhook
3. **Problem Solving**: Determines SQL problem based on registration number
4. **Solution Submission**: Submits SQL solution with JWT authentication

## API Endpoints
- **Generate Webhook**: `POST /hiring/generateWebhook/JAVA`
- **Submit Solution**: `POST /hiring/testWebhook/JAVA`

## Configuration
- Application runs on port 8080
- H2 database for development
- Debug logging enabled for Bajaj package

## Notes
- No REST controllers required
- Flow executes automatically on startup
- Uses RestTemplate for HTTP operations
- JWT token handling for authentication
