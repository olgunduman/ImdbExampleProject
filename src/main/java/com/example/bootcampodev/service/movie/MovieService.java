package com.example.bootcampodev.service.movie;

import com.example.bootcampodev.dto.request.movie.MovieRequest;
import com.example.bootcampodev.dto.response.movie.MovieResponse;
import com.example.bootcampodev.service.actor.Actor;
import com.example.bootcampodev.service.movie.Movie;
import org.springframework.cache.annotation.CacheEvict;

import java.util.List;


public interface MovieService {
    Long createMovie(Movie movie, List<Actor> actors, List<Long> actorIds);

    Movie retrieve(Long id);

    Movie deleteById(Long id);


    List<Movie> getAll();
}
