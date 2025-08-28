package com.emcode.aikinetics.sporttype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public  interface SportTypeRepository extends JpaRepository<SportType, Long> {

    boolean existsByKeyNameIgnoreCaseAndAccount_Id(String keyName, Long accountId);

    List<SportType> findAllByAccount_Id(Long accountId);

}