package com.biagab.transaction.services;

import com.biagab.transaction.entities.TransactionEntity;
import com.biagab.transaction.models.Transaction;
import com.biagab.transaction.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public long create(Transaction model) {
        TransactionEntity entity = mapToEntity(model);
        return transactionRepository.save(entity).getId();
    }

    public Transaction read(long id) {
        TransactionEntity entity = transactionRepository.findById(id).orElse(null);
        return mapToModel(entity);
    }

    public void update(long id, Transaction model) {
        TransactionEntity entity = transactionRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(model, entity);
        transactionRepository.save(entity);
    }

    public void delete(long id) {
        transactionRepository.deleteById(id);
    }

    public List<Transaction> list(String ibanAccount) {
        return transactionRepository
                .findAll()
                .stream()
                .map(this::mapToModel)
                .collect(Collectors.toList());
    }

    public TransactionEntity mapToEntity(Transaction model) {
        if (model == null)
            return null;
        TransactionEntity entity = new TransactionEntity();
        BeanUtils.copyProperties(model, entity);
        return entity;
    }

    public Transaction mapToModel(TransactionEntity entity) {
        if (entity == null)
            return null;
        Transaction model = new Transaction();
        BeanUtils.copyProperties(entity, model);
        return model;
    }

}
