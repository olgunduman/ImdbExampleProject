package com.example.bootcampodev.domain.rate;

import java.util.List;

public interface RateService {
    void create(Rate rate);

    List<Rate> retrieveByMovieId(Long movieId);

    List<Rate> retrieveByMemberId(Long memberId);
}
