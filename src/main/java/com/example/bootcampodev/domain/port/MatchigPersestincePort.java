package com.example.bootcampodev.domain.port;
import com.example.bootcampodev.domain.actor.Actor;
import com.example.bootcampodev.domain.movie.Movie;

import java.util.List;

public interface MatchigPersestincePort {
    void create(Movie movie, List<Actor> actors);

    List<Actor> retrieveActors(Long movieId);
}
