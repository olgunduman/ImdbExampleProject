package com.example.bootcampodev.repository.movieWatch;

import com.example.bootcampodev.entity.MovieWatchListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieWatchListJpaRepository extends JpaRepository<MovieWatchListEntity,Long> {
}
