package com.example.bootcampodev.dto.request.member;

import com.example.bootcampodev.entity.MemberEntity;
import com.example.bootcampodev.service.member.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberRequest {

    private String fullName;

    private Integer birthYear;

    private String phone;



    public MemberEntity convertToMember() {
       return MemberEntity.builder()
               .fullName(this.fullName)
               .birthYear(this.birthYear)
               .phone(this.phone)
               .build();
    }
}
