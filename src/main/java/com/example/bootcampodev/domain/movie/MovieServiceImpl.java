package com.example.bootcampodev.domain.movie;

import com.example.bootcampodev.domain.actor.Actor;
import com.example.bootcampodev.domain.exception.ExceptionType;
import com.example.bootcampodev.domain.exception.MyProjectValidationException;
import com.example.bootcampodev.domain.port.ActorPersistencePort;
import com.example.bootcampodev.domain.port.MatchigPersestincePort;
import com.example.bootcampodev.domain.port.MoviePersistencePort;
import com.example.bootcampodev.domain.port.MovieCachePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class MovieServiceImpl {

        private final MoviePersistencePort moviePersistencePort;
        private final ActorPersistencePort actorPersistencePort;
        private final MatchigPersestincePort mathcingPersestincePort;

        private final MovieCachePort movieCachePort;




    public Long createMovie(Movie movie, List<Actor> actors, List<Long> actorIds) {
        List<Actor> existingActors = retrieveExistingActors(actorIds);
        List<Actor>  createdActors = createdActors(actors);
        Movie createdMovie =  moviePersistencePort.save(movie);

        ArrayList<Actor> newActors = new ArrayList<>();
        newActors.addAll(existingActors);
        newActors.addAll(createdActors);

        mathcingPersestincePort.create(createdMovie,newActors);

        return createdMovie.getId();
    }


    public Movie retrieve(Long id) {

        Movie movie= movieCachePort.retrieveMovie(id);
        log.info("Movie is retrieving" + id);

        if(movie == null){
            log.info("Movie cache is updating" + id);
            movie =  moviePersistencePort.retrieve(id);

            movieCachePort.createMovie(movie);
        }

        return movie;

    }
    public Movie deleteById(Long id) {
        Movie movie = moviePersistencePort.deleteById(id);
        return movie;
    }



    public List<Movie> getAll() {

        return moviePersistencePort.findAll();
    }

    public void softDelete(Long id) {

        moviePersistencePort.softDelete(id);
    }

    private List<Actor> createdActors(List<Actor> actors){
        if(!CollectionUtils.isEmpty(actors)){
            return actorPersistencePort.createActorList(actors);

        }
        return new ArrayList<>();
    }

    private List<Actor> retrieveExistingActors(List<Long> actorIds){
        if(!CollectionUtils.isEmpty(actorIds)){
            // - actor id lerin db de kayıt edilmiş oldugunu kontrol et yoksa exception at
           List<Actor> retrievedActors =  actorPersistencePort.retrieve(actorIds);

            if(retrievedActors.size() < actorIds.size()){
               String detail = "Verilen actor id db' de bulunamamıştır. Beklenen : " + actorIds +  "Bulunan :" + retrievedActors;
                throw new MyProjectValidationException(ExceptionType.COLLECTION_SIZE_EXCEPTION,detail );
                // todo unit test for exception
            }
            return retrievedActors;
        }
        return new ArrayList<>();
    }
}
