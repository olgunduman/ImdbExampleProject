package com.example.bootcampodev.dto.response.movieWatch;

import com.example.bootcampodev.service.movieWatch.MovieWatchList;
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
