package com.example.bootcampodev.adapter.redis;

import com.example.bootcampodev.domain.movie.Movie;
import com.example.bootcampodev.domain.port.MovieCachePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;



@Service
@RequiredArgsConstructor
public class RedisAdapter implements MovieCachePort {
    private final RedisTemplate<String, MovieCache> movieRedisTemplate;
    @Override
    public Movie retrieveMovie(Long movieId){// todo optional
        MovieCache movieCache = movieRedisTemplate.opsForValue().get("patika:movie: " + movieId);

        if(movieCache == null)
            return null;

        return movieCache.toModel();
    }
    @Override
    public void createMovie(Movie movie){
        MovieCache movieCache = MovieCache.from(movie);
        movieRedisTemplate.opsForValue().set("patika:movie: "+ movie.getId(), movieCache, Duration.ofSeconds(30));

    }
}
