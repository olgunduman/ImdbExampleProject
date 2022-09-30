package com.example.bootcampodev.adapter.jpa.account;

import com.example.bootcampodev.domain.port.AccountPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountJpaAdapter implements AccountPersistencePort {

    private final AccountJpaRepository accountJpaRepository;
    @Override
    public void save(AccountEntity accountEntity) {

        accountJpaRepository.save(accountEntity);

    }

    @Override
    public AccountEntity findByEmail(String username) {
        return accountJpaRepository.findByEmail(username);
    }
}
