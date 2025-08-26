package com.emcode.aikinetics.domain.service;

import com.emcode.aikinetics.api.dto.sporttype.SportTypeRequest;
import com.emcode.aikinetics.api.dto.sporttype.SportTypeResponse;
import com.emcode.aikinetics.api.error.DuplicateFieldException;
import com.emcode.aikinetics.api.error.NotFoundException;
import com.emcode.aikinetics.api.mapper.SportTypeMapper;
import com.emcode.aikinetics.domain.model.SportType;
import com.emcode.aikinetics.repository.SportTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportTypeService {

    private final SportTypeMapper mapper;
    private final SportTypeRepository sportTypeRepository;

    public SportTypeService(SportTypeMapper mapper, SportTypeRepository sportTypeRepository) {
        this.mapper = mapper;
        this.sportTypeRepository = sportTypeRepository;
    }

    public SportTypeResponse createSportType(SportTypeRequest request) {
        if (sportTypeRepository.existsByKeyNameIgnoreCaseAndAccount_Id(request.keyName(), request.accountId())) {
            throw new DuplicateFieldException(request.keyName() + " already exists.");
        }
        SportType sportType = sportTypeRepository.save(mapper.mapToEntity(request));
        return mapper.mapToResponse(sportType);
    }

    public SportTypeResponse getSingleSportTypeById(Long id) {
        SportType sportType = sportTypeRepository.findById(id).orElseThrow(() ->
                new NotFoundException("No sport type found with id: " + id));
        return mapper.mapToResponse(sportType);
    }

    public List<SportTypeResponse> getAllSportTypes() {
        return sportTypeRepository.findAll()
                .stream()
                .map(mapper::mapToResponse)
                .toList();
    }

//    Todo Test this method in postman!
    public List<SportTypeResponse> getAllSportTypesByAccountId(Long accountId) {
        return sportTypeRepository.findAllByAccount_Id((accountId))
                .stream()
                .map(mapper::mapToResponse)
                .toList();
    }
}
