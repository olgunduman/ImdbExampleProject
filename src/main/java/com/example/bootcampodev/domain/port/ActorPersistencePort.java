package com.example.bootcampodev.domain.port;
import com.example.bootcampodev.domain.actor.Actor;

import java.util.List;

public interface ActorPersistencePort {
    Long create(Actor actor);

    List<Actor> createActorList(List<Actor> actors);
    List<Actor> retrieve(List<Long> actorIds);
}
