package com.example.bootcampodev.adapter.jpa.movie;

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
@EntityListeners(AuditingEntityListener.class)
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @CreatedDate
    private LocalDateTime createdDate;

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
        movieEntity.setId(movie.getId());
        movieEntity.setName(movie.getName());
        movieEntity.setGenre(movie.getGenre());
        movieEntity.setReleaseYear(movie.getReleaseYear());
        movieEntity.setDirector(movie.getDirector());
        return movieEntity;
    }

    public static MovieEntity fromMovieId(Long movieId) {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setId(movieId);
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
