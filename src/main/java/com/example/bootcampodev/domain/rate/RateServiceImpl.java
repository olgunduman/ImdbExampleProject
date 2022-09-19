package com.example.bootcampodev.domain.rate;

import com.example.bootcampodev.domain.movie.Movie;
import com.example.bootcampodev.domain.port.MemberPersistencePort;
import com.example.bootcampodev.domain.port.MoviePersistencePort;
import com.example.bootcampodev.domain.port.RatePersistencePort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RateServiceImpl implements RateService {

    private final RatePersistencePort ratePersistencePort;
    private final MoviePersistencePort moviePersistencePort;
    private final MemberPersistencePort memberPersistencePort;

    public RateServiceImpl(RatePersistencePort ratePersistencePort, MoviePersistencePort moviePersistencePort, MemberPersistencePort memberPersistencePort) {
        this.ratePersistencePort = ratePersistencePort;
        this.moviePersistencePort = moviePersistencePort;
        this.memberPersistencePort = memberPersistencePort;
    }


    @Override
    public void create(Rate rate) {
        Movie movie = moviePersistencePort.retrieve(rate.getMovieId());
        if (movie == null) {
            throw new IllegalArgumentException("Movie not found");
        }

        ratePersistencePort.rateToMovieSave(rate);

    }

    @Override
    public List<Rate> retrieveByMovieId(Long movieId) {
        return ratePersistencePort.retrieveByMovieId(movieId);
    }

    @Override
    public List<Rate> retrieveByMemberId(Long memberId) {
        return ratePersistencePort.retrieveByMemberId(memberId);
    }
}
