package com.emcode.aikinetics.api.mapper;

import com.emcode.aikinetics.api.dto.account.AccountRequest;
import com.emcode.aikinetics.api.dto.account.AccountResponse;
import com.emcode.aikinetics.domain.model.Account;
import org.springframework.stereotype.Component;

@Component
public final class AccountMapper {

    public  AccountResponse mapToResponse(Account account) {
        return new AccountResponse(account.getId(), account.getName(), account.getEmail());
    }

    public Account mapToEntity(AccountRequest request) {
        Account account = new Account();
        account.setName(request.name());
        account.setEmail(request.email());
        return account;
    }
}
