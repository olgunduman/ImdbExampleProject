package com.example.bootcampodev.domain.movie;

import com.example.bootcampodev.adapter.jpa.enums.Genre;
import com.example.bootcampodev.adapter.jpa.movie.MovieEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {

    private Long id;
    private String name;
    private Genre genre;
    private int releaseYear;
    private String director;

    public static Movie convertFrom(MovieEntity entity){
        return Movie.builder()
                .id(entity.getId())
                .name(entity.getName())
                .genre(entity.getGenre())
                .releaseYear(entity.getReleaseYear())
                .director(entity.getDirector())
                .build();
    }

    public MovieEntity convertToMovieEntity() {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setName(name);
        movieEntity.setGenre(genre);
        movieEntity.setReleaseYear(releaseYear);
        movieEntity.setDirector(director);

        return movieEntity;
    }
}
