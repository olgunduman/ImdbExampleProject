package com.example.bootcampodev.service.actor;

import com.example.bootcampodev.entity.ActorEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class Actor {

    private Long id;

    private String name;

    private LocalDateTime birthDate;

    public ActorEntity convertToActorEntity() {
        ActorEntity actorEntity = new ActorEntity();
        actorEntity.setName(name);
        actorEntity.setBirthDate(birthDate);
        return actorEntity;
    }
}
