package com.example.bootcampodev.adapter.rest.movie;

import com.example.bootcampodev.domain.actor.Actor;
import com.example.bootcampodev.domain.actor.ActorService;
import com.example.bootcampodev.domain.movie.Movie;
import com.example.bootcampodev.domain.movie.MovieService;
import com.example.bootcampodev.domain.movie.MovieServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieServiceImpl movieService;
    private final ActorService actorService;

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
        var actor = actorService.retrieveActors(id);
        return MovieResponse.convertFrom(movie,actor);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public MovieResponse delete(@PathVariable Long id){
        var movie = movieService.deleteById(id);
      return   MovieResponse.convertFrom(movie);
    }

    @DeleteMapping("/soft/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void softDelete(@PathVariable Long id){
        movieService.softDelete(id);

    }

}
