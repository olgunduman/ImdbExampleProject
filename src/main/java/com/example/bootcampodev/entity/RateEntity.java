package com.example.bootcampodev.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rate")
@Entity(name = "rateEntity")
public class RateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)

    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(nullable = false)
    private MemberEntity member;

    @ManyToOne
    @JoinColumn(nullable = false)
    private MovieEntity movie;

    @Max(5)
    @Min(1)
    @NotNull
    private Integer point;


}
