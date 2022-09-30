package com.example.bootcampodev.adapter.jpa.matching;

import com.example.bootcampodev.adapter.jpa.actor.ActorEntity;
import com.example.bootcampodev.adapter.jpa.movie.MovieEntity;
import com.example.bootcampodev.domain.actor.Actor;
import com.example.bootcampodev.domain.movie.Movie;
import com.example.bootcampodev.domain.port.MatchigPersestincePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchingJpaAdapter implements MatchigPersestincePort {
    private final MatchingJpaRepository matchingJpaRepository;


    @Override
    public void create(Movie movie, List<Actor> actors) {


        List<MatchingEntity> matchingEntities =  actors.stream()
                .map(actorEntity -> {
                    MatchingEntity matchingEntity = new MatchingEntity();
                    matchingEntity.setMovie(MovieEntity.from(movie));
                    matchingEntity.setActor(ActorEntity.from(actorEntity));

                    return matchingEntity;
                }).collect(Collectors.toList());


        matchingJpaRepository.saveAll(matchingEntities);

    }

    @Override
    public List<Actor> retrieveActors(Long movieId) {
        List<ActorEntity> actorEntities = matchingJpaRepository.findAllByMovie_Id(movieId)
                .stream().map(matchingEntity -> matchingEntity.getActor())
                .collect(Collectors.toList());

        return actorEntities.stream().map(ActorEntity::toModel).collect(Collectors.toList());
    }
}
