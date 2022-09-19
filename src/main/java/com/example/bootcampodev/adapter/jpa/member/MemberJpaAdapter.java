package com.example.bootcampodev.adapter.jpa.member;

import com.example.bootcampodev.adapter.rest.member.MemberRequest;
import com.example.bootcampodev.domain.member.Member;
import com.example.bootcampodev.domain.port.MemberPersistencePort;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberJpaAdapter implements MemberPersistencePort {

    private final MemberJpaRepository memberJpaRepository;

    public MemberJpaAdapter(MemberJpaRepository memberJpaRepository) {
        this.memberJpaRepository = memberJpaRepository;
    }

    @Override
    public Long save(Member member) {
       var createdMember = memberJpaRepository.save(MemberEntity.from(member));
         return createdMember.getId();

    }

    @Override
    public Member retrieve(Long id) {
        MemberEntity retrieveMember = memberJpaRepository.findById(id).orElseThrow(() -> new NotFoundException("Member Id bulunamadı"));
        return retrieveMember.toModel();
    }

    @Override
    public List<Member> findAll() {

        return memberJpaRepository.findAll()
                .stream()
                .map(MemberEntity::toModel)
                .collect(Collectors.toList());
    }
}
