package com.emcode.aikinetics.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OpenAiServiceImpl implements AIService {

    @Value("${openai.api.key}")
    private String apiKey;

    @Override
    public String getAiResponse(String prompt) {
        return ("The prompt was: " + prompt);
    }
}
