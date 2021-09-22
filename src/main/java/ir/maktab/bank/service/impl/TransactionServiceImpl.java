package ir.maktab.bank.service.impl;

import ir.maktab.bank.base.service.impl.BaseServiceImpl;
import ir.maktab.bank.domain.Transaction;
import ir.maktab.bank.repository.TransactionRepository;
import ir.maktab.bank.service.TransactionService;

public class TransactionServiceImpl extends BaseServiceImpl<Transaction, Long, TransactionRepository>
        implements TransactionService {

    public TransactionServiceImpl(TransactionRepository repository) {
        super(repository);
    }
}
