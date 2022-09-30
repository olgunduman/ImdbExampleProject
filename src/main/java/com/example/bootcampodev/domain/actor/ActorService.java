package com.example.bootcampodev.domain.actor;

import com.example.bootcampodev.domain.movie.Movie;

import java.util.List;

public interface ActorService {

    Long create(Actor actor);

    List<Movie> retrieveMovies(Long actorId);

    List<Actor> retrieveActors(Long movieId);
}
