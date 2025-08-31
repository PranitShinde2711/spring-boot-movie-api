package com.example.movies.integration;

import com.example.movies.dto.MovieRequestDTO;
import com.example.movies.dto.MovieResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = { "spring.profiles.active=test" } // use application-test.properties
)
class MovieIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/movies";
    }

    @Test
    void shouldPerformFullCrudFlow() {
        // 1. Create Movie
        MovieRequestDTO createRequest = new MovieRequestDTO(
                "The Dark Knight",
                "Christopher Nolan",
                2008,
                "Action",
                9.0
        );

        ResponseEntity<MovieResponseDTO> createResponse =
                restTemplate.postForEntity(getBaseUrl(), createRequest, MovieResponseDTO.class);

        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        MovieResponseDTO createdMovie = createResponse.getBody();
        assertThat(createdMovie).isNotNull();
        assertThat(createdMovie.getId()).isNotNull();
        UUID movieId = createdMovie.getId();

        // 2. Get Movie by ID
        ResponseEntity<MovieResponseDTO> getResponse =
                restTemplate.getForEntity(getBaseUrl() + "/" + movieId, MovieResponseDTO.class);

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody()).isNotNull();
        assertThat(getResponse.getBody().getTitle()).isEqualTo("The Dark Knight");

        // 3. Update Movie
        MovieRequestDTO updateRequest = new MovieRequestDTO(
                "The Dark Knight Rises",
                "Christopher Nolan",
                2012,
                "Action",
                8.5
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MovieRequestDTO> updateEntity = new HttpEntity<>(updateRequest, headers);

        ResponseEntity<MovieResponseDTO> updateResponse = restTemplate.exchange(
                getBaseUrl() + "/" + movieId,
                HttpMethod.PUT,
                updateEntity,
                MovieResponseDTO.class
        );

        assertThat(updateResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(updateResponse.getBody()).isNotNull();
        assertThat(updateResponse.getBody().getTitle()).isEqualTo("The Dark Knight Rises");
        assertThat(updateResponse.getBody().getReleaseYear()).isEqualTo(2012);

        // 4. Delete Movie
        restTemplate.delete(getBaseUrl() + "/" + movieId);

        ResponseEntity<MovieResponseDTO> afterDeleteResponse =
                restTemplate.getForEntity(getBaseUrl() + "/" + movieId, MovieResponseDTO.class);

        assertThat(afterDeleteResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
