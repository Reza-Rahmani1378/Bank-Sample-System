package ir.maktab.bank.service;

import ir.maktab.bank.base.service.BaseService;
import ir.maktab.bank.domain.Account;
import ir.maktab.bank.domain.BankBranch;

import java.util.List;

public interface AccountService extends BaseService<Account, Long> {

    List<Account> getAccountsByBranch(BankBranch branch);


    List<Account> findAllPerPage(int page , int size , BankBranch branch);


}
