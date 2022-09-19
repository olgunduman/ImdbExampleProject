package com.example.bootcampodev.domain.port;

import com.example.bootcampodev.domain.rate.Rate;

import java.util.List;

public interface RatePersistencePort {
    Rate rateToMovieSave(Rate rate);

    List<Rate> retrieveByMovieId(Long movieId);

    List<Rate> retrieveByMemberId(Long memberId);
}
