package com.emcode.aikinetics.ping;

import com.emcode.aikinetics.exception.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/ping")
public class PingController {

    @GetMapping
    public Map<String, String> ping() {
        return Map.of("status", "ok");
    }

    @GetMapping("/boom")
    public String boom() {
        throw new RuntimeException("Boom");
    }

    @GetMapping("/ex_not_found")
    public String exNotfound() {
        throw new NotFoundException("custom not found exception");
    }
}
