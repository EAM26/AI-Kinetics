package com.emcode.aikinetics.api.controller;

import com.emcode.aikinetics.api.dto.account.AccountRequest;
import com.emcode.aikinetics.api.dto.account.AccountResponse;
import com.emcode.aikinetics.api.validation.ValidationUtil;
import com.emcode.aikinetics.domain.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;
    private final ValidationUtil validationUtil;

    public AccountController(AccountService accountService, ValidationUtil validationUtil) {
        this.accountService = accountService;
        this.validationUtil = validationUtil;
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @PostMapping
    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountRequest accountRequest, BindingResult br) {
        if (br.hasFieldErrors()) {
            return ResponseEntity.badRequest().body(validationUtil.validationMessage(br).toString());
        }

        AccountResponse savedAccount = accountService.createAccount(accountRequest);
        URI location = URI.create("/api/accounts/" + savedAccount.id());

        return ResponseEntity.created(location).body(savedAccount);
    }
}
