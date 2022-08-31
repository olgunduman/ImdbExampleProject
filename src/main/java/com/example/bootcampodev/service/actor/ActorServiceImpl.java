package com.example.bootcampodev.service.actor;

import com.example.bootcampodev.repository.actor.ActorDao;
import com.example.bootcampodev.repository.movie.MovieDao;
import com.example.bootcampodev.service.movie.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService{

    private final ActorDao actorDao;
    private final MovieDao movieDao;
    @Override
    public Long create(Actor actor) {
        return  actorDao.create(actor.convertToActorEntity());
    }




    @Override
    public List<Movie> retrieveMovies(Long actorId) {
        return movieDao.retrieveByActorId(actorId)
                .stream()
                .map(Movie::convertFrom)
                .collect(Collectors.toList());
    }
}
