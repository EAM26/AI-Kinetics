package com.emcode.aikinetics.domain.service;

import com.emcode.aikinetics.api.dto.sporttype.SportTypeRequest;
import com.emcode.aikinetics.api.dto.sporttype.SportTypeResponse;
import com.emcode.aikinetics.api.mapper.SportTypeMapper;
import com.emcode.aikinetics.domain.model.SportType;
import com.emcode.aikinetics.repository.SportTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class SportTypeService {

    private final SportTypeMapper mapper;
    private final SportTypeRepository sportTypeRepository;

    public SportTypeService(SportTypeMapper mapper, SportTypeRepository sportTypeRepository) {
        this.mapper = mapper;
        this.sportTypeRepository = sportTypeRepository;
    }

    public SportTypeResponse createSportType(SportTypeRequest request) {

        SportType sportType = sportTypeRepository.save(mapper.mapToEntity(request));
        return mapper.mapToResponse(sportType);

    }
}
