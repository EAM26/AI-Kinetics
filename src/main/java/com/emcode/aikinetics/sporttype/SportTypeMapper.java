package com.emcode.aikinetics.sporttype;

import com.emcode.aikinetics.account.Account;
import com.emcode.aikinetics.sporttype.dto.SportTypeRequest;
import com.emcode.aikinetics.sporttype.dto.SportTypeResponse;
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
