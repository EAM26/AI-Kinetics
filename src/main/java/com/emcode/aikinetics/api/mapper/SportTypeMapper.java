package com.emcode.aikinetics.api.mapper;

import com.emcode.aikinetics.api.dto.sporttype.SportTypeRequest;
import com.emcode.aikinetics.api.dto.sporttype.SportTypeResponse;
import com.emcode.aikinetics.domain.model.Account;
import com.emcode.aikinetics.domain.model.SportType;
import org.springframework.stereotype.Component;

@Component
public class SportTypeMapper {


    public SportType mapToEntity(SportTypeRequest request, Account acc) {
        SportType sportType = new SportType();
        sportType.setKeyName(request.keyName());
        sportType.setAccount(acc);
        return sportType;
    }

    public SportTypeResponse mapToResponse(SportType sportType) {
        return new SportTypeResponse(sportType.getId(), sportType.getKeyName(),
                sportType.getAccount().getId());

    }
}
