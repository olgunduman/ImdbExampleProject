package com.example.bootcampodev.dto.response.member;

import com.example.bootcampodev.service.member.Member;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class MemberResponse {

    private String fullName;
    private Integer birthYear;
    private String phone;

    public static MemberResponse convertToMember(Member member) {

        return MemberResponse.builder()
                .fullName(member.getFullName())
                .birthYear(member.getBirthYear())
                .phone(member.getPhone())
                .build();
    }

    public static List<MemberResponse> convertToMemberList(List<Member> member) {
        return member.stream()
                .map(MemberResponse::convertToMember)
                .collect(Collectors.toList());
    }
}
