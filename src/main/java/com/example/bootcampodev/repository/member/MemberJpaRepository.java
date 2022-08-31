package com.example.bootcampodev.repository.member;

import com.example.bootcampodev.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<MemberEntity,Long> {
}
