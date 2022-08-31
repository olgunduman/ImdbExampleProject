package com.example.bootcampodev.service.watch;

import com.example.bootcampodev.entity.MemberEntity;
import com.example.bootcampodev.entity.WatchListEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WatchList {

    private Long id;

    private String name;

    private Long memberId;


    public static WatchList convertToWatchListEntity(WatchListEntity watchListEntity) {
        return WatchList.builder()
                .name(watchListEntity.getName())
                .memberId(watchListEntity.getMember().getId())
                .build();
    }

    public WatchListEntity convertToRateEntity(MemberEntity memberEntity) {
        WatchListEntity watchListMovie = new WatchListEntity();
        watchListMovie.setName(this.name);
        watchListMovie.setMember(memberEntity);
        return watchListMovie;

    }
}
