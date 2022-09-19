package com.example.bootcampodev.adapter.jpa.actor;

import com.example.bootcampodev.domain.actor.Actor;
import com.example.bootcampodev.domain.port.ActorPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActorJpaAdapter implements ActorPersistencePort {

    private final ActorJpaRepository actorJpaRepository;

    @Override
    public Long create(Actor actor) {
        var actorCreate =  actorJpaRepository.save(ActorEntity.from(actor));
        return actorCreate.getId();
    }

    @Override
    public List<Actor> createActorList(List<Actor> actors) {
        List<ActorEntity> actorEntities = actors.stream().map(
                ActorEntity::from
        ).collect(Collectors.toList());

      return actorJpaRepository.saveAll(actorEntities)
                 .stream()
                 .map(ActorEntity::toModel).collect(Collectors.toList());
    }

    @Override
    public List<Actor> retrieve(List<Long> actorIds) {
        return actorJpaRepository.findAllByIdIn(actorIds)
                .stream()
                .map(actorEntity -> actorEntity.toModel())
                .collect(Collectors.toList());

    }
}
