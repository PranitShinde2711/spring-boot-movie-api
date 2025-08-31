package com.example.movies.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponseDTO {
    private UUID id;
    private String title;
    private String director;
    private int releaseYear;
    private String genre;
    private double rating;
}
