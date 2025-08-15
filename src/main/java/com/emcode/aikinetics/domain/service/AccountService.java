package com.emcode.aikinetics.domain.service;

import com.emcode.aikinetics.api.dto.account.AccountRequest;
import com.emcode.aikinetics.api.dto.account.AccountResponse;
import com.emcode.aikinetics.api.error.NotFoundException;
import com.emcode.aikinetics.domain.model.Account;
import com.emcode.aikinetics.repository.AccountRepository;
import org.springframework.stereotype.Service;

import static com.emcode.aikinetics.api.mapper.AccountMapper.mapToEntity;
import static com.emcode.aikinetics.api.mapper.AccountMapper.mapToResponse;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountResponse createAccount(AccountRequest accountRequest) {
       Account saved = accountRepository.save(mapToEntity(accountRequest));
       return mapToResponse(saved);
    }


    public AccountResponse getAccountById(Long id) {
        Account account = accountRepository.findById(id).
                orElseThrow(()-> new NotFoundException("No account found with id: " + id));
        return mapToResponse(account);
    }
}
