package com.example.bootcampodev.service.rate;

import com.example.bootcampodev.service.rate.Rate;

import java.util.List;

public interface RateService {
    void create(Rate rate);

    List<Rate> retrieveByMovieId(Long movieId);

    List<Rate> retrieveByMemberId(Long memberId);
}
