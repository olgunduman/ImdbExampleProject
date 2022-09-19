package com.example.bootcampodev.domain.watch;
import com.example.bootcampodev.domain.member.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WatchList {

    private Long id;

    private String name;

    private Long memberId;



    }