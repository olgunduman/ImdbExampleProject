package com.example.bootcampodev.adapter.jpa.account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJpaRepository extends JpaRepository<AccountEntity,Long> {

    AccountEntity findByEmail(String email);
}
