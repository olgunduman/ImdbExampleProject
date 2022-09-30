package com.example.bootcampodev.adapter.rest.account;

import com.example.bootcampodev.adapter.jpa.account.AccountEntity;
import com.example.bootcampodev.domain.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController

@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;



    @PostMapping("/accounts")
    public void login(@RequestBody AccountEntity accountEntity) {
        accountEntity.setPassword(bCryptPasswordEncoder.encode(accountEntity.getPassword()));
        accountService.save(accountEntity);
    }

}
