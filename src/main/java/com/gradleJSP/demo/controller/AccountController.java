package com.gradleJSP.demo.controller;

import com.gradleJSP.demo.entity.Account;
import com.gradleJSP.demo.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@PropertySource("classpath:application.properties")
@Tag(name = "계정", description = "계정 관련 api 입니다.")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String account(){
        return "register";
    }

    @Operation(summary = "계정추가", description = "계정 추가 api")
    @PostMapping("/register")
    public String addAccount(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountService.insert(account);
        return "redirect:/login";
    }
}
