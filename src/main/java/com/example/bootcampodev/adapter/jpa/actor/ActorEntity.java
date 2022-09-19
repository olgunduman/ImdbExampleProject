package com.example.bootcampodev.adapter.jpa.actor;

import com.example.bootcampodev.adapter.jpa.matching.MatchingEntity;
import com.example.bootcampodev.domain.actor.Actor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Table(name = "actor")
@Entity(name = "actor")
public class ActorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime birthDate;

    @OneToMany(mappedBy = "actor",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<MatchingEntity> matchings;

    public static ActorEntity from(Actor actor) {
        ActorEntity actorEntity = new ActorEntity();
        actorEntity.setId(actor.getId());
        actorEntity.setName(actor.getName());
        actorEntity.setBirthDate(actor.getBirthDate());
        return actorEntity;
    }

    public  Actor toModel(){
        return Actor.builder()
                .id(this.id)
                .name(this.name)
                .birthDate(this.birthDate)
                .build();
    }
}
