package com.example.bootcampodev.adapter.jpa.movie;

import com.example.bootcampodev.adapter.jpa.common.BaseEntity;
import com.example.bootcampodev.adapter.jpa.rate.RateEntity;
import com.example.bootcampodev.adapter.jpa.enums.Genre;
import com.example.bootcampodev.adapter.jpa.enums.Status;
import com.example.bootcampodev.domain.movie.Movie;
import lombok.*;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Table(name = "movie")
@Entity(name = "movieEntity")
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Where(clause = "status = 'ACTIVE'")
//@Where(clause = "status <> 'ACTIVE'")
@Where(clause = "status <> 'DELETED'")

public class MovieEntity extends BaseEntity {
    

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @NotNull
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @NotNull
    private int releaseYear;

    private String director;

    @OneToMany(mappedBy = "movie",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<RateEntity> rates;

    public static MovieEntity from(Movie movie) {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.id = movie.getId();
        movieEntity.name = movie.getName();
        movieEntity.genre = movie.getGenre();
        movieEntity.releaseYear = movie.getReleaseYear();
        movieEntity.director = movie.getDirector();
        return movieEntity;
    }

    public static MovieEntity fromMovieId(Long movieId) {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.id = movieId;
        return movieEntity;
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
