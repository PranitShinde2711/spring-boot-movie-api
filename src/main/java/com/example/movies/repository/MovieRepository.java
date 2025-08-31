package com.example.movies.repository;

import com.example.movies.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {

    // Find movies by title (case-insensitive search)
    List<Movie> findByTitleContainingIgnoreCase(String title);

    // Find movies by director (case-insensitive search)
    List<Movie> findByDirectorContainingIgnoreCase(String director);

    // Find movies by genre
    List<Movie> findByGenre(String genre);

    // Find movies by release year
    List<Movie> findByReleaseYear(Integer releaseYear);

    // Find movies by rating range
    List<Movie> findByRatingBetween(Double minRating, Double maxRating);

    // Find movie by ID
    Optional<Movie> findById(UUID id);
}
