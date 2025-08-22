package com.emcode.aikinetics.repository;

import com.emcode.aikinetics.domain.model.Account;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByName(String name);

    boolean existsByEmail(@Email String email);
}
