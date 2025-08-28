package com.emcode.aikinetics.account;

import com.emcode.aikinetics.account.dto.AccountRequest;
import com.emcode.aikinetics.account.dto.AccountResponse;
import com.emcode.aikinetics.exception.DuplicateFieldException;
import com.emcode.aikinetics.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountService {

    private final AccountMapper mapper;
    private final AccountRepository accountRepository;

    public AccountService(AccountMapper mapper, AccountRepository accountRepository) {
        this.mapper = mapper;
        this.accountRepository = accountRepository;
    }

    public AccountResponse createAccount(AccountRequest accountRequest) {
        if (accountRepository.existsByNameIgnoreCase((accountRequest.name()))) {
            throw new DuplicateFieldException("Name: "+ accountRequest.name() + " already exists.");
        }
        if (accountRepository.existsByEmailIgnoreCase((accountRequest.email()))) {
            throw new DuplicateFieldException("Email: "+ accountRequest.email() + " already exists.");
        }
        Account account = accountRepository.save(mapper.mapToEntity(accountRequest));
            return mapper.mapToResponse(account);


    }

    public AccountResponse getAccountById(Long id) {
        Account account = accountRepository.findById(id).
                orElseThrow(() -> new NotFoundException("No account found with id: " + id));
        return mapper.mapToResponse(account);
    }

    public List<AccountResponse> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(mapper::mapToResponse).toList();
    }
}
