package com.emcode.aikinetics.sporttype;

import com.emcode.aikinetics.sporttype.dto.SportTypeResponse;
import com.emcode.aikinetics.validation.ValidationUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SportTypeController.class)
class SportTypeControllerTest {

    @Autowired
    MockMvc mvc;

    @MockitoBean
    SportTypeService sportTypeService;

    @MockitoBean
    ValidationUtil validationUtil;

    @Test
    void shouldReturnSingleSportTypeById() throws Exception {
        // Arrange
        Long id = 5L;
        var sportTypeResponse = SportTypeResponse.builder()
                .id(id).keyName("Cycling")
                .accountId(1L)
                .build();
        when(sportTypeService.getSingleSportTypeById(id)).thenReturn(sportTypeResponse);

        // Act
        ResultActions result = mvc.perform(get("/api/sport-types/{id}", id));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.keyName").value("Cycling"))
                .andExpect(jsonPath("$.accountId").value(1L));
    }

    @Test
    void createSportType() {
    }
}