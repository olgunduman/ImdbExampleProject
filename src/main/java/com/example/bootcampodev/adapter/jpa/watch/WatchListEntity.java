package com.example.bootcampodev.adapter.jpa.watch;

import com.example.bootcampodev.adapter.jpa.member.MemberEntity;
import com.example.bootcampodev.adapter.jpa.movieWatch.MovieWatchListEntity;
import com.example.bootcampodev.domain.watch.WatchList;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "watchList")
public class WatchListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private MemberEntity member;

    @OneToMany(mappedBy = "watchList")
    private List<MovieWatchListEntity> movieWatchListEntity;

    public static WatchListEntity from(WatchList watchList) {
        return WatchListEntity.builder()
                .id(watchList.getId())
                .name(watchList.getName())
                .member(MemberEntity.fromMemberId(watchList.getMemberId()))
                .build();
    }

    public WatchList toModel() {
        return WatchList.builder()
                .id(this.id)
                .name(this.name)
                .memberId(this.member.getId())
                .build();
    }

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
