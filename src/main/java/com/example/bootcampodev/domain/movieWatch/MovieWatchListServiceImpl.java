package com.example.bootcampodev.domain.movieWatch;

import com.example.bootcampodev.domain.movie.Movie;
import com.example.bootcampodev.domain.port.MoviePersistencePort;
import com.example.bootcampodev.domain.port.MovieWatchListPersistencePort;
import com.example.bootcampodev.domain.port.WatchListPersistencePort;
import com.example.bootcampodev.domain.watch.WatchList;
import org.springframework.stereotype.Service;

@Service
public class MovieWatchListServiceImpl implements MovieWatchListService {

    private final MovieWatchListPersistencePort movieWatchListPersistencePort;
    private final MoviePersistencePort moviePersistencePort;
    private final WatchListPersistencePort watchListPersistencePort;

    public MovieWatchListServiceImpl(MovieWatchListPersistencePort movieWatchListPersistencePort, MoviePersistencePort moviePersistencePort, WatchListPersistencePort watchListPersistencePort) {
        this.movieWatchListPersistencePort = movieWatchListPersistencePort;
        this.moviePersistencePort = moviePersistencePort;
        this.watchListPersistencePort = watchListPersistencePort;
    }

    @Override
    public MovieWatchList addMovie(MovieWatchList movieWatchList) {

        Movie retrieve = moviePersistencePort.retrieve(movieWatchList.getMovieId());

        WatchList watchList  = watchListPersistencePort.retrieve(movieWatchList.getWatchListId());

        MovieWatchList movieWatchListEntity = movieWatchList.convertToMovieWatch(retrieve,watchList);

        movieWatchListPersistencePort.create(movieWatchListEntity);
        return movieWatchList;

    }
}
