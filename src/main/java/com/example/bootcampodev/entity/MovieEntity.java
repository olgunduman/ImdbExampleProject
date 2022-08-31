package com.example.bootcampodev.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Table(name = "movie")
@Entity(name = "movieEntity")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @NotNull
    private int releaseYear;

    private String director;

    @OneToMany(mappedBy = "movie",fetch = FetchType.EAGER)
    private List<RateEntity> rates;
}
