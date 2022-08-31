package com.example.bootcampodev.dto.response.movie;

import com.example.bootcampodev.entity.Genre;
import com.example.bootcampodev.service.movie.Movie;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@EqualsAndHashCode
public class MovieResponse {

    private String name;
    private Genre genre;
    private int releaseYear;
    private String director;

    public static MovieResponse convertFrom (Movie movie){
        return MovieResponse.builder()
                .name(movie.getName())
                .genre(movie.getGenre())
                .releaseYear(movie.getReleaseYear())
                .director(movie.getDirector())
                .build();
    }

    public static List<MovieResponse> convertFromListMovie(List<Movie> movies) {
      return  movies.stream()
                .map(MovieResponse::convertFrom)
                .collect(Collectors.toList());
    }
}
