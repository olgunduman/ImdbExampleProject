package com.example.bootcampodev.domain.rate;

import com.example.bootcampodev.adapter.jpa.member.MemberEntity;
import com.example.bootcampodev.adapter.jpa.movie.MovieEntity;
import com.example.bootcampodev.adapter.jpa.rate.RateEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@EqualsAndHashCode
public class Rate {
    private Long id;
     private LocalDateTime createdDate;
    private Long memberId;
    private Long movieId;
    private Integer point;



}
