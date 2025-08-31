package com.example.movies.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequestDTO {
    @NotBlank(message = "Title is required")
    private String title;

    private String director;

    @Min(1880)
    private int releaseYear;

    private String genre;

    @Min(1)
    @Max(10)
    private double rating;
}
