package com.example.bootcampodev.adapter.jpa.member;

import com.example.bootcampodev.adapter.jpa.common.BaseEntity;
import com.example.bootcampodev.adapter.jpa.rate.RateEntity;
import com.example.bootcampodev.adapter.jpa.watch.WatchListEntity;
import com.example.bootcampodev.domain.member.Member;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "member")
@Table(name = "member")
public class MemberEntity extends BaseEntity {



    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private Integer birthYear;

    @Column(nullable = false)
    private String phone;

    @OneToMany(mappedBy = "member",fetch = FetchType.EAGER)
    private List<RateEntity> rates;

    @OneToMany(mappedBy = "member")
    private List<WatchListEntity> watchListMovieEntities;

    public static MemberEntity from(Member member) {
    MemberEntity memberEntity = new MemberEntity();
        memberEntity.fullName = member.getFullName();
        memberEntity.birthYear = member.getBirthYear();
        memberEntity.phone = member.getPhone();
        return memberEntity;
    }

    public static MemberEntity fromMemberId(Long memberId) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.id = memberId;
        return memberEntity;
    }


    public Member toModel() {
        return Member.builder()
                .id(this.id)
                .fullName(this.fullName)
                .birthYear(this.birthYear)
                .phone(this.phone)
                .build();
    }
}
