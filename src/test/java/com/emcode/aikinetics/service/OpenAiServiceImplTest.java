package com.emcode.aikinetics.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OpenAiServiceImplTest {

    @Mock
    private HttpClient httpClient;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private HttpResponse<String> httpResponse;

    @InjectMocks
    private OpenAiServiceImpl aiService;


    @Test
    void getAiResponse_whenGivenPrompt_shouldReturnExpectedString() throws IOException, InterruptedException {
        // Arrange
        String prompt = "Wat is de hoofdstad van Nederland?";
        String expectedResponse = "{\"choices\":[{\"message\":{\"content\":\"Amsterdam\"}}]}";

        Map<String, Object> requestBody = Map.of(
                "model", "gpt-4o",
                "messages", List.of(Map.of("role", "user", "content", prompt))
        );
        when(objectMapper.writeValueAsString(requestBody)).thenReturn("{\"dummy-json-request\":\"true\"}");
        when(httpResponse.body()).thenReturn(expectedResponse);
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(httpResponse);

        // Act
        String actualResponse = aiService.getAiResponse(prompt);

        // Assert
        assertEquals(expectedResponse, actualResponse);
    }
}