package com.example.bootcampodev.domain.movie;

import com.example.bootcampodev.domain.actor.Actor;

import java.util.List;


public interface MovieService {
    Long createMovie(Movie movie, List<Actor> actors, List<Long> actorIds);

    Movie retrieve(Long id);

    Movie deleteById(Long id);


    List<Movie> getAll();

    void softDelete(Long id);
}
