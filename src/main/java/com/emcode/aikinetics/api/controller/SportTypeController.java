package com.emcode.aikinetics.api.controller;

import com.emcode.aikinetics.api.dto.sporttype.SportTypeRequest;
import com.emcode.aikinetics.api.dto.sporttype.SportTypeResponse;
import com.emcode.aikinetics.domain.service.SportTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sport-types")
public class SportTypeController {

    private final SportTypeService sportTypeService;

    public SportTypeController(SportTypeService sportTypeService) {
        this.sportTypeService = sportTypeService;
    }

    @PostMapping
    public ResponseEntity<SportTypeResponse> createSportType(@RequestBody SportTypeRequest request) {
        SportTypeResponse response =  sportTypeService.createSportType(request);
        return ResponseEntity.ok(response);
    }

}
