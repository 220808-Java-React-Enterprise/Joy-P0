package com.revature.blazinhot.services;

import com.revature.blazinhot.daos.TransactionDAO;
import com.revature.blazinhot.models.Transaction;

public class TransactionService {
    private final TransactionDAO transactionDAO;

    public TransactionService(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    public void saveTransaction(Transaction transaction) {
        transactionDAO.save(transaction);
    }
}
