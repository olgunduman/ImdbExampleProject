package com.example.bootcampodev.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "watchList")
public class WatchListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private MemberEntity member;

    @OneToMany(mappedBy = "watchList")
    private List<MovieWatchListEntity> movieWatchListEntity;

}
