package com.example.bootcampodev.domain.port;

import com.example.bootcampodev.domain.member.Member;

import java.util.List;

public interface MemberPersistencePort {
    Long save(Member member);

    Member retrieve(Long id);

    List<Member> findAll();
}
