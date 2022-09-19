package com.example.bootcampodev.domain.member;

import java.util.List;

public interface MemberService {
    Long create(Member member);


    Member retrieve(Long id);

    List<Member> getAll();
}
