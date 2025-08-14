package com.emcode.aikinetics.service;

import com.emcode.aikinetics.api.dto.account.AccountRequest;
import com.emcode.aikinetics.domain.model.Account;
import com.emcode.aikinetics.domain.service.AccountService;
import com.emcode.aikinetics.repository.AccountRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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

    @Test
    void createAccount() {
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
        Account result = accountService.createAccount(input);

        // Assert
        verify(accountRepository, times(1)).save(captor.capture());
        Account toPersist = captor.getValue();
        assertThat(toPersist.getName()).isEqualTo(input.getName());
        assertThat(toPersist.getEmail()).isEqualTo(input.getEmail());

        Assertions.assertThat(result).isEqualTo(saved);
    }
}