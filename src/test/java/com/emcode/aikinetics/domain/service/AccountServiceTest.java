package com.emcode.aikinetics.domain.service;

import com.emcode.aikinetics.api.dto.account.AccountRequest;
import com.emcode.aikinetics.api.dto.account.AccountResponse;
import com.emcode.aikinetics.api.mapper.AccountMapper;
import com.emcode.aikinetics.domain.model.Account;
import com.emcode.aikinetics.repository.AccountRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    AccountRepository accountRepository;

    @Captor
    ArgumentCaptor<Account> captor;

    @InjectMocks
    AccountService accountService;

    @Spy
    AccountMapper accountMapper = new AccountMapper();

    @Test
    void createShouldReturnAccountResponse() {
        // Arrange
        var input = AccountRequest.builder()
                .name("myName")
                .email("mail@gmail.nl")
                .build();

        var saved = Account.builder()
                .id(1L)
                .name("myName")
                .email("mail@gmail.nl")
                .build();

        when(accountRepository.save(any(Account.class))).thenReturn(saved);

        // Act
        AccountResponse result = accountService.createAccount(input);

        // Assert
        verify(accountRepository, times(1)).save(captor.capture());
        Account toPersist = captor.getValue();
        assertThat(toPersist.getName()).isEqualTo(input.name());
        assertThat(toPersist.getEmail()).isEqualTo(input.email());

        Assertions.assertThat(result).isEqualTo(accountMapper.mapToResponse(saved));
    }

    @Test
    void getByIdShouldReturnAccountResponse() {
        // Arrange
        Long id = 1L;
        var accountEntity = Account.builder()
                .id(id)
                .name("myName")
                .email("mail@gmail.nl")
                .build();

        when(accountRepository.findById(id)).thenReturn(Optional.ofNullable(accountEntity));

        // Act
        AccountResponse result = accountService.getAccountById(id);

        // Assert
        assertNotNull(accountEntity);
        assertThat(result.id()).isEqualTo(id);
        assertThat(result.name()).isEqualTo(accountEntity.getName());
        assertThat(result.email()).isEqualTo(accountEntity.getEmail());


    }
}