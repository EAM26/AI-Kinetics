package com.emcode.aikinetics.sporttype;

import com.emcode.aikinetics.validation.ValidationUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sport-types")
public class SportTypeController {

    private final SportTypeService sportTypeService;
    private final ValidationUtil validationUtil;
    private final SportTypeRepository sportTypeRepository;

    public SportTypeController(SportTypeService sportTypeService, ValidationUtil validationUtil, SportTypeRepository sportTypeRepository) {
        this.sportTypeService = sportTypeService;
        this.validationUtil = validationUtil;
        this.sportTypeRepository = sportTypeRepository;
    }

    @GetMapping
    public ResponseEntity<List<SportTypeResponse>> getAllSportTypes() {
        return ResponseEntity.ok(sportTypeService.getAllSportTypes());
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<SportTypeResponse>> getAllSportTypesByAccountId(@PathVariable Long accountId) {
        return ResponseEntity.ok(sportTypeService.getAllSportTypesByAccountId(accountId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SportTypeResponse> getSingleSportTypeById(@PathVariable Long id) {
        SportTypeResponse response = sportTypeService.getSingleSportTypeById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> createSportType(@Valid @RequestBody SportTypeRequest request, BindingResult br) {
        if (br.hasFieldErrors()) {
            return ResponseEntity.badRequest().body(validationUtil.validationMessage(br).toString());
        }
        SportTypeResponse savedSportType = sportTypeService.createSportType(request);
        URI location = URI.create("/api/sport-types/" + savedSportType.id());
        return ResponseEntity.created(location).body(savedSportType);
    }

}
