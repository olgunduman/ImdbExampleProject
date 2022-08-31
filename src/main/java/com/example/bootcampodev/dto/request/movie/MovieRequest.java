package com.example.bootcampodev.dto.request.movie;

import com.example.bootcampodev.dto.request.actor.ActorCreateRequest;
import com.example.bootcampodev.entity.Genre;
import com.example.bootcampodev.entity.MovieEntity;
import com.example.bootcampodev.service.actor.Actor;
import com.example.bootcampodev.service.movie.Movie;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter

public class MovieRequest {


    @NotNull
    private String name;

    @NotNull
    private Genre genre;

    @NotNull
    private int releaseYear;

    @NotNull
    private String director;

    @Valid
    private List<ActorCreateRequest> actors;

    private List<Long> actorIds;


    public Movie convertFromMovie(){
       return Movie.builder()
                .name(this.name)
                .genre(this.genre)
                .releaseYear(this.releaseYear)
                .director(this.director)
                .build();
    }


    public List<Actor> convertToActors() {
        if(CollectionUtils.isEmpty(actors))
            return new ArrayList<>();

        return actors.stream()
                .map(ActorCreateRequest::convertToActor)
                .collect(Collectors.toList());
    }
}
