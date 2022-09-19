package com.example.bootcampodev.adapter.jpa.rate;

import com.example.bootcampodev.adapter.jpa.member.MemberJpaRepository;
import com.example.bootcampodev.adapter.jpa.movie.MovieJpaRepository;
import com.example.bootcampodev.domain.port.RatePersistencePort;
import com.example.bootcampodev.domain.rate.Rate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RateJpaAdapter implements RatePersistencePort {

    private final RateJpaRepository rateJpaRepository;
    private final MovieJpaRepository movieJpaRepository;
    private final MemberJpaRepository memberJpaRepository;

    public RateJpaAdapter(RateJpaRepository rateJpaRepository, MovieJpaRepository movieJpaRepository, MemberJpaRepository memberJpaRepository) {
        this.rateJpaRepository = rateJpaRepository;
        this.movieJpaRepository = movieJpaRepository;
        this.memberJpaRepository = memberJpaRepository;
    }

    @Override
    public Rate rateToMovieSave(Rate rate) {
        RateEntity created = rateJpaRepository.save(RateEntity.from(rate));
        return created.toModel();
    }

    @Override
    public List<Rate> retrieveByMovieId(Long movieId) {
        return rateJpaRepository.findAllByMovieId(movieId)
                .stream()
                .map(RateEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Rate> retrieveByMemberId(Long memberId) {
        return rateJpaRepository.findAllByMemberId(memberId)
                .stream()
                .map(RateEntity::toModel)
                .collect(Collectors.toList());
    }
}
