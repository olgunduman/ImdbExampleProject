package com.example.bootcampodev.dto.response.watch;

import com.example.bootcampodev.service.watch.WatchList;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder

public class WatchListResponse {


    private String name;

    private Long memberId;

    public static List<WatchListResponse> convertFromWatch(List<WatchList> watchList) {
        return watchList.stream()
                .map(WatchListResponse::converToWatch)
                .collect(Collectors.toList());
    }


    private static WatchListResponse converToWatch(WatchList watchList){
       return WatchListResponse.builder()
                .name(watchList.getName())
                .memberId(watchList.getMemberId())
                .build();
    }

    public static WatchListResponse convertoWatchMovie(WatchList watchList) {
        return WatchListResponse.builder()
                .name(watchList.getName())
                .memberId(watchList.getMemberId())
                .build();
    }



}
