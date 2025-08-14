package com.emcode.aikinetics.controller;

import com.emcode.aikinetics.dto.AccountDto;
import com.emcode.aikinetics.model.Account;
import com.emcode.aikinetics.service.AccountService;
import com.emcode.aikinetics.util.ValidationUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;
    private final ValidationUtil validationUtil;

    public AccountController(AccountService accountService, ValidationUtil validationUtil) {
        this.accountService = accountService;
        this.validationUtil = validationUtil;
    }

    @PostMapping
    public ResponseEntity<Object> createAccount(@Valid @RequestBody AccountDto accountDto, BindingResult br, UriComponentsBuilder ucb) {
        if (br.hasFieldErrors()) {
            return ResponseEntity.badRequest().body(validationUtil.validationMessage(br).toString());
        }

        Account savedAccount = accountService.createAccount(accountDto);
        URI location = URI.create("/api/account/" + savedAccount.getId());

        return ResponseEntity.created(location).body(savedAccount);
    }
}
