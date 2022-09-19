package com.example.bootcampodev.adapter.rest.movie;

import com.example.bootcampodev.adapter.rest.actor.ActorResponse;
import com.example.bootcampodev.adapter.jpa.enums.Genre;
import com.example.bootcampodev.domain.actor.Actor;
import com.example.bootcampodev.domain.movie.Movie;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponse {

    private Long id;
    private String name;
    private Genre genre;
    private int releaseYear;
    private String director;
    private List<ActorResponse> actors;

    public static MovieResponse convertFrom (Movie movie){
        return MovieResponse.builder()
                .id(movie.getId())
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

    public static MovieResponse convertFrom(Movie movie, List<Actor> actor){
       return MovieResponse.builder()
                .id(movie.getId())
                .name(movie.getName())
                .genre(movie.getGenre())
                .releaseYear(movie.getReleaseYear())
                .director(movie.getDirector())
                .actors(ActorResponse.from(actor))
                .build();
    }
}
