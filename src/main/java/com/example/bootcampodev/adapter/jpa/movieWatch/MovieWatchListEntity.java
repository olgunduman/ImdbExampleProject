package com.example.bootcampodev.adapter.jpa.movieWatch;

import com.example.bootcampodev.adapter.jpa.movie.MovieEntity;
import com.example.bootcampodev.adapter.jpa.watch.WatchListEntity;
import com.example.bootcampodev.domain.movieWatch.MovieWatchList;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movieWatchList")

public class MovieWatchListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private MovieEntity movie;

    @ManyToOne
    private WatchListEntity watchList;

    public static MovieWatchListEntity from(MovieWatchList movieWatchList) {
        return MovieWatchListEntity.builder()
                .id(movieWatchList.getId())
                .movie(MovieEntity.builder().id(movieWatchList.getMovieId()).build())
                .watchList(WatchListEntity.builder().id(movieWatchList.getWatchListId()).build())
                .build();
    }

    public MovieWatchList toModel() {
        return MovieWatchList.builder()
                .id(this.id)
                .movieId(this.movie.getId())
                .watchListId(this.watchList.getId())
                .build();
    }
}
