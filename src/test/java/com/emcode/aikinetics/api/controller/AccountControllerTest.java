package com.emcode.aikinetics.api.controller;

import com.emcode.aikinetics.api.dto.account.AccountRequest;
import com.emcode.aikinetics.api.dto.account.AccountResponse;
import com.emcode.aikinetics.api.validation.ValidationUtil;
import com.emcode.aikinetics.domain.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(AccountController.class)
@AutoConfigureMockMvc(addFilters = false)
class AccountControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    AccountService accountService;

    @MockitoBean
    ValidationUtil validationUtil;

    private AccountRequest accountRequestOne;
//    private AccountRequest accountRequestTwo;
//    private AccountRequest accountRequestThree;
    private AccountResponse responseOne;
    private AccountResponse responseTwo;
    private AccountResponse responseThree;

    @BeforeEach
    void setup() {
        accountRequestOne = AccountRequest.builder()
                .name("Albert Einstein")
                .email("albert@gmail.com")
                .build();


        responseOne = AccountResponse.builder()
                .id(1L)
                .name("Albert Einstein")
                .email("albert@gmail.com")
                .build();

        responseTwo = AccountResponse.builder()
                .id(2L)
                .name("Isaac Newton")

                .email("isaac@hotmail.com")
                .build();

        responseThree = AccountResponse.builder()
                .id(3L)
                .name("Niels Bohr")
                .email("niels@gmail.com")
                .build();

    }

    @Test
    void getAllShouldReturnListAnd200() throws Exception {
        // Arrange
        when(accountService.getAllAccounts()).thenReturn(List.of(responseTwo, responseThree));

        // Act
        ResultActions result = mvc.perform(get("/api/accounts"));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[?(@.id == 2)].name").value("Isaac Newton"))
                .andExpect(jsonPath("$[?(@.id == 3)].name").value("Niels Bohr"))
                .andExpect(jsonPath("$[?(@.id == 2)].email").value("isaac@hotmail.com"))
                .andExpect(jsonPath("$[?(@.id == 3)].email").value("niels@gmail.com"));

    }

    @Test
    void createShouldReturn201AndBody() throws Exception {
        // Arrange
        when(accountService.createAccount(accountRequestOne))
                .thenReturn(responseOne);

        // ACT
        ResultActions result = mvc.perform(post("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accountRequestOne)));

        // ASSERT
        result.andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/accounts/1"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value(accountRequestOne.name()))
                .andExpect(jsonPath("$.email").value(accountRequestOne.email()));

        verify(accountService).createAccount(any(AccountRequest.class));
        verifyNoMoreInteractions(accountService);
    }

    @Test
    void getAccountByIdShouldReturn200AndResponse() throws Exception {
        // Arrange

        Long id = 10L;
        var responseFour = AccountResponse.builder()
                .id(10L)
                .name("Max Planck")
                .email("max@yahoo.com")
                .build();

        when(accountService.getAccountById(id))
                .thenReturn(responseFour);

        // ACT
        ResultActions result = mvc.perform(get("/api/accounts/{id}", id));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Max Planck"))
                .andExpect(jsonPath("$.email").value("max@yahoo.com"));
    }
}
