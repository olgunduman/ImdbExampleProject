package com.example.bootcampodev.controller;

import com.example.bootcampodev.dto.request.actor.ActorCreateRequest;
import com.example.bootcampodev.dto.response.actor.ActorCreateResponse;
import com.example.bootcampodev.dto.response.movie.MovieResponse;
import com.example.bootcampodev.service.actor.ActorService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{actorId}/movies")
    public List<MovieResponse> retrieveMovie(@PathVariable Long actorId){
        return actorService.retrieveMovies(actorId)
                .stream().map(MovieResponse::convertFrom)
                .collect(Collectors.toList());
    }


}
