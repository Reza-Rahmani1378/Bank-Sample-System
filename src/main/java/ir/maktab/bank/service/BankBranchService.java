package ir.maktab.bank.service;

import ir.maktab.bank.base.service.BaseService;
import ir.maktab.bank.domain.BankBranch;

import java.util.List;

public interface BankBranchService extends BaseService<BankBranch, Long> {
    List<BankBranch> findAllPerPage(int page , int size);
}
