package com.example.bootcampodev.adapter.rest.member;

import com.example.bootcampodev.adapter.jpa.member.MemberEntity;
import com.example.bootcampodev.domain.member.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberRequest {

    private String fullName;

    private Integer birthYear;

    private String phone;


    public Member convertToMember() {
        return Member.builder()
                .fullName(this.fullName)
                .birthYear(this.birthYear)
                .phone(this.phone)
                .build();
    }
}
