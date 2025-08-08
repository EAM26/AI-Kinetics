package com.emcode.aikinetics.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

@Service
public class OpenAiServiceImpl implements AIService {


    private final String apiKey;
    private final ObjectMapper objectMapper;
    private final HttpClient client;

    public OpenAiServiceImpl(
            HttpClient client,
            ObjectMapper objectMapper,
            @Value("${openai.api.key}") String apiKey
    ) {
        this.client = client;
        this.objectMapper = objectMapper;
        this.apiKey = apiKey;
    }

    @Override
    public String getAiResponse(String prompt) throws IOException, InterruptedException {

        Map<String, Object> requestBody = Map.of(
                "model", "gpt-4o",
                "messages", List.of(Map.of("role", "user", "content", prompt))
        );

        String requestBodyJson = objectMapper.writeValueAsString(requestBody);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openai.com/v1/chat/completions"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(requestBodyJson))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }


}
