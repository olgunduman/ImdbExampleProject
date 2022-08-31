package com.example.bootcampodev.repository.actor;

import com.example.bootcampodev.entity.ActorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActorJpaRepository extends JpaRepository<ActorEntity,Long> {
    List<ActorEntity> findAllByIdIn(List<Long> actorIds);
}
