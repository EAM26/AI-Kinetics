package com.emcode.aikinetics.domain.service;

import com.emcode.aikinetics.api.dto.sporttype.SportTypeRequest;
import com.emcode.aikinetics.api.dto.sporttype.SportTypeResponse;
import com.emcode.aikinetics.api.error.DuplicateFieldException;
import com.emcode.aikinetics.api.error.NotFoundException;
import com.emcode.aikinetics.api.mapper.SportTypeMapper;
import com.emcode.aikinetics.domain.model.Account;
import com.emcode.aikinetics.domain.model.SportType;
import com.emcode.aikinetics.repository.AccountRepository;
import com.emcode.aikinetics.repository.SportTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportTypeService {

    private final SportTypeMapper mapper;
    private final SportTypeRepository sportTypeRepository;
    private final AccountRepository accountRepository;

    public SportTypeService(SportTypeMapper mapper, SportTypeRepository sportTypeRepository, AccountRepository accountRepository) {
        this.mapper = mapper;
        this.sportTypeRepository = sportTypeRepository;
        this.accountRepository = accountRepository;
    }

    public SportTypeResponse createSportType(SportTypeRequest request) {
        Account account = accountRepository.findById(request.accountId()).orElseThrow(() -> new NotFoundException("No account found with id: " + request.accountId()));

        if (sportTypeRepository.existsByKeyNameIgnoreCaseAndAccount_Id(request.keyName(), request.accountId())) {
            throw new DuplicateFieldException(request.keyName() + " already exists.");
        }
        SportType sportType = sportTypeRepository.save(mapper.mapToEntity(request, account));
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
