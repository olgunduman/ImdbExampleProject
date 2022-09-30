package com.example.bootcampodev.domain.port;


import com.example.bootcampodev.domain.movieWatch.MovieWatchList;

public interface MovieWatchListPersistencePort {
    MovieWatchList create(MovieWatchList movieWatchList);
}
