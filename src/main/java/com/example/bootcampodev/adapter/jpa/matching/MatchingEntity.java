package com.example.bootcampodev.adapter.jpa.matching;

import com.example.bootcampodev.adapter.jpa.actor.ActorEntity;
import com.example.bootcampodev.adapter.jpa.common.BaseEntity;
import com.example.bootcampodev.adapter.jpa.movie.MovieEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "matching")
@Entity(name = "matching")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatchingEntity extends BaseEntity {


    @ManyToOne(cascade = CascadeType.MERGE)
    private MovieEntity movie;

    @ManyToOne(cascade = CascadeType.MERGE)
    private ActorEntity actor;
}
