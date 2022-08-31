package com.example.bootcampodev.repository.watch;

import com.example.bootcampodev.entity.WatchListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchListJpaRepository extends JpaRepository<WatchListEntity, Long> {
}
