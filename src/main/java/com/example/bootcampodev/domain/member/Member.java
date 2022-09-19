package com.example.bootcampodev.domain.member;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
@Builder
@EqualsAndHashCode
public class Member  implements Serializable {
    private Long id;

    private String fullName;

    private Integer birthYear;

    private String phone;



}
