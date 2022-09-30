package com.example.bootcampodev.domain.member;

import com.example.bootcampodev.domain.port.MemberPersistencePort;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class MemberServiceImpl implements MemberService {

    private final MemberPersistencePort memberPersistencePort;

    public MemberServiceImpl(MemberPersistencePort memberPersistencePort) {
        this.memberPersistencePort = memberPersistencePort;
    }

    @Override
    public Long create(Member member) {
        return memberPersistencePort.save(member);
    }




    @Override
    public Member retrieve(Long id){
        Member retrieveMember = memberPersistencePort.retrieve(id);
       return retrieveMember;

    }

    @Override
    @Cacheable(cacheNames = "redisMemberGetAll")
    public List<Member> getAll() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return memberPersistencePort.findAll();
    }
}
