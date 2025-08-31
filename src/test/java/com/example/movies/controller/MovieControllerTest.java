package com.example.movies.controller;

import com.example.movies.dto.MovieRequestDTO;
import com.example.movies.dto.MovieResponseDTO;
import com.example.movies.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MovieController.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    private MovieResponseDTO sampleMovie;

    @BeforeEach
    void setup() {
        sampleMovie = new MovieResponseDTO(
                UUID.randomUUID(),
                "Inception",
                "Christopher Nolan",
                2010,
                "Sci-Fi",
                9.0
        );
    }

    @Test
    void testGetAllMovies() throws Exception {
        Page<MovieResponseDTO> page = new PageImpl<>(Collections.singletonList(sampleMovie));

        Mockito.when(movieService.getAllMovies(Mockito.any(Pageable.class)))
                .thenReturn(page);

        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title").value("Inception"))
                .andExpect(jsonPath("$.content[0].director").value("Christopher Nolan"))
                .andExpect(jsonPath("$.content[0].releaseYear").value(2010))
                .andExpect(jsonPath("$.content[0].genre").value("Sci-Fi"))
                .andExpect(jsonPath("$.content[0].rating").value(9.0));
    }


    @Test
    void testCreateMovie() throws Exception {
        Mockito.when(movieService.createMovie(any(MovieRequestDTO.class))).thenReturn(sampleMovie);

        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON) // ✅ send JSON
                        .content("""
                        {
                          "title": "Inception",
                          "director": "Christopher Nolan",
                          "releaseYear": 2010,
                          "genre": "Sci-Fi",
                          "rating": 9.0
                        }
                        """))
                .andExpect(status().isCreated()) // ✅ expect 201
                .andExpect(jsonPath("$.title").value("Inception"))
                .andExpect(jsonPath("$.director").value("Christopher Nolan"))
                .andExpect(jsonPath("$.releaseYear").value(2010))
                .andExpect(jsonPath("$.genre").value("Sci-Fi"))
                .andExpect(jsonPath("$.rating").value(9.0))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}
