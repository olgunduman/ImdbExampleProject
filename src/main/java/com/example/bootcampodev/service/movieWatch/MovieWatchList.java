package com.example.bootcampodev.service.movieWatch;

import com.example.bootcampodev.entity.MovieEntity;
import com.example.bootcampodev.entity.MovieWatchListEntity;
import com.example.bootcampodev.entity.WatchListEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class MovieWatchList {

    private Long id;


    private Long movieId;
    private Long watchListId;

    public MovieWatchListEntity convertToMovieWatch(MovieEntity movieEntity, WatchListEntity watchListEntity) {
        MovieWatchListEntity movieWatchList = new MovieWatchListEntity();
        movieWatchList.setId(this.id);
        movieWatchList.setMovie(movieEntity);
        movieWatchList.setWatchList(watchListEntity);

        return movieWatchList;
    }
}
