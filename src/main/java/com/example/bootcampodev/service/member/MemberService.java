package com.example.bootcampodev.service.member;

import com.example.bootcampodev.dto.request.member.MemberRequest;
import org.springframework.cache.annotation.CacheEvict;

import java.util.List;

public interface MemberService {
    Long create(MemberRequest memberRequest);


    void clearCache();

    Member retrieve(Long id) throws InterruptedException;

    List<Member> getAll();
}
