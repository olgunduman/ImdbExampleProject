package com.example.bootcampodev.repository.movie;

import com.example.bootcampodev.dto.request.movie.MovieRequest;
import com.example.bootcampodev.dto.response.movie.MovieResponse;
import com.example.bootcampodev.entity.MovieEntity;
import com.example.bootcampodev.service.movie.Movie;

import java.util.List;

public interface MovieDao {
    MovieEntity save(MovieEntity movieEntity);

    MovieEntity retrieve(Long movieId);

    MovieEntity deleteById(Long id);

    List<MovieEntity> findAll();

    List<MovieEntity> retrieveByActorId(Long actorId);
}
