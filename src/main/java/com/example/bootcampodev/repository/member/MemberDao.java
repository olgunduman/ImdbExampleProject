package com.example.bootcampodev.repository.member;

import com.example.bootcampodev.dto.request.member.MemberRequest;
import com.example.bootcampodev.entity.MemberEntity;
import com.example.bootcampodev.service.member.Member;

import java.util.List;

public interface MemberDao {
    Long save(MemberRequest memberRequest);

    MemberEntity retrieve(Long id);

    List<MemberEntity> findAll();
}
