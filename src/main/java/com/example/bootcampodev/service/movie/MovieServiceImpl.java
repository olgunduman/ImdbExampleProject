package com.example.bootcampodev.service.movie;

import com.example.bootcampodev.dto.request.movie.MovieRequest;
import com.example.bootcampodev.entity.ActorEntity;
import com.example.bootcampodev.entity.MatchingEntity;
import com.example.bootcampodev.entity.MovieEntity;
import com.example.bootcampodev.repository.actor.ActorDao;
import com.example.bootcampodev.repository.matching.MatchingDao;
import com.example.bootcampodev.service.actor.Actor;
import com.example.bootcampodev.service.movie.Movie;
import com.example.bootcampodev.repository.movie.MovieDao;
import com.example.bootcampodev.service.movie.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

        private final MovieDao movieDao;
        private final ActorDao actorDao;

        private final MatchingDao mathcingDao;
        private final RedisTemplate<String, Movie> movieRedisTemplate;



    @Override
    public Long createMovie(Movie movie, List<Actor> actors, List<Long> actorIds) {
        List<ActorEntity> createdActors = createdActors(actors);
        List<ActorEntity> existingActors = retrieveExistingActors(actorIds);
        MovieEntity createdMovie = movieDao.save(movie.convertToMovieEntity());

        // movie actor eşleşmesi yapılacak matching tablosuna kayıt atılacak;

      Stream.of(createdActors, existingActors).flatMap(List::stream).forEach(actorEntity -> {
          List<MatchingEntity> matchingEntities = Collections.singletonList(MatchingEntity.builder().actor(actorEntity).movie(createdMovie).build());
            mathcingDao.create(matchingEntities);
        });


        return createdMovie.getId();
    }

    @Override
    public Movie retrieve(Long id) {
        Movie movie = movieRedisTemplate.opsForValue().get("movie :" + id);
        log.info("Movie is retrieving" + id);
        if(movie == null){
            log.info("Movie cache is updating" + id);
            MovieEntity entity =  movieDao.retrieve(id);
            movie = Movie.convertFrom(entity);
            movieRedisTemplate.opsForValue().set("movie : "+id, movie, Duration.ofHours(1));

        }
       return movie;
    }

    @Override
    public Movie deleteById(Long id) {
        MovieEntity movieEntity = movieDao.deleteById(id);
        return Movie.convertFrom(movieEntity);
    }



    @Override
    public List<Movie> getAll()   {

        return movieDao.findAll().stream()
                .map(Movie::convertFrom)
                .collect(Collectors.toList());
    }

    @Override
    public void softDelete(Long id) {
        movieDao.softDelete(id);
    }

    private List<ActorEntity> createdActors(List<Actor> actors){
        if(!CollectionUtils.isEmpty(actors)){
            //yeni gelen actorleri db ye kaydet
            var actorEntites = actors.stream()
                    .map(Actor::convertToActorEntity).collect(Collectors.toList());
            return actorDao.createActorList(actorEntites);

        }
        return new ArrayList<>();
    }

    private List<ActorEntity> retrieveExistingActors(List<Long> actorIds){
        if(!CollectionUtils.isEmpty(actorIds)){
            // - actor id lerin db de kayıt edilmiş oldugunu kontrol et yoksa exception at
           List<ActorEntity> retrievedActors =  actorDao.retrieve(actorIds);

            if(retrievedActors.size() < actorIds.size()){
                throw new RuntimeException("verilen actor id db de bulunamadı");
            }
            return retrievedActors;
        }
        return new ArrayList<>();
    }
}
