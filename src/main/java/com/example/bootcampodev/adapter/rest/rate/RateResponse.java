package com.example.bootcampodev.adapter.rest.rate;

import com.example.bootcampodev.domain.rate.Rate;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder

public class RateResponse {
    private LocalDateTime createdDate;
    private Long memberId;
    private Long movieId;
    private Integer point;

    public static List<RateResponse> convertFromRate(List<Rate> rateList) {
      return   rateList.stream()
                .map(RateResponse::convertToRate)
                .collect(Collectors.toList());
    }


    private static RateResponse convertToRate(Rate rate){
       return RateResponse.builder()
                .createdDate(LocalDateTime.now())
                .memberId(rate.getMemberId())
                .movieId(rate.getMovieId())
                .point(rate.getPoint())
                .build();
    }
}
