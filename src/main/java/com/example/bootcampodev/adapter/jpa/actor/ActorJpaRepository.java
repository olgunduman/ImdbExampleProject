package com.example.bootcampodev.adapter.jpa.actor;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActorJpaRepository extends JpaRepository<ActorEntity,Long> {
    List<ActorEntity> findAllByIdIn(List<Long> actorIds);
}
