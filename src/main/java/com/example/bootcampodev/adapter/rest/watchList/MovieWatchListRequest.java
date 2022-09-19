package com.example.bootcampodev.adapter.rest.watchList;

import com.example.bootcampodev.domain.movieWatch.MovieWatchList;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MovieWatchListRequest {

    private Long movieId;
    private Long watchListId;


    public MovieWatchList convertToMovieWatch() {
       return MovieWatchList.builder()
               .movieId(this.movieId)
               .watchListId(this.watchListId)
               .build();
    }
}
