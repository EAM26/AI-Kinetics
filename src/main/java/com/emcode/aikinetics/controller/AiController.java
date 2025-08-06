package com.emcode.aikinetics.controller;

import com.emcode.aikinetics.service.AIService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/ai/ask")
public class AiController {

    private final AIService aiService;

    public AiController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping
    public ResponseEntity<String> askAi(@RequestBody String prompt) throws IOException, InterruptedException {
        return ResponseEntity.ok(aiService.getAiResponse(prompt));

    }

}
