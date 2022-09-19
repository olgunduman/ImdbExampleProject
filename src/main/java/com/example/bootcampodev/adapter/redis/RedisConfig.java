package com.example.bootcampodev.adapter.redis;

import com.example.bootcampodev.domain.actor.Actor;
import com.example.bootcampodev.domain.movie.Movie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory(){
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName("localhost"); //todo Configuration properties
        redisStandaloneConfiguration.setPort(6379);

        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean("movieRedisTemplate")
    public RedisTemplate<String, MovieCache> movieRedisTemplate(LettuceConnectionFactory lettuceConnectionFactory){
        RedisTemplate<String,MovieCache> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(lettuceConnectionFactory);
        return template;
    }

    @Bean("actorRedisTemplate")
    public RedisTemplate<String, Actor> actorRedisTemplate(LettuceConnectionFactory lettuceConnectionFactory){
        RedisTemplate<String,Actor> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(lettuceConnectionFactory);
        return template;
    }

}
