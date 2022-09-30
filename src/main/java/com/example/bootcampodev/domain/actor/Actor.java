package com.example.bootcampodev.domain.actor;

import com.example.bootcampodev.adapter.jpa.actor.ActorEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Actor {

    private Long id;

    private String name;

    private LocalDateTime birthDate;







}
