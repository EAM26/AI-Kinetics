package com.emcode.aikinetics.ai;

import java.io.IOException;

public interface AIService {
    String getAiResponse(String prompt) throws IOException, InterruptedException;


}
