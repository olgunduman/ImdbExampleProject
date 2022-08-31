package com.example.bootcampodev.repository.rate;

import com.example.bootcampodev.entity.RateEntity;
import com.example.bootcampodev.repository.member.MemberJpaRepository;
import com.example.bootcampodev.repository.movie.MovieJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateDaoImpl implements RateDao{

    private final RateJpaRepository rateJpaRepository;
    private final MovieJpaRepository movieJpaRepository;
    private final MemberJpaRepository memberJpaRepository;

    public RateDaoImpl(RateJpaRepository rateJpaRepository, MovieJpaRepository movieJpaRepository, MemberJpaRepository memberJpaRepository) {
        this.rateJpaRepository = rateJpaRepository;
        this.movieJpaRepository = movieJpaRepository;
        this.memberJpaRepository = memberJpaRepository;
    }

    @Override
    public RateEntity rateToMovie(RateEntity rateEntity) {
       return rateJpaRepository.save(rateEntity);
    }

    @Override
    public List<RateEntity> retrieveByMovieId(Long movieId) {
        return rateJpaRepository.findAllByMovieId(movieId);
    }

    @Override
    public List<RateEntity> retrieveByMemberId(Long memberId) {
        return rateJpaRepository.findAllByMemberId(memberId);
    }
}
