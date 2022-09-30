package com.example.bootcampodev.adapter.jpa.movieWatch;

import com.example.bootcampodev.adapter.jpa.common.BaseEntity;
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

public class MovieWatchListEntity extends BaseEntity {



    @ManyToOne
    private MovieEntity movie;

    @ManyToOne
    private WatchListEntity watchList;

    public static MovieWatchListEntity from(MovieWatchList movieWatchList) {

        MovieWatchListEntity movieWatchListEntity = new MovieWatchListEntity();
        movieWatchListEntity.id = movieWatchList.getId();
        movieWatchListEntity.movie = MovieEntity.fromMovieId(movieWatchList.getMovieId());
        movieWatchListEntity.watchList = WatchListEntity.fromWatchListId(movieWatchList.getWatchListId());
        return movieWatchListEntity;

    }

    public MovieWatchList toModel() {
        return MovieWatchList.builder()
                .id(this.id)
                .movieId(this.movie.getId())
                .watchListId(this.watchList.getId())
                .build();
    }
}
