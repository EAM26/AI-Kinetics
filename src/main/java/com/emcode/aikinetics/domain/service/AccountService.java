package com.emcode.aikinetics.domain.service;

import com.emcode.aikinetics.api.dto.account.AccountRequest;
import com.emcode.aikinetics.api.dto.account.AccountResponse;
import com.emcode.aikinetics.api.error.DuplicateFieldException;
import com.emcode.aikinetics.api.error.NotFoundException;
import com.emcode.aikinetics.api.mapper.AccountMapper;
import com.emcode.aikinetics.domain.model.Account;
import com.emcode.aikinetics.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.emcode.aikinetics.api.mapper.AccountMapper.mapToEntity;
import static com.emcode.aikinetics.api.mapper.AccountMapper.mapToResponse;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountResponse createAccount(AccountRequest accountRequest) {
        if (accountRepository.existsByName(accountRequest.name())) {
            throw new DuplicateFieldException("Name: "+ accountRequest.name() + " already exists.");
        }
        if (accountRepository.existsByEmail(accountRequest.email())) {
            throw new DuplicateFieldException("Email: "+ accountRequest.email() + " already exists.");
        }
        Account account = accountRepository.save(mapToEntity(accountRequest));
            return mapToResponse(accountRepository.save(account));


    }

    public AccountResponse getAccountById(Long id) {
        Account account = accountRepository.findById(id).
                orElseThrow(() -> new NotFoundException("No account found with id: " + id));
        return mapToResponse(account);
    }

    public List<AccountResponse> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(AccountMapper::mapToResponse).toList();
    }
}
