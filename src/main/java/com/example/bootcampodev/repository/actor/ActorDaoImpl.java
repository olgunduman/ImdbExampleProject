package com.example.bootcampodev.repository.actor;

import com.example.bootcampodev.entity.ActorEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorDaoImpl implements ActorDao{

    private final ActorJpaRepository actorJpaRepository;

    @Override
    public Long create(ActorEntity actorEntity) {
        var actorCreate =  actorJpaRepository.save(actorEntity);
        return actorCreate.getId();
    }

    @Override
    public List<ActorEntity> createActorList(List<ActorEntity> actors) {
        return actorJpaRepository.saveAll(actors);
    }

    @Override
    public List<ActorEntity> retrieve(List<Long> actorIds) {
        return actorJpaRepository.findAllByIdIn(actorIds);
    }
}
