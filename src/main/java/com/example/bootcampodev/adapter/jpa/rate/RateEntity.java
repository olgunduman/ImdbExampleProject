package com.example.bootcampodev.adapter.jpa.rate;

import com.example.bootcampodev.adapter.jpa.member.MemberEntity;
import com.example.bootcampodev.adapter.jpa.movie.MovieEntity;
import com.example.bootcampodev.domain.rate.Rate;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@EntityListeners(AuditingEntityListener.class)
public class RateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)

    @CreatedDate
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


    public static RateEntity from(Rate rate) {
        return RateEntity.builder()
                .id(rate.getId())
                .point(rate.getPoint())
                .member(MemberEntity.fromMemberId(rate.getMemberId()))
                .movie(MovieEntity.fromMovieId(rate.getMovieId()))
                .build();

    }


    public Rate toModel() {
        return Rate.builder()
                .id(id)
                .createdDate(createdDate)
                .memberId(member.getId())
                .movieId(movie.getId())
                .point(point)
                .build();
    }
}
