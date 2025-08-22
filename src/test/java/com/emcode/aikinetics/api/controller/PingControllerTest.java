package com.emcode.aikinetics.api.controller;

import com.emcode.aikinetics.api.controller.PingController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PingController.class)
class PingControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    void ping_shouldReturn200() throws Exception {
        mvc.perform(get("/api/ping"))
                .andExpect(status().isOk())
                .andExpect((jsonPath("$.status").value("ok")));
    }

    @Test
    void boom_shouldReturn500() throws Exception {
        mvc.perform(get("/api/ping/boom"))
                .andExpect(status().isInternalServerError())
                .andExpect((jsonPath("$.code").value("UNEXPECTED")));
    }

    @Test
    void exNotfound_shouldReturn404() throws Exception {
        mvc.perform(get("/api/ping/ex_not_found"))
                .andExpect(status().isNotFound())
                .andExpect((jsonPath("$.code").value("NOT_FOUND")));
    }

}