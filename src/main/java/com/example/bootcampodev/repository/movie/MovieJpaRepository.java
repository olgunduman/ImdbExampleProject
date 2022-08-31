package com.example.bootcampodev.repository.movie;

import com.example.bootcampodev.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieJpaRepository extends JpaRepository<MovieEntity, Long> {
}
