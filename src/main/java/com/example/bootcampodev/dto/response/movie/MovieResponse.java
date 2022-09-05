package com.example.bootcampodev.dto.response.movie;

import com.example.bootcampodev.dto.response.actor.ActorResponse;
import com.example.bootcampodev.entity.Genre;
import com.example.bootcampodev.service.actor.Actor;
import com.example.bootcampodev.service.movie.Movie;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MovieResponse {
    private String name;
    private Genre genre;
    private int releaseYear;
    private String director;
    private List<ActorResponse> actors;

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

    public static MovieResponse convertFrom(Movie entity, List<Actor> actor){
        return MovieResponse.builder()
                .name(entity.getName())
                .genre(entity.getGenre())
                .releaseYear(entity.getReleaseYear())
                .director(entity.getDirector())
                .actors(ActorResponse.from(actor))
                .build();
    }
}
