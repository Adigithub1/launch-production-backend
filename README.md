# LAUNCH production-style layered backend
Generated directly from the uploaded React routes, pages and API client. Includes API Gateway, Eureka registry and 11 business services. Every business service has Entity, Repository, DTO, Mapper, Service interface, ServiceImpl, Controller, exception handler, Flyway SQL and test package.

Run: `docker compose up --build`
Gateway: `http://localhost:8080`

This is a production-style source foundation, not a claim that third-party integrations are live. Real payment, AI, OTP, video, email/SMS and cloud file storage require provider choices and credentials. Maven was not available in the artifact environment, so the project structure and ZIP were verified, but the complete Maven build was not executed here.
