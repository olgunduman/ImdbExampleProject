package com.example.bootcampodev.adapter.jpa.rate;

import com.example.bootcampodev.adapter.jpa.common.BaseEntity;
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
public class RateEntity extends BaseEntity {


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
        RateEntity rateEntity = new RateEntity();
        rateEntity.id = rate.getId();
        rateEntity.point = rate.getPoint();
        rateEntity.member = MemberEntity.fromMemberId(rate.getMemberId());
        rateEntity.movie = MovieEntity.fromMovieId(rate.getMovieId());
        return rateEntity;


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
