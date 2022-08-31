package com.example.bootcampodev.controller;

import com.example.bootcampodev.dto.request.movieWatch.MovieWatchListRequest;
import com.example.bootcampodev.dto.request.watch.WatchListRequest;
import com.example.bootcampodev.dto.response.movieWatch.MovieWatchListResponse;
import com.example.bootcampodev.dto.response.watch.WatchListResponse;
import com.example.bootcampodev.service.movieWatch.MovieWatchList;
import com.example.bootcampodev.service.movieWatch.MovieWatchListService;
import com.example.bootcampodev.service.watch.WatchList;
import com.example.bootcampodev.service.watch.WatchListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/watchList")
public class WatchListController {

    private final WatchListService watchListService;
    private final MovieWatchListService movieWatchListService;

    public WatchListController(WatchListService watchListService, MovieWatchListService movieWatchListService) {
        this.watchListService = watchListService;
        this.movieWatchListService = movieWatchListService;
    }


    @PostMapping("/create")
    public WatchListResponse create(@RequestBody WatchListRequest request){

        WatchList watchList = request.converToWatchList();
        watchListService.create(watchList);
       return WatchListResponse.convertoWatchMovie(watchList);

    }

    @GetMapping("/{id}")
    public WatchListResponse retrieve(@PathVariable Long id){
        WatchList watchList = watchListService.retrieve(id);
        return WatchListResponse.convertoWatchMovie(watchList);
    }

    @PostMapping("/addMovie")
    public MovieWatchListResponse addMovie(@RequestBody MovieWatchListRequest movieWatchListRequest){
        MovieWatchList movieWatchList = movieWatchListRequest.convertToMovieWatch();
        movieWatchListService.addMovie(movieWatchList);
        return MovieWatchListResponse.convertFromMovieWatch(movieWatchList);
    }
    @GetMapping("/watchList")
    public List<WatchListResponse> findAll(){
        List<WatchList> watchLists = watchListService.findAll();
       return WatchListResponse.convertFromWatch(watchLists);

    }
}
