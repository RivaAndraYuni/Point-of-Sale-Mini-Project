package com.miniproject.backend.controller;

import com.miniproject.backend.dto.TransactionsDTO;
import com.miniproject.backend.service.TransactionsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pos/api")
public class TransactionsController {

    @Autowired
    private TransactionsService transactionsService;

    @GetMapping("/listtransactions")
    public ResponseEntity<List<TransactionsDTO>> getAllTransactions() {
        List<TransactionsDTO> transactions = transactionsService.getAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
    
    @GetMapping("/transaction/{id}")
    public ResponseEntity<TransactionsDTO> getTransactionById(@PathVariable Integer id) {
        TransactionsDTO transaction = transactionsService.getTransactionById(id);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }
        
    @PostMapping("/addtransaction")
    public ResponseEntity<Void> addTransaction(@RequestBody TransactionsDTO transactionsDTO) {
        transactionsService.addTransaction(transactionsDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
}
