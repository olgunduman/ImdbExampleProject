package com.example.bootcampodev.adapter.rest.actor;

import com.example.bootcampodev.adapter.rest.movie.MovieResponse;
import com.example.bootcampodev.domain.actor.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/actors")
@RequiredArgsConstructor
public class ActorController {

    private final ActorService actorService;


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ActorCreateResponse create(@RequestBody ActorCreateRequest actorCreateRequest){

        Long createdActorId = actorService.create(actorCreateRequest.convertToActor());
        return ActorCreateResponse.builder().id(createdActorId).build();

    }

    @GetMapping(value = "/{actorId}/movies", consumes = MediaType.ALL_VALUE)
    public List<MovieResponse> retrieveMovie(@PathVariable Long actorId){
        return actorService.retrieveMovies(actorId)
                .stream().map(MovieResponse::convertFrom)
                .collect(Collectors.toList());
    }


}
