package com.example.bootcampodev.repository.rate;

import com.example.bootcampodev.entity.RateEntity;
import com.example.bootcampodev.service.rate.Rate;

import java.util.List;

public interface RateDao {
    RateEntity rateToMovie(RateEntity rate);

    List<RateEntity> retrieveByMovieId(Long movieId);

    List<RateEntity> retrieveByMemberId(Long memberId);
}
