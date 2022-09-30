package com.example.bootcampodev.adapter.rest.watchList;

import com.example.bootcampodev.domain.movieWatch.MovieWatchList;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MovieWatchListResponse {

    private Long movieId;
    private Long watchListId;



    public static MovieWatchListResponse convertFromMovieWatch(MovieWatchList movieWatchList) {
        return MovieWatchListResponse.builder()
                .movieId(movieWatchList.getMovieId())
                .watchListId(movieWatchList.getWatchListId())
                .build();
    }
}
