package com.emcode.aikinetics.api.mapper;

import com.emcode.aikinetics.api.dto.account.AccountRequest;
import com.emcode.aikinetics.api.dto.account.AccountResponse;
import com.emcode.aikinetics.domain.model.Account;

public final class AccountMapper {

    public static AccountResponse mapToResponse(Account account) {
        return new AccountResponse(account.getId(), account.getName(), account.getEmail());
    }

    public static Account mapToEntity(AccountRequest request) {
        Account account = new Account();
        account.setName(request.getName());
        account.setEmail(request.getEmail());
        return account;
    }
}
