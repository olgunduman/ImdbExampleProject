package com.example.bootcampodev.movie;

import com.example.bootcampodev.base.BaseIntegrationTest;
import com.example.bootcampodev.dto.request.actor.ActorCreateRequest;
import com.example.bootcampodev.dto.request.movie.MovieRequest;
import com.example.bootcampodev.dto.response.movie.MovieCreateResponse;
import com.example.bootcampodev.dto.response.movie.MovieResponse;
import com.example.bootcampodev.entity.MovieEntity;
import com.example.bootcampodev.entity.enums.Genre;
import com.example.bootcampodev.repository.actor.ActorJpaRepository;
import com.example.bootcampodev.repository.matching.MatchingJpaRepository;
import com.example.bootcampodev.repository.movie.MovieJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    MovieJpaRepository movieJpaRepository;

    @Autowired
    ActorJpaRepository actorJpaRepository;

    @Autowired
    MatchingJpaRepository matchingJpaRepository;

    @Test
    @Sql(scripts = "/actor-create.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void should_create_movie_with_actors_in_db_and_new_actors() {

        //given
        MovieRequest request = new MovieRequest();
        request.setName("movie-name");
        request.setGenre(Genre.ACTION);
        request.setReleaseYear(2015);
        request.setDirector("movie-director");

        ActorCreateRequest actorCreateRequest1 = new ActorCreateRequest();
        actorCreateRequest1.setName("actor-name 1");
        actorCreateRequest1.setBirthDate(LocalDateTime.of(1980, 1, 1, 5, 0));

        ActorCreateRequest actorCreateRequest2 = new ActorCreateRequest();
        actorCreateRequest2.setName("actor-name 2");
        actorCreateRequest2.setBirthDate(LocalDateTime.of(1989, 7, 3, 1, 0));


        request.setActors(List.of(actorCreateRequest1, actorCreateRequest2));

        request.setActorIds(List.of(1001L, 1002L, 1003L));

        //when

        ResponseEntity<MovieCreateResponse> response = testRestTemplate.postForEntity("/movie/create", request, MovieCreateResponse.class);


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
        assertThat(createdMovie.get().getCreatedDate()).isNotNull();

        //validate actor
        var actors = actorJpaRepository.findAll();
        assertThat(actors).hasSize(5)
                .extracting("name", "birthDate")
                .contains(
                        tuple("actor-name 1", LocalDateTime.of(1980, 1, 1, 5, 0)),
                        tuple("actor-name 2", LocalDateTime.of(1989, 7, 3, 1, 0))
                );

        // validate matching
        var matchings = matchingJpaRepository.findAll();
        assertThat(matchings).hasSize(5);

    }

    @Test
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Sql(scripts = "/movie-create.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void should_delete_movie_test() {

        //given
        Long movieId = 1001L;
        var url = "/movie/delete/" + movieId;
        //when
        ResponseEntity<MovieResponse> response = testRestTemplate.postForEntity(url, movieId, MovieResponse.class);


        //then
        //assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isNotNull();

        //validate movie
        var createdMovie = movieJpaRepository.findById(movieId);
        assertThat(createdMovie.isPresent());
        assertThat(createdMovie.get().getName()).isEqualTo("movie-name 1001");
        assertThat(createdMovie.get().getGenre()).isEqualTo(Genre.ACTION);
        assertThat(createdMovie.get().getReleaseYear()).isEqualTo(2015);
        assertThat(createdMovie.get().getDirector()).isEqualTo("movie-director 1001");


    }

    @Test
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Sql(scripts = "/movie-create.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void should_movie_get_all() {


        //when
        ResponseEntity<List<MovieResponse>> response = testRestTemplate.exchange("/movie/getAll", HttpMethod.GET, HttpEntity.EMPTY,
                new ParameterizedTypeReference<List<MovieResponse>>() {
                });
        //then
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).hasSize(2);

    }

    @Test
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Sql(scripts = "/movie-create.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void should_retrieve_movie_test() {
        //when
        ResponseEntity<MovieResponse> response = testRestTemplate.getForEntity("/movie/1001", MovieResponse.class);

        //then
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).extracting("name", "genre", "releaseYear", "director")
                .containsExactly("movie-name 1001", Genre.ACTION, 2015, "movie-director 1001");

        assertThat(response.getBody().getActors())
                .hasSize(2)
                .extracting("id", "name", "birthDate")
                .containsExactly(
                        tuple(2001L, "test actor 2001", LocalDateTime.of(2001, 1, 12, 11, 0, 0)),
                        tuple(2003L, "test actor 2003", LocalDateTime.of(2003, 1, 12, 13, 0, 0))
                );
    }

    @Test
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Sql(scripts = "/movie-create.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void should_soft_delete_movie_test() {
        //given
        Optional<MovieEntity> optionalMovie = movieJpaRepository.findById(1001L);
        assertThat(optionalMovie).isPresent();

        //when
        testRestTemplate.delete("/movie/soft/delete/1001");

        //then
        optionalMovie = movieJpaRepository.findById(1001L);
        assertThat(optionalMovie).isEmpty();

    }

}
