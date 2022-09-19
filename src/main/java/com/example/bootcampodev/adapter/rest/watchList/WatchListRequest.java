package com.example.bootcampodev.adapter.rest.watchList;

import com.example.bootcampodev.domain.watch.WatchList;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder

public class WatchListRequest {

    private String name;

    private Long memberId;

    public WatchList converToWatchList() {
       return WatchList.builder()
                .name(this.name)
                .memberId(this.memberId)
                .build();

    }


}
