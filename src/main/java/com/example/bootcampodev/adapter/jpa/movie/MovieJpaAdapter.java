package com.example.bootcampodev.adapter.jpa.movie;

import com.example.bootcampodev.adapter.jpa.actor.ActorEntity;
import com.example.bootcampodev.adapter.jpa.matching.MatchingEntity;
import com.example.bootcampodev.adapter.jpa.enums.Status;
import com.example.bootcampodev.adapter.jpa.actor.ActorJpaRepository;
import com.example.bootcampodev.domain.exception.DataNotFoundException;
import com.example.bootcampodev.domain.exception.ExceptionType;
import com.example.bootcampodev.domain.movie.Movie;
import com.example.bootcampodev.domain.port.MoviePersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieJpaAdapter implements MoviePersistencePort {
    private final MovieJpaRepository movieJpaRepository;
    private final ActorJpaRepository actorJpaRepository;



    @Override
    public Movie save(Movie movie) {

        MovieEntity movieEntity = MovieEntity.from(movie);
       return movieJpaRepository.save(movieEntity).toModel();
    }

    @Override
    public Movie retrieve(Long movieId) {

        Optional<MovieEntity> movieEntity = movieJpaRepository.findById(movieId);
        if (!movieEntity.isPresent())
            throw new DataNotFoundException(ExceptionType.MOVIE_NOT_FOUND_EXCEPTION,"movie id :" + movieId);

        return movieEntity.get().toModel();
    }

    @Override
    public Movie deleteById(Long id) {
        var movie = movieJpaRepository.findById(id).orElseThrow(() -> new DataNotFoundException(ExceptionType.MOVIE_NOT_FOUND_EXCEPTION));
        movieJpaRepository.delete(movie);
        return movie.toModel();
    }

    @Override
    public List<Movie> findAll() {
        List<MovieEntity> findAllMovie = movieJpaRepository.findAll();
       return findAllMovie.stream().map(MovieEntity::toModel).collect(Collectors.toList());
    }

    @Override

    public List<Movie> retrieveByActorId(Long actorId) {
        Optional<ActorEntity> actorEntityOptional =   actorJpaRepository.findById(actorId);
        if(actorEntityOptional.isPresent())
            return  actorEntityOptional.get()
                    .getMatchings()
                    .stream()
                    .map(MatchingEntity::getMovie)
                    .map(MovieEntity::toModel)
                    .collect(Collectors.toList());
        else
            throw new DataNotFoundException(ExceptionType.ACTOR_NOT_FOUND_EXCEPTION, "actor id :" + actorId);
    }

    @Override
    public void softDelete(Long id) {
        Optional<MovieEntity> optionalMovieEntity = movieJpaRepository.findById(id);
        if(optionalMovieEntity.isPresent())
        {
            MovieEntity movieEntity = optionalMovieEntity.get();
            movieEntity.setStatus(Status.DELETED);
            movieJpaRepository.save(movieEntity);
        }

    }
}