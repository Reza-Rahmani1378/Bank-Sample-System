package ir.maktab.bank.service.impl;

import ir.maktab.bank.base.service.impl.BaseServiceImpl;
import ir.maktab.bank.domain.Account;
import ir.maktab.bank.domain.BankBranch;
import ir.maktab.bank.repository.AccountRepository;
import ir.maktab.bank.service.AccountService;

import java.util.List;

public class AccountServiceImpl extends BaseServiceImpl<Account, Long, AccountRepository> implements
        AccountService {

    public AccountServiceImpl(AccountRepository repository) {
        super(repository);
    }

    @Override
    public List<Account> getAccountsByBranch(BankBranch branch) {
        return repository.getAccountsByBranch(branch);
    }

    @Override
    public List<Account> findAllPerPage(int page, int size, BankBranch branch) {
        return repository.findAllPerPage(page , size , branch);
    }
}
