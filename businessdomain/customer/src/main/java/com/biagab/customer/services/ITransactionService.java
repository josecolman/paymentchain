package com.biagab.customer.services;

import java.util.List;

public interface ITransactionService {

    List<?> getTransactionsByCustomerIBAN(String iban);
}
