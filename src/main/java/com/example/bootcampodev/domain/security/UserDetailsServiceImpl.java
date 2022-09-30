package com.example.bootcampodev.domain.security;


import com.example.bootcampodev.adapter.jpa.account.AccountEntity;
import com.example.bootcampodev.domain.port.AccountPersistencePort;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private AccountPersistencePort accountPersistencePort;

    public UserDetailsServiceImpl(AccountPersistencePort accountPersistencePort) {
        this.accountPersistencePort = accountPersistencePort;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AccountEntity account = accountPersistencePort.findByEmail(email);
        return new User(account.getEmail(), account.getPassword(), List.of());
    }
}
