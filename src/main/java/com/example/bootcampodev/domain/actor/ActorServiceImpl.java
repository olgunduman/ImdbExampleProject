package com.example.bootcampodev.domain.actor;

import com.example.bootcampodev.domain.port.ActorPersistencePort;
import com.example.bootcampodev.domain.port.MatchigPersestincePort;
import com.example.bootcampodev.domain.port.MoviePersistencePort;
import com.example.bootcampodev.domain.movie.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService{

    private final ActorPersistencePort actorPersistencePort;
    private final MoviePersistencePort moviePersistencePort;

    private final MatchigPersestincePort matchigPersestincePort;

    @Override
    public Long create(Actor actor) {

        return actorPersistencePort.create(actor);
    }




    @Override
    public List<Movie> retrieveMovies(Long actorId) {
        return moviePersistencePort.retrieveByActorId(actorId);
    }

    @Override
    public List<Actor> retrieveActors(Long movieId) {
        return matchigPersestincePort.retrieveActors(movieId);

    }
}
