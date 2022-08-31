package com.example.bootcampodev.service.rate;

import com.example.bootcampodev.entity.MemberEntity;
import com.example.bootcampodev.entity.MovieEntity;
import com.example.bootcampodev.repository.member.MemberDao;
import com.example.bootcampodev.repository.movie.MovieDao;
import com.example.bootcampodev.repository.rate.RateDao;
import com.example.bootcampodev.service.rate.Rate;
import com.example.bootcampodev.service.rate.RateService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RateServiceImpl implements RateService {

    private final RateDao rateDao;
    private final MovieDao movieDao;
    private final MemberDao memberDao;

    public RateServiceImpl(RateDao rateDao, MovieDao movieDao, MemberDao memberDao) {
        this.rateDao = rateDao;
        this.movieDao = movieDao;
        this.memberDao = memberDao;
    }


    @Override
    public void create(Rate rate) {
        MovieEntity movieEntity = movieDao.retrieve(rate.getMovieId());
        MemberEntity memberEntity = memberDao.retrieve(rate.getMemberId());
        var rateEntity = rate.convertToRateEntity(movieEntity,memberEntity);
        rateDao.rateToMovie(rateEntity);

    }

    @Override
    public List<Rate> retrieveByMovieId(Long movieId) {
        return rateDao.retrieveByMovieId(movieId)
                .stream()
                .map(Rate::convertfromRateEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Rate> retrieveByMemberId(Long memberId) {
        return rateDao.retrieveByMemberId(memberId).stream()
                .map(Rate::convertfromRateEntity)
                .collect(Collectors.toList());
    }
}
