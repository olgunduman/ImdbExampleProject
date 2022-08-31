package com.example.bootcampodev.service.movieWatch;

import com.example.bootcampodev.entity.MovieEntity;
import com.example.bootcampodev.entity.MovieWatchListEntity;
import com.example.bootcampodev.entity.WatchListEntity;
import com.example.bootcampodev.repository.movie.MovieDao;
import com.example.bootcampodev.repository.movieWatch.MovieWatchListDao;
import com.example.bootcampodev.repository.watch.WatchListDao;
import org.springframework.stereotype.Service;

@Service
public class MovieWatchListServiceImpl implements MovieWatchListService {

    private final MovieWatchListDao movieWatchListDao;
    private final MovieDao movieDao;
    private final WatchListDao watchListDao;

    public MovieWatchListServiceImpl(MovieWatchListDao movieWatchListDao, MovieDao movieDao, WatchListDao watchListDao) {
        this.movieWatchListDao = movieWatchListDao;
        this.movieDao = movieDao;
        this.watchListDao = watchListDao;
    }

    @Override
    public MovieWatchList addMovie(MovieWatchList movieWatchList) {

        MovieEntity movieEntity = movieDao.retrieve(movieWatchList.getMovieId());
        WatchListEntity watchListEntity  = watchListDao.retrieve(movieWatchList.getWatchListId());
        MovieWatchListEntity movieWatchListEntity = movieWatchList.convertToMovieWatch(movieEntity,watchListEntity);
        movieWatchListDao.create(movieWatchListEntity);
        return movieWatchList;

    }
}
