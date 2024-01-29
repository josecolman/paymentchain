package com.biagab.transaction.repositories;

import com.biagab.transaction.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    List<TransactionEntity> findByIbanAccount(String ibanAccount);

}
