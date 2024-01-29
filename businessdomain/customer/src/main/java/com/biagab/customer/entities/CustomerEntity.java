package com.biagab.customer.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String code;
    private String name;
    private String phone;
    /**
     * International Bank Account Number
     */
    private String iban;
    private String address;
    private String surname;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerProductEntity> products;

    @Transient
    private List<?> transactions;

}
