package com.biagab.customer.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CustomerProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long productId;

    @Transient
    private String productName;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = CustomerEntity.class)
    @JoinColumn(name = "customerId", nullable = true)
    private CustomerEntity customer;

}
