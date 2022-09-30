package com.example.bootcampodev.adapter.jpa.rate;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RateJpaRepository extends JpaRepository<RateEntity, Long> {
    List<RateEntity> findAllByMovieId(Long movieId);

    List<RateEntity> findAllByMemberId(Long memberId);
}
