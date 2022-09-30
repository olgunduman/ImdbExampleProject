package com.example.bootcampodev.domain.account;

import com.example.bootcampodev.adapter.jpa.account.AccountEntity;
import com.example.bootcampodev.domain.port.AccountPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final AccountPersistencePort accountPersistencePort;
    @Override
    public void save(AccountEntity accountEntity) {
        accountPersistencePort.save(accountEntity);

    }
}
