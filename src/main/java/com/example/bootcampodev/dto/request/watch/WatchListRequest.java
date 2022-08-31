package com.example.bootcampodev.dto.request.watch;

import com.example.bootcampodev.service.watch.WatchList;
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
