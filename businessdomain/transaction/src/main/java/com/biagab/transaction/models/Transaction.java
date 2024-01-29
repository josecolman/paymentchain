package com.biagab.transaction.models;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Transaction {

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
