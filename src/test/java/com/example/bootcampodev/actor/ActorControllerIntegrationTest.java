package com.example.bootcampodev.actor;

import com.example.bootcampodev.base.BaseIntegrationTest;
import com.example.bootcampodev.dto.request.actor.ActorCreateRequest;
import com.example.bootcampodev.dto.response.actor.ActorCreateResponse;
import com.example.bootcampodev.dto.response.movie.MovieResponse;
import com.example.bootcampodev.entity.ActorEntity;
import com.example.bootcampodev.repository.actor.ActorJpaRepository;
import com.example.bootcampodev.service.movie.Movie;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ActorControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private  ActorJpaRepository actorJpaRepository;

    @Test
    @Sql(scripts = "/cleanup.sql",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void should_create_actor(){

        //given
        ActorCreateRequest request = new ActorCreateRequest();
        request.setName("test actor");
        request.setBirthDate(LocalDateTime.of(1985,1,12,13,0,0));

        //when
        ResponseEntity<ActorCreateResponse> response = testRestTemplate.postForEntity("/actors/create",request,ActorCreateResponse.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull();

        //validate actor
        Optional<ActorEntity> actorEntity = actorJpaRepository.findById(response.getBody().getId());
        assertThat(actorEntity.isPresent());
        assertThat(actorEntity.get()).extracting("name","birthDate")
                .containsExactly("test actor", LocalDateTime.of(1985,1,12,13,0,0));


    }

    @Test
    @Sql(scripts = "/cleanup.sql",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Sql(scripts = "/movie-create.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void should_retrieve_actor_movies() {

        //when
       ResponseEntity<MovieResponse[]> response = testRestTemplate.getForEntity("/actors/2001/movies",MovieResponse[].class);
        //
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(Arrays.stream(response.getBody()).collect(Collectors.toList())).hasSize(2)
                .extracting("id")
                .containsExactly(1001L,1002L);
    }




}
