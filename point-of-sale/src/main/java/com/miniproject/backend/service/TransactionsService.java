package com.miniproject.backend.service;

import java.util.List;

import com.miniproject.backend.model.Transactions;

public interface TransactionsService {

    List<Transactions> getAllTransactions();
    Transactions getTransactionById(Integer id);
    void addTransactions(Transactions transaction);
    
}


