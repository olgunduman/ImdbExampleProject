package com.example.bootcampodev.domain.movie;

import com.example.bootcampodev.adapter.jpa.movie.MovieEntity;
import com.example.bootcampodev.domain.actor.Actor;
import com.example.bootcampodev.domain.exception.MyProjectValidationException;
import com.example.bootcampodev.domain.port.ActorPersistencePort;
import com.example.bootcampodev.domain.port.MatchigPersestincePort;
import com.example.bootcampodev.domain.port.MovieCachePort;
import com.example.bootcampodev.domain.port.MoviePersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceImplTest {

    private MovieServiceImpl movieService;

    @Mock
    private MoviePersistencePort moviePersistencePort;
    @Mock

    private ActorPersistencePort actorPersistencePort;
    @Mock
    private MatchigPersestincePort mathcingPersestincePort;
    @Mock
    private MovieCachePort movieCachePort;


    @BeforeEach
    void setUp() {
        movieService = new MovieServiceImpl(
                moviePersistencePort,
                actorPersistencePort,
                mathcingPersestincePort,
                movieCachePort);
    }

    @Test
    void should_return_movie_when_movie_id_is_given() {
        //given

        Movie mockMovie = Movie.builder()
                .id(1L)
                .name("test-film")
                .releaseYear(2019)
                .build();

        //mock
        when(movieCachePort.retrieveMovie(1L)).thenReturn(mockMovie);

        //when
        Movie movie = movieService.retrieve(1L);

        //then
        assertEquals(movie.getId(), 1L);
        assertEquals(movie.getName(), "test-film");
        assertEquals(movie.getReleaseYear(), 2019);

        verifyNoInteractions(moviePersistencePort);
        verifyNoMoreInteractions(movieCachePort);
    }

    @Test
    void should_retrieve_movie_when_cache_empty() {
        //given

        Movie mockMovie = Movie.builder()
                .id(1L)
                .name("test-film")
                .releaseYear(2019)
                .build();

        //mock

        when(movieCachePort.retrieveMovie(any())).thenReturn(null);

        when(moviePersistencePort.retrieve(1L)).thenReturn(mockMovie);
        doNothing().when(movieCachePort).createMovie(mockMovie);

        //when
        Movie movie = movieService.retrieve(1L);

        //then
        assertEquals(movie.getId(), 1L);
        assertEquals(movie.getName(), "test-film");
        assertEquals(movie.getReleaseYear(), 2019);

    }

    @Test
    void should_create() {
        List<Actor> actors = new ArrayList<>();
        List<Long> actorIds = new ArrayList<>();

        Movie movie = Movie.builder().build();

        //mock
        Movie createdMovie = Movie.builder().id(3L).build();
        when(moviePersistencePort.save(any())).thenReturn(createdMovie);

        //when
        Long cretedMovieId = movieService.createMovie(movie, actors, actorIds);

        //then
        assertThat(cretedMovieId).isEqualTo(3L);


        verify(mathcingPersestincePort, timeout(1)).create(any(), any());
    }

    @Test
    void should_NOT_create_when_given_actors_not_exist_in_db() {

        List<Actor> actors = new ArrayList<>();
        List<Long> actorIds = List.of(1L, 2L);

        Movie movie = Movie.builder().build();

        //mock
        when(actorPersistencePort.retrieve(any())).thenReturn(List.of(Actor.builder().build()));

        //when
        Throwable throwable = catchThrowable(() -> movieService.createMovie(movie, actors, actorIds));

        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable).isInstanceOf(MyProjectValidationException.class);
        assertThat(throwable.getMessage()).isEqualTo("Liste boyutları uyuşmuyor");


    }

    @Test
    void should_getAll_movie() {
        //given
        Movie movie1 = Movie.builder().id(1L).name("test-movie 1").build();
        Movie movie2 = Movie.builder().id(2L).name("test-movie 2").build();
        Movie movie3 = Movie.builder().id(3L).name("test-movie 3").build();
        List<Movie> movieList = List.of(movie1, movie2, movie3);


        //mock
        when(moviePersistencePort.findAll()).thenReturn(movieList);

        //when
        List<Movie> movies = movieService.getAll();

        //then

        assertThat(movies.size()).isEqualTo(3);
    }

    @Test
    void should_delete_by_id_movie() {

        //given
        Movie movie = Movie.builder().id(1L).name("test-movie").build();

        //mock
        when(moviePersistencePort.deleteById(1L)).thenReturn(movie);

        //when
        Movie deletedMovie = movieService.deleteById(1L);

        //then
        assertThat(deletedMovie.getId()).isNotNull();
        assertEquals(deletedMovie.getId(), 1L);
        assertEquals(deletedMovie.getName(), "test-movie");

    }


}

