package com.miniproject.backend.service.impl;

import com.miniproject.backend.dto.TransactionDetailsDTO;
import com.miniproject.backend.dto.TransactionsDTO;
import com.miniproject.backend.model.Products;
import com.miniproject.backend.model.TransactionDetails;
import com.miniproject.backend.model.Transactions;
import com.miniproject.backend.repository.ProductsRepository;
import com.miniproject.backend.repository.TransactionsRepository;
import com.miniproject.backend.service.TransactionsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionsServiceImpl implements TransactionsService {

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public TransactionsDTO getTransactionById(Integer id) {
        Transactions transaction = transactionsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + id));
        return convertToDTO(transaction);
    }

    @Override
    public List<TransactionsDTO> getAllTransactions() {
        List<Transactions> transactionsList = transactionsRepository.findAll();
        List<TransactionsDTO> transactionsDTOList = new ArrayList<>();
        for (Transactions transaction : transactionsList) {
            transactionsDTOList.add(convertToDTO(transaction));
        }
        return transactionsDTOList;
    }

    @Override
    public void addTransaction(TransactionsDTO transactionsDTO) {
        Transactions transaction = convertToEntity(transactionsDTO);
        transactionsRepository.save(transaction);
    }

    private TransactionsDTO convertToDTO(Transactions transaction) {
        TransactionsDTO dto = new TransactionsDTO();
        dto.setId(transaction.getId());
        dto.setTransactionDate(transaction.getTransactionDate());
        dto.setTotalAmount(transaction.getTotalAmount());
        dto.setTotalPay(transaction.getTotalPay());

        List<TransactionDetailsDTO> transactionDetailsDTOList = new ArrayList<>();
        for (TransactionDetails transactionDetail : transaction.getTransactionDetails()) {
            TransactionDetailsDTO detailDTO = new TransactionDetailsDTO();
            detailDTO.setProduct_id(transactionDetail.getProducts().getId());
            detailDTO.setQuantity(transactionDetail.getQuantity());
            detailDTO.setSubtotal(transactionDetail.getSubTotal());
            transactionDetailsDTOList.add(detailDTO);
        }
        dto.setTransactionDetails(transactionDetailsDTOList);

        return dto;
    }

    private Transactions convertToEntity(TransactionsDTO transactionsDTO) {
        Transactions transaction = new Transactions();
        transaction.setTotalAmount(transactionsDTO.getTotalAmount());
        transaction.setTotalPay(transactionsDTO.getTotalPay());
        transaction.setTransactionDate(LocalDate.now());

        List<TransactionDetails> transactionDetailsList = new ArrayList<>();
        for (TransactionDetailsDTO detailDTO : transactionsDTO.getTransactionDetails()) {
            TransactionDetails transactionDetail = new TransactionDetails();
            transactionDetail.setQuantity(detailDTO.getQuantity());
            transactionDetail.setSubTotal(detailDTO.getSubtotal());

            Products product = productsRepository.findById(detailDTO.getProduct_id())
                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + detailDTO.getProduct_id()));
            transactionDetail.setProducts(product);
            transactionDetail.setTransactions(transaction);
            transactionDetailsList.add(transactionDetail);
        }
        transaction.setTransactionDetails(transactionDetailsList);

        return transaction;
    }
}

