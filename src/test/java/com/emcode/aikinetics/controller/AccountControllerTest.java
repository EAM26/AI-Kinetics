package com.emcode.aikinetics.controller;

import com.emcode.aikinetics.dto.AccountDto;
import com.emcode.aikinetics.model.Account;
import com.emcode.aikinetics.service.AccountService;
import com.emcode.aikinetics.util.ValidationUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @Autowired
    MockMvc mvc;

    @MockitoBean
    AccountService accountService;

    @MockitoBean
    ValidationUtil validationUtil;

    @Test
    void createShouldReturn201AndBody() throws Exception {
        // Arrange
        var input = AccountDto.builder()
                .name("myName")
                .email("mail@gmail.nl")
                .password("1234ABC!")
                .build();

        var saved = Account.builder()
                .id(1L)
                .name("myName")
                .email("mail@gmail.nl")
                .password("1234ABC!")
                .build();

        String jsonBody = """
                {"name":"myName",
                "email":"mail@gmail.nl",
                "password":"1234ABC!"}
                """;

        when(accountService.createAccount(input)).thenReturn(saved);

        // ACT
        var result = mvc.perform(post("/api/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody));

        // ASSERT
        result.andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/account/1"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("myName"))
                .andExpect(jsonPath("$.email").value("mail@gmail.nl"));

        verify(accountService).createAccount(any(AccountDto.class));
        verifyNoMoreInteractions(accountService);






    }
}