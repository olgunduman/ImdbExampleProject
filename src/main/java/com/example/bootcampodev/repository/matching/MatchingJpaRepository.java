package com.example.bootcampodev.repository.matching;

import com.example.bootcampodev.entity.MatchingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchingJpaRepository extends JpaRepository<MatchingEntity,Long> {
}
