package com.emcode.aikinetics.controller;

import com.emcode.aikinetics.model.Account;
import com.emcode.aikinetics.repository.AccountRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @PostMapping
    public void createAccount(@RequestBody Account account) {
        accountRepository.save(account);
    }
}
