package com.example.bootcampodev.adapter.jpa.movie;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieJpaRepository extends JpaRepository<MovieEntity, Long> {
}
