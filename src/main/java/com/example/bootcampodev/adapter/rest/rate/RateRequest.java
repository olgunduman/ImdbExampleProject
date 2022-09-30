package com.example.bootcampodev.adapter.rest.rate;

import com.example.bootcampodev.domain.rate.Rate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RateRequest {

    private Long memberId;
    private Long movieId;
    private Integer point;

    public Rate convertToRate(){
        return Rate.builder()
                .memberId(memberId)
                .movieId(movieId)
                .point(point)
                .build();
    }
}
