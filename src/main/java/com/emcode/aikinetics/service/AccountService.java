package com.emcode.aikinetics.service;

import com.emcode.aikinetics.api.dto.AccountDto;
import com.emcode.aikinetics.model.Account;
import com.emcode.aikinetics.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(AccountDto accountDto) {
       return accountRepository.save(mapToEntity(accountDto));
    }

    private Account mapToEntity(AccountDto dto) {
        Account account = new Account();
        account.setName(dto.getName());
        account.setEmail(dto.getEmail());
        account.setPassword(dto.getPassword());

        return account;
    }
}
