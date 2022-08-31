package com.example.bootcampodev.repository.movie;

import com.example.bootcampodev.entity.ActorEntity;
import com.example.bootcampodev.entity.MatchingEntity;
import com.example.bootcampodev.entity.MovieEntity;
import com.example.bootcampodev.repository.actor.ActorJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieDaoImpl implements MovieDao {
    private final MovieJpaRepository movieJpaRepository;
    private final ActorJpaRepository actorJpaRepository;



    @Override
    public MovieEntity save(MovieEntity movieEntity) {
        return movieJpaRepository.save(movieEntity);
    }

    @Override
    public MovieEntity retrieve(Long movieId) {

        Optional<MovieEntity> movieEntity = movieJpaRepository.findById(movieId);
        if (!movieEntity.isPresent())
            throw new NotFoundException("Movie Id bulunamadı");

        return movieEntity.get();
    }

    @Override
    public MovieEntity deleteById(Long id) {
        var movie = movieJpaRepository.findById(id).orElseThrow(() -> new NotFoundException("Movie Id Bulunamadı"));
        movieJpaRepository.delete(movie);
        return movie;
    }

    @Override
    public List<MovieEntity> findAll() {
        return movieJpaRepository.findAll();
    }

    @Override

    public List<MovieEntity> retrieveByActorId(Long actorId) {
        Optional<ActorEntity> actorEntityOptional =   actorJpaRepository.findById(actorId);
        if(actorEntityOptional.isPresent())
            return   actorEntityOptional.get()
                    .getMatchings()
                    .stream()
                    .map(MatchingEntity::getMovieEntity)
                    .collect(Collectors.toList());

        else
            throw new RuntimeException("actor ıd bulunamadı");
    }
}