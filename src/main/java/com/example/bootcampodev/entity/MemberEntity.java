package com.example.bootcampodev.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "member")
@Table(name = "member")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private Integer birthYear;

    @Column(nullable = false)
    private String phone;

    @OneToMany(mappedBy = "member",fetch = FetchType.EAGER)
    private List<RateEntity> rates;

    @OneToMany(mappedBy = "member")
    private List<WatchListEntity> watchListMovieEntities;

}
