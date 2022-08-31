package com.example.bootcampodev.service.member;

import com.example.bootcampodev.entity.MemberEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
@EqualsAndHashCode
public class Member  implements Serializable {

    private String fullName;

    private Integer birthYear;

    private String phone;



    public static Member convertToMemberEntity(MemberEntity memberEntity) {
        return Member.builder()
                .fullName(memberEntity.getFullName())
                .birthYear(memberEntity.getBirthYear())
                .phone(memberEntity.getPhone())
                .build();

    }
}
