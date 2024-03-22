package com.miniproject.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject.backend.model.TransactionDetails;
import com.miniproject.backend.repository.TransactionDetailsRepository;
import com.miniproject.backend.service.TransactionDetailsService;

@Service
public class TransactionDetailsServiceImpl implements TransactionDetailsService {

    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;

    @Override
    public void addTransactionDetails(TransactionDetails transactionDetails) {
        transactionDetailsRepository.save(transactionDetails);
    }

    @Override
    public void addTransactionDetails(List<TransactionDetails> transactionDetailsList) {
        transactionDetailsRepository.saveAll(transactionDetailsList);
    }
}
