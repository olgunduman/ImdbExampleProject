package com.example.bootcampodev.repository.matching;

import com.example.bootcampodev.entity.ActorEntity;
import com.example.bootcampodev.entity.MatchingEntity;
import com.example.bootcampodev.service.actor.Actor;

import java.util.List;

public interface MatchingDao {
    void create(List<MatchingEntity> matchingEntities);

    List<ActorEntity> retrieveActors(Long movieId);
}
