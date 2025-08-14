package com.emcode.aikinetics.domain.service;

import com.emcode.aikinetics.api.dto.account.AccountRequest;
import com.emcode.aikinetics.domain.model.Account;
import com.emcode.aikinetics.repository.AccountRepository;
import org.springframework.stereotype.Service;

import static com.emcode.aikinetics.api.mapper.AccountMapper.mapToEntity;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(AccountRequest accountRequest) {
       return accountRepository.save(mapToEntity(accountRequest));
    }


}
