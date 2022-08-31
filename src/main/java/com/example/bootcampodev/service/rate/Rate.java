package com.example.bootcampodev.service.rate;

import com.example.bootcampodev.entity.MemberEntity;
import com.example.bootcampodev.entity.MovieEntity;
import com.example.bootcampodev.entity.RateEntity;
import com.example.bootcampodev.service.member.Member;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@EqualsAndHashCode
public class Rate {
     private LocalDateTime createdDate;
    private Long memberId;
    private Long movieId;
    private Integer point;

    public RateEntity convertToRateEntity(MovieEntity movieEntity, MemberEntity memberEntity) {
        RateEntity rateEntity = new RateEntity();
        rateEntity.setCreatedDate(LocalDateTime.now());
        rateEntity.setMember(memberEntity);
        rateEntity.setMovie(movieEntity);
        rateEntity.setPoint(this.getPoint());

        return rateEntity;
    }

    public static Rate convertfromRateEntity(RateEntity rateEntity) {
        return Rate.builder()
                .memberId(rateEntity.getMember().getId())
                .point(rateEntity.getPoint())
                .createdDate(LocalDateTime.now())
                .movieId(rateEntity.getMovie().getId())
                .build();
    }

}
