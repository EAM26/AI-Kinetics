package com.emcode.aikinetics.ai;

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
        String responseBody = aiService.getAiResponse(prompt);
        System.out.println("**********************************");
        System.out.println(responseBody);
        System.out.println("**********************************");
        return ResponseEntity.ok(responseBody);

    }

}
