package com.example.bootcampodev.domain.account;

import com.example.bootcampodev.adapter.jpa.account.AccountEntity;

public interface AccountService {
    void save(AccountEntity accountEntity);
}
