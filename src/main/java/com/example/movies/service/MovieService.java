package com.example.movies.service;

import com.example.movies.dto.MovieRequestDTO;
import com.example.movies.dto.MovieResponseDTO;
import com.example.movies.model.Movie;
import com.example.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    // Convert Movie entity to MovieResponseDTO
    private MovieResponseDTO convertToDTO(Movie movie) {
        return new MovieResponseDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getDirector(),
                movie.getReleaseYear(),
                movie.getGenre(),
                movie.getRating()
        );
    }

    // Get all movies
//    public List<MovieResponseDTO> getAllMovies() {
//        List<Movie> movies = movieRepository.findAll();
//        return movies.stream().map(this::convertToDTO).collect(Collectors.toList());
//    }

    // Get movie by ID
    public Optional<MovieResponseDTO> getMovieById(UUID id) {
        return movieRepository.findById(id).map(this::convertToDTO);
    }

    // Create a new movie
    @Transactional
    public MovieResponseDTO createMovie(MovieRequestDTO movieRequestDTO) {
        Movie movie = new Movie();
        movie.setTitle(movieRequestDTO.getTitle());
        movie.setDirector(movieRequestDTO.getDirector());
        movie.setReleaseYear(movieRequestDTO.getReleaseYear());
        movie.setGenre(movieRequestDTO.getGenre());
        movie.setRating(movieRequestDTO.getRating());
        movie = movieRepository.save(movie);
        return convertToDTO(movie);
    }

    // Update movie by ID
    @Transactional
    public MovieResponseDTO updateMovie(UUID id, MovieRequestDTO movieRequestDTO) {
        return movieRepository.findById(id)
                .map(movie -> {
                    movie.setTitle(movieRequestDTO.getTitle());
                    movie.setDirector(movieRequestDTO.getDirector());
                    movie.setReleaseYear(movieRequestDTO.getReleaseYear());
                    movie.setGenre(movieRequestDTO.getGenre());
                    movie.setRating(movieRequestDTO.getRating());
                    return convertToDTO(movieRepository.save(movie));
                })
                .orElseThrow(() -> new IllegalArgumentException("Movie not found"));
    }

    // Delete movie by ID
    @Transactional
    public void deleteMovie(UUID id) {
        movieRepository.deleteById(id);
    }

    public Page<MovieResponseDTO> getAllMovies(Pageable pageable) {
        return movieRepository.findAll(pageable)
                .map(this::convertToDTO);
    }





}
