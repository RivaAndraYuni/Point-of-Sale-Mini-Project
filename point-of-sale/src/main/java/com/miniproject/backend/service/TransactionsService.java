package com.miniproject.backend.service;

import com.miniproject.backend.dto.TransactionsDTO;

import java.util.List;

public interface TransactionsService {
    TransactionsDTO getTransactionById(Integer id);
    List<TransactionsDTO> getAllTransactions();
    void addTransaction(TransactionsDTO transactionsDTO);
}
