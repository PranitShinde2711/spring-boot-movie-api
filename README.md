Spring Boot Movie API
A RESTful API built with Spring Boot to manage movies. Features include CRUD operations, pagination, integration tests,unit tests, swagger integration and global exception handling.

🛠 Tech Stack
1. Java 21
2. Spring Boot 3.2
3. Spring Data JPA (Hibernate)
4. H2 Database (in-memory)
5. JUnit 5 & AssertJ (Integration Testing)
6. Maven
7. Swagger (OpenAPI)

▶️ How to Run the Project
1. Clone the repository
1] git clone https://github.com/PranitShinde2711/spring-boot-movie-api.git
2] cd spring-boot-movie-api

2. Build the project
1] mvn clean install
2] Run the application-  mvn spring-boot:run

3. Access the API
Base URL:  http://localhost:8080/movies

📖API Endpoints
1] movies
2] POST /movies → Create a movie
3] GET /movies/{id} → Get movie by ID
4] PUT /movies/{id} → Update movie
5] DELETE /movies/{id} → Delete movie
6] GET /movies?page=0&size=5 → Get all movies (with pagination)

🧪 How to Run Tests

1] Run all tests (including integration tests): mvn test
2] To run only integration tests:  mvn test -Dtest=MovieIntegrationTest

📑 Swagger API Docs
Swagger UI is available when the app is running:
👉 http://localhost:8080/swagger-ui.html
