package com.example.movies.controller;

import com.example.movies.dto.MovieRequestDTO;
import com.example.movies.dto.MovieResponseDTO;
import com.example.movies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    // Get all movies
    @GetMapping
    public ResponseEntity<Page<MovieResponseDTO>> getAllMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<MovieResponseDTO> moviesPage = movieService.getAllMovies(pageable);
        return ResponseEntity.ok(moviesPage);
    }


    // Get movie by ID
    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> getMovieById(@PathVariable UUID id) {
        Optional<MovieResponseDTO> movie = movieService.getMovieById(id);
        return movie.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new movie
    @PostMapping
    public ResponseEntity<MovieResponseDTO> createMovie(@Valid @RequestBody MovieRequestDTO movieRequestDTO) {
        return new ResponseEntity<>(movieService.createMovie(movieRequestDTO), HttpStatus.CREATED);
    }

    // Update movie by ID
    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> updateMovie(@PathVariable UUID id, @Valid @RequestBody MovieRequestDTO movieRequestDTO) {
        return ResponseEntity.ok(movieService.updateMovie(id, movieRequestDTO));
    }

    // Delete movie by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable UUID id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    // GET /movies with pagination




}
