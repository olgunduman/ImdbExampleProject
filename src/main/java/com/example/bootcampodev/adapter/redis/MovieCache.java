package com.example.bootcampodev.adapter.redis;

import com.example.bootcampodev.adapter.jpa.enums.Genre;
import com.example.bootcampodev.domain.movie.Movie;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieCache {

        private Long id;
        private String name;

        private Genre genre;

        private Integer releaseYear;

        private String director;

        public static MovieCache from(Movie movie){
                return MovieCache.builder()
                        .id(movie.getId())
                        .name(movie.getName())
                        .genre(movie.getGenre())
                        .releaseYear(movie.getReleaseYear())
                        .director(movie.getDirector())
                        .build();
        }

        public Movie toModel() {
                return Movie.builder()
                        .id(this.id)
                        .name(this.name)
                        .genre(this.genre)
                        .releaseYear(this.releaseYear)
                        .director(this.director)
                        .build();
        }
}
