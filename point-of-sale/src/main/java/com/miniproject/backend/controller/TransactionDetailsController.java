//package com.miniproject.backend.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.miniproject.backend.dto.TransactionDTO;
//import com.miniproject.backend.model.Transaction;
//import com.miniproject.backend.model.TransactionDetail;
//import com.miniproject.backend.response.ResponseModel;
//import com.miniproject.backend.service.TransactionService;
//
//@RestController
//@RequestMapping("/pos/api")
//public class TransactionDetailsController {
//
//    @Autowired
//    private TransactionService transactionService;
//
//    @PostMapping("/addtransaction")
//    public ResponseModel<TransactionDTO> addTransaction(@RequestBody TransactionDTO request) {
//        Transaction transaction = convertToEntity(request);
//        
//        transactionService.addTransaction(transaction);
//
//        if (request.getTransactionDetails() != null) {
//            for (TransactionDetailDTO detailDTO : request.getTransactionDetails()) {
//                TransactionDetail detailEntity = convertToTransactionDetailEntity(detailDTO);
//                detailEntity.setTransaction(transaction); 
//                transactionService.addTransactionDetail(detailEntity);
//            }
//        }
//
//        ResponseModel<TransactionDTO> response = new ResponseModel<>();
//        response.setStatus("ok");
//        response.setMessage("success");
//        return response;
//    }
//
//    private Transaction convertToEntity(TransactionDTO dto) {
//        Transaction transaction = new Transaction();
//        transaction.setTotalAmount(dto.getTotalAmount());
//        transaction.setTotalPay(dto.getTotalPay());
//        return transaction;
//    }
//
//    private TransactionDetail convertToTransactionDetailEntity(TransactionDetailDTO dto) {
//        TransactionDetail detail = new TransactionDetail();
//        detail.setProductId(dto.getProductId());
//        detail.setQuantity(dto.getQuantity());
//        detail.setSubtotal(dto.getSubtotal());
//        return detail;
//    }
//}
