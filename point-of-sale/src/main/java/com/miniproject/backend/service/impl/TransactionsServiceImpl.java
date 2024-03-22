package com.miniproject.backend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject.backend.model.Transactions;
import com.miniproject.backend.repository.TransactionsRepository;
import com.miniproject.backend.service.TransactionsService;

@Service
public class TransactionsServiceImpl implements TransactionsService {

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Override
    public List<Transactions> getAllTransactions() {
        return transactionsRepository.findAll();
    }

    @Override
    public Transactions getTransactionById(Integer id) {
        Optional<Transactions> transactionOptional = transactionsRepository.findById(id);
        if (transactionOptional.isPresent()) {
            return transactionOptional.get();
        } else {
            throw new RuntimeException("Transaction not found with ID: " + id);
        }
    }

    @Override
    public void addTransactions(Transactions transaction) {
        transactionsRepository.save(transaction);
    }
}

