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
git clone https://github.com/PranitShinde2711/spring-boot-movie-api.git
cd spring-boot-movie-api


Build the project

mvn clean install


Run the application

mvn spring-boot:run


Access the API

Base URL: http://localhost:8080/movies

📖 API Endpoints
Movies

POST /movies → Create a movie

GET /movies/{id} → Get movie by ID

PUT /movies/{id} → Update movie

DELETE /movies/{id} → Delete movie

GET /movies?page=0&size=5 → Get all movies (with pagination)

🧪 How to Run Tests

Run all tests (including integration tests):

mvn test

📑 Swagger API Docs

Swagger UI is available when the app is running:
👉 http://localhost:8080/swagger-ui.html
