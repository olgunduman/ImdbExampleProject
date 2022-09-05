package com.example.bootcampodev.repository.matching;

import com.example.bootcampodev.entity.MatchingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchingJpaRepository extends JpaRepository<MatchingEntity,Long> {

    List<MatchingEntity> findAllByMovie_Id(Long movieId);
}
