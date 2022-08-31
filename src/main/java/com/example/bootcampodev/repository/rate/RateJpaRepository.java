package com.example.bootcampodev.repository.rate;

import com.example.bootcampodev.entity.RateEntity;
import com.example.bootcampodev.service.rate.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RateJpaRepository extends JpaRepository<RateEntity, Long> {
    List<RateEntity> findAllByMovieId(Long movieId);

    List<RateEntity> findAllByMemberId(Long memberId);
}
