package com.example.bootcampodev.repository.member;

import com.example.bootcampodev.dto.request.member.MemberRequest;
import com.example.bootcampodev.entity.MemberEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class MemberDaoImpl implements MemberDao{

    private final MemberJpaRepository memberJpaRepository;

    public MemberDaoImpl(MemberJpaRepository memberJpaRepository) {
        this.memberJpaRepository = memberJpaRepository;
    }

    @Override
    public Long save(MemberRequest memberRequest) {
       MemberEntity savedMember= memberJpaRepository.save(memberRequest.convertToMember());
       return savedMember.getId();
    }

    @Override
    public MemberEntity retrieve(Long id) {
        var memberEntity = memberJpaRepository.findById(id).orElseThrow(() -> new NotFoundException("Member Id bulunamadı"));
        return memberEntity;
    }

    @Override
    public List<MemberEntity> findAll() {
        return memberJpaRepository.findAll();
    }
}
