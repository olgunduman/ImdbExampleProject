package com.example.bootcampodev.controller;

import com.example.bootcampodev.dto.request.movie.MovieRequest;
import com.example.bootcampodev.dto.response.movie.MovieCreateResponse;
import com.example.bootcampodev.dto.response.movie.MovieResponse;
import com.example.bootcampodev.entity.MovieEntity;
import com.example.bootcampodev.service.actor.Actor;
import com.example.bootcampodev.service.movie.Movie;
import com.example.bootcampodev.service.movie.MovieService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public MovieCreateResponse movieCreate(@RequestBody MovieRequest movieRequest){
        Movie movie = movieRequest.convertFromMovie();
        List<Actor> actors = movieRequest.convertToActors();
        var createdMovieId = movieService.createMovie(movie,actors,movieRequest.getActorIds());
        return MovieCreateResponse.convertToMovieResponse(createdMovieId);

    }



    @GetMapping("/getAll")
    public List<MovieResponse> getAll() throws InterruptedException {

         List<Movie> movies = movieService.getAll();
         return MovieResponse.convertFromListMovie(movies);
    }

    @GetMapping("/{id}")
    public MovieResponse retrieve(@PathVariable Long id){
        Movie movie = movieService.retrieve(id);
        return MovieResponse.convertFrom(movie);
    }

    @DeleteMapping("/{id}")
    public MovieResponse delete(@PathVariable Long id){

        var movie = movieService.deleteById(id);
      return   MovieResponse.convertFrom(movie);
    }

}
