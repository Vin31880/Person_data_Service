# Person-Data-Collection-App

Problem Statement:
1.	Crete a Rest API which stores, updates, retrieved and delete Person entity.

Solution:

Person Service:
-	It provides Rest API’s which stores, updates, retrieved and delete Person entity from in-memory H2 database.
-	Developed using Spring Boot application
-	Validate input using hibernate-validators.
-	Used CrudRepository for DAO operation.
-	Added test cases using Spock framework.

Things to Note
1.	Spock Framework: I have used Spock framework for testing Person-service.

2.	Swagger Documentation: Swagger UI for Receiver service is available on http://localhost:8080/swagger-ui.html

3. Validation using hibernate-validators

How to run ?
1. Using Maven
- The Person Service is Spring boot maven-based applications
- Go to app path using terminal and use command “mvn clean install” to build app
- To run backend app “mvn spring-boot:run”

2. Using Docker
- Person Data Service images is published at docker hub
- Use command "docker pull jaybhavi123/person_data_service:latest" to pull the image
- Use command "docker run -p 8080:8080 jaybhavi123/person_data_service" to launch container
