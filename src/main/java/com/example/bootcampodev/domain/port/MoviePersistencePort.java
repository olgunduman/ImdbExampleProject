package com.example.bootcampodev.domain.port;

import com.example.bootcampodev.domain.movie.Movie;

import java.util.List;

public interface MoviePersistencePort {
    Movie save(Movie movie);

    Movie retrieve(Long movieId);

    Movie deleteById(Long id);

    List<Movie> findAll();

    List<Movie> retrieveByActorId(Long actorId);

    void softDelete(Long id);
}
