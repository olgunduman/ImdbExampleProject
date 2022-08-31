package com.example.bootcampodev.repository.movieWatch;

import com.example.bootcampodev.entity.MovieWatchListEntity;
import org.springframework.stereotype.Service;

@Service
public class MovieWatchListDaoImpl implements MovieWatchListDao {

    private final MovieWatchListJpaRepository movieWatchListJpaRepository;

    public MovieWatchListDaoImpl(MovieWatchListJpaRepository movieWatchListJpaRepository) {
        this.movieWatchListJpaRepository = movieWatchListJpaRepository;
    }

    @Override
    public MovieWatchListEntity create(MovieWatchListEntity movieWatchListEntity) {
        return movieWatchListJpaRepository.save(movieWatchListEntity);

    }
}
