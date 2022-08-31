package com.example.bootcampodev;

import com.example.bootcampodev.dto.request.actor.ActorCreateRequest;
import com.example.bootcampodev.dto.request.movie.MovieRequest;
import com.example.bootcampodev.dto.response.movie.MovieCreateResponse;
import com.example.bootcampodev.entity.Genre;
import com.example.bootcampodev.repository.actor.ActorJpaRepository;
import com.example.bootcampodev.repository.matching.MatchingJpaRepository;
import com.example.bootcampodev.repository.movie.MovieJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
public class MovieControllerIntegrationTest extends BaseIntegrationTest{

    @Autowired
    MovieJpaRepository movieJpaRepository;

    @Autowired
    ActorJpaRepository actorJpaRepository;

    @Autowired
    MatchingJpaRepository matchingJpaRepository;

    @Test
    @Sql(scripts = "/actor-create.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts="/cleanup.sql" , executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void should_create_movie_with_actors_in_db_and_new_actors(){

        //given
        MovieRequest request = new MovieRequest();
        request.setName("movie-name");
        request.setGenre(Genre.ACTION);
        request.setReleaseYear(2015);
        request.setDirector("movie-director");

        ActorCreateRequest actorCreateRequest1 = new ActorCreateRequest();
        actorCreateRequest1.setName("actor-name 1");
        actorCreateRequest1.setBirthDate(LocalDateTime.of(1980,1,1,5,0));

        ActorCreateRequest actorCreateRequest2 = new ActorCreateRequest();
        actorCreateRequest2.setName("actor-name 2");
        actorCreateRequest2.setBirthDate(LocalDateTime.of(1989,7,3,1,0));


        request.setActors(List.of(actorCreateRequest1,actorCreateRequest2));

        request.setActorIds(List.of(1001L, 1002L, 1003L));

        //when

        ResponseEntity<MovieCreateResponse> response = testRestTemplate.postForEntity("/movie/create",request,MovieCreateResponse.class);


        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getId()).isNotNull();

        //validate movie
        var createdMovie = movieJpaRepository.findById(response.getBody().getId());
        assertThat(createdMovie.isPresent());
        assertThat(createdMovie.get().getName()).isEqualTo("movie-name");
        assertThat(createdMovie.get().getGenre()).isEqualTo(Genre.ACTION);
        assertThat(createdMovie.get().getReleaseYear()).isEqualTo(2015);
        assertThat(createdMovie.get().getDirector()).isEqualTo("movie-director");


        //validate actor
        var actors = actorJpaRepository.findAll();
        assertThat(actors).hasSize(5)
                .extracting("name","birthDate")
                .contains(
                        tuple("actor-name 1",LocalDateTime.of(1980,1,1,5,0)),
                        tuple("actor-name 2",LocalDateTime.of(1989,7,3,1,0))
                );

        // validate matching
        var matchings = matchingJpaRepository.findAll();
        assertThat(matchings).hasSize(5);




    }
}
