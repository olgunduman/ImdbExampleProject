package com.example.bootcampodev.domain.port;

import com.example.bootcampodev.adapter.jpa.account.AccountEntity;

public interface AccountPersistencePort {

    void save(AccountEntity accountEntity);

    AccountEntity findByEmail(String username);
}
