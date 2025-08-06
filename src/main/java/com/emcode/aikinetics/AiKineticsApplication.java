package com.emcode.aikinetics;

import com.emcode.aikinetics.service.AIService;
import com.emcode.aikinetics.service.OpenAiServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AiKineticsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiKineticsApplication.class, args);


    }

}
