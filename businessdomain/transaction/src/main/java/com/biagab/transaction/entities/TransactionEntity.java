package com.biagab.transaction.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String reference;
    private String ibanAccount;
    private LocalDateTime date;
    private String description;
    private BigDecimal amount;
    private BigDecimal fee;
    private String status;
    private String channel;

}
