package com.example.bootcampodev.service.member;

import com.example.bootcampodev.dto.request.member.MemberRequest;
import com.example.bootcampodev.entity.MemberEntity;
import com.example.bootcampodev.repository.member.MemberDao;
import com.example.bootcampodev.service.member.Member;
import com.example.bootcampodev.service.member.MemberService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberDao memberDao;

    public MemberServiceImpl(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Override
    public Long create(MemberRequest memberRequest) {

        return memberDao.save(memberRequest);
    }

    @Override
    @CacheEvict("mySpecialCache")
    public void clearCache(){
        System.out.println(" mySpecialCache is clearing now");
    }


    @Override
   @Cacheable("mySpecialCache")
    public Member retrieve(Long id) throws InterruptedException {
        Thread.sleep(2000);
      MemberEntity memberEntity = memberDao.retrieve(id);
    return   Member.convertToMemberEntity(memberEntity);
    }

    @Override
    @Cacheable(cacheNames = "redisMemberGetAll")
    public List<Member> getAll() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return memberDao.findAll().stream()
               .map(Member::convertToMemberEntity)
               .collect(Collectors.toList());
    }
}
