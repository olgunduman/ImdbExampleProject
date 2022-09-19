package com.example.bootcampodev.domain.port;

import com.example.bootcampodev.domain.movie.Movie;

public interface MovieCachePort {
    Movie retrieveMovie(Long movieId);

    void createMovie(Movie movie);
}
