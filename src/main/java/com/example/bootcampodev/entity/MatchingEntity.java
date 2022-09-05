package com.example.bootcampodev.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Table(name = "matching")
@Entity(name = "matching")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatchingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private MovieEntity movie;

    @ManyToOne
    private ActorEntity actor;
}
