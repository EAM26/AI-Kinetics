package com.emcode.aikinetics.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface AIService {
    String getAiResponse(String prompt) throws IOException, InterruptedException;


}
