package com.gradleJSP.demo.service;

import com.gradleJSP.demo.entity.Account;
import com.gradleJSP.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account insert(Account account){
        return accountRepository.save(account);
    }
}
