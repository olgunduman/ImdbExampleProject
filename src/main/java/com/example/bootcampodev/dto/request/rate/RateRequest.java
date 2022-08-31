package com.example.bootcampodev.dto.request.rate;

import com.example.bootcampodev.service.rate.Rate;
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
