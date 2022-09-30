package com.example.bootcampodev.adapter.jpa.movieWatch;

import com.example.bootcampodev.domain.movieWatch.MovieWatchList;
import com.example.bootcampodev.domain.port.MovieWatchListPersistencePort;
import org.springframework.stereotype.Service;

@Service
public class MovieWatchListJpaAdapter implements MovieWatchListPersistencePort {

    private final MovieWatchListJpaRepository movieWatchListJpaRepository;

    public MovieWatchListJpaAdapter(MovieWatchListJpaRepository movieWatchListJpaRepository) {
        this.movieWatchListJpaRepository = movieWatchListJpaRepository;
    }

    @Override
    public MovieWatchList create(MovieWatchList movieWatchList) {
        MovieWatchListEntity createdMovieWatchList = movieWatchListJpaRepository.save(MovieWatchListEntity.from(movieWatchList));
        return createdMovieWatchList.toModel();

    }
}
