package com.example.bootcampodev.service.actor;

import com.example.bootcampodev.service.movie.Movie;

import java.util.Arrays;
import java.util.List;

public interface ActorService {

    Long create(Actor actor);

    List<Movie> retrieveMovies(Long actorId);
}
