package com.miniproject.backend.service;

import java.util.List;

import com.miniproject.backend.model.TransactionDetails;

public interface TransactionDetailsService {

    void addTransactionDetails(TransactionDetails transactionDetails);
    
    void addTransactionDetails(List<TransactionDetails> transactionDetailsList);
}
