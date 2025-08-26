package com.emcode.aikinetics.repository;

import com.emcode.aikinetics.domain.model.SportType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SportTypeRepository extends JpaRepository<SportType, Long> {

    boolean existsByKeyNameIgnoreCaseAndAccount_Id(String keyName, Long accountId);

    List<SportType> findAllByAccount_Id(Long accountId);

}
