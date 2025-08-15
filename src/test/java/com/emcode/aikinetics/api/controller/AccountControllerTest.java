package com.emcode.aikinetics.api.controller;

import com.emcode.aikinetics.api.dto.account.AccountRequest;
import com.emcode.aikinetics.api.mapper.AccountMapper;
import com.emcode.aikinetics.api.validation.ValidationUtil;
import com.emcode.aikinetics.domain.model.Account;
import com.emcode.aikinetics.domain.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    AccountService accountService;

    @MockitoBean
    ValidationUtil validationUtil;

    @Test
    void createShouldReturn201AndBody() throws Exception {
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


        when(accountService.createAccount(input)).thenReturn(AccountMapper.mapToResponse(saved));

        // ACT
        ResultActions result = mvc.perform(post("/api/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)));

        // ASSERT
        result.andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/account/1"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("myName"))
                .andExpect(jsonPath("$.email").value("mail@gmail.nl"));


        verify(accountService).createAccount(any(AccountRequest.class));
        verifyNoMoreInteractions(accountService);


    }

    @Test
    void getAccountByIdShouldReturn200AndResponse() throws Exception {
        // Arrange
        Long id = 10L;
        var accountRequest = AccountRequest.builder()
                .name("myName")
                .email("mail@gmail.nl")
                .build();

        var accountEntity = Account.builder()
                .id(id)
                .name("myName")
                .email("mail@gmail.nl")
                .build();

        when(accountService.getAccountById(accountEntity.getId())).thenReturn(AccountMapper.mapToResponse(accountEntity));

        // ACT
        ResultActions result = mvc.perform(get("/api/account/{id}", id));

        // Assert

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(accountRequest.name()))
                .andExpect(jsonPath("$.email").value(accountRequest.email()));


    }


}