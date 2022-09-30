package com.example.bootcampodev.domain.movieWatch;


import com.example.bootcampodev.domain.movie.Movie;
import com.example.bootcampodev.domain.watch.WatchList;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MovieWatchList {

    private Long id;


    private Long movieId;
    private Long watchListId;

    public MovieWatchList convertToMovieWatch(Movie movie, WatchList watchList) {
        MovieWatchList movieWatchList = new MovieWatchList();
        movieWatchList.setId(this.id);
        movieWatchList.setMovieId(movie.getId());
        movieWatchList.setWatchListId(watchList.getId());

        return movieWatchList;
    }
}
