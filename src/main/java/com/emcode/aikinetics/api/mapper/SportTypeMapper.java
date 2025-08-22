package com.emcode.aikinetics.api.mapper;

import com.emcode.aikinetics.api.dto.sporttype.SportTypeRequest;
import com.emcode.aikinetics.api.dto.sporttype.SportTypeResponse;
import com.emcode.aikinetics.api.error.NotFoundException;
import com.emcode.aikinetics.domain.model.Account;
import com.emcode.aikinetics.domain.model.SportType;
import com.emcode.aikinetics.repository.AccountRepository;
import org.springframework.stereotype.Component;

@Component
public class SportTypeMapper {
    private final AccountRepository accountRepository;

    public SportTypeMapper(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public SportType mapToEntity(SportTypeRequest request) {
        SportType sportType = new SportType();
        sportType.setKeyName(request.keyName());
        Account account = accountRepository.findById(request.accountId()).orElseThrow(() ->
                new NotFoundException("No account found with id: " + request.accountId()));
        sportType.setAccount(account);
        return sportType;
    }

    public SportTypeResponse mapToResponse(SportType sportType) {
        return new SportTypeResponse(sportType.getId(), sportType.getKeyName(),
                sportType.getAccount().getId());

    }
}
