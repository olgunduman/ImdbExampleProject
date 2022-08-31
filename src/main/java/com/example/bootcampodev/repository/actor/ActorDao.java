package com.example.bootcampodev.repository.actor;

import com.example.bootcampodev.entity.ActorEntity;
import com.example.bootcampodev.service.actor.Actor;

import java.util.List;

public interface ActorDao {
    Long create(ActorEntity actorEntity);

    List<ActorEntity> createActorList(List<ActorEntity> actors);
    List<ActorEntity> retrieve(List<Long> actorIds);
}
