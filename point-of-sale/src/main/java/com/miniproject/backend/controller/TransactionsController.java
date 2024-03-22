package com.miniproject.backend.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniproject.backend.dto.TransactionsDTO;
import com.miniproject.backend.dto.TransactionDetailsDTO;
import com.miniproject.backend.model.Transactions;
import com.miniproject.backend.model.TransactionDetails;
import com.miniproject.backend.response.ResponseModel;
import com.miniproject.backend.service.TransactionsService;
import com.miniproject.backend.service.ProductsService;
import com.miniproject.backend.service.TransactionDetailsService;

@RestController
@RequestMapping("/pos/api")
public class TransactionsController {

    @Autowired
    private TransactionsService transactionsService;

    @Autowired
    private ProductsService productService;

    @Autowired
    private TransactionDetailsService transactionDetailsService;

    @PostMapping("/addtransaction")
    public ResponseModel<TransactionsDTO> addTransaction(@RequestBody TransactionsDTO request) {
        Transactions transactions = convertToTransactionsEntity(request);
        transactionsService.addTransactions(transactions);
        
        
        if (request.getTransactionDetails() != null) {
            for (TransactionDetailsDTO detailDTO : request.getTransactionDetails()) {
                TransactionDetails transactionDetails = convertToTransactionDetailsEntity(detailDTO, transactions);
                transactionDetailsService.addTransactionDetails(transactionDetails);
                transactions.getTransactionDetails().add(transactionDetails);
            }
        }
        

        ResponseModel<TransactionsDTO> response = new ResponseModel<>();
        response.setStatus("ok");
        response.setMessage("success");
        return response;
    }


    private Transactions convertToTransactionsEntity(TransactionsDTO dto) {
        Transactions transactions = new Transactions();
        transactions.setTotalAmount(dto.getTotalAmount());
        transactions.setTotalPay(dto.getTotalPay());
        transactions.setTransactionDate(LocalDate.now());
        return transactions;
    }

    private TransactionDetails convertToTransactionDetailsEntity(TransactionDetailsDTO dto, Transactions transactions) {
        TransactionDetails transactionDetails = new TransactionDetails();
        transactionDetails.setProducts(productService.getProductById(dto.getProductId()));
        transactionDetails.setQuantity(dto.getQuantity());
        transactionDetails.setSubTotal(dto.getSubTotal());
        transactionDetails.setTransactions(transactions);
        return transactionDetails;
    }

}
