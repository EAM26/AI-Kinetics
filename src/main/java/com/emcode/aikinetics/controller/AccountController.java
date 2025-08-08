package com.emcode.aikinetics.controller;

import com.emcode.aikinetics.dto.AccountDto;
import com.emcode.aikinetics.model.Account;
import com.emcode.aikinetics.repository.AccountRepository;
import com.emcode.aikinetics.service.AccountService;
import com.emcode.aikinetics.util.ValidationUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> createAccount(@Valid @RequestBody AccountDto accountDto, BindingResult br) {
        if (br.hasFieldErrors()) {
            return ResponseEntity.badRequest().body(validationUtil.validationMessage(br).toString());
        }
        return ResponseEntity.ok(accountService.createAccount(accountDto));
    }
}
