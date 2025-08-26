package com.emcode.aikinetics.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class SportType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String keyName;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;



}
