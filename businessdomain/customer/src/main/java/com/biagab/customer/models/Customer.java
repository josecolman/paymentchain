package com.biagab.customer.models;

import com.biagab.customer.entities.CustomerProductEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class Customer {

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

    private List<CustomerProduct> products;
    private List<?> transactions;

}
