package ir.maktab.bank.service.impl;

import ir.maktab.bank.base.service.impl.BaseServiceImpl;
import ir.maktab.bank.domain.BankBranch;
import ir.maktab.bank.repository.BankBranchRepository;
import ir.maktab.bank.service.BankBranchService;

import java.util.List;

public class BankBranchServiceImpl extends BaseServiceImpl<BankBranch, Long, BankBranchRepository> implements
        BankBranchService {

    public BankBranchServiceImpl(BankBranchRepository repository) {
        super(repository);
    }

    @Override
    public List<BankBranch> findAllPerPage(int page, int size) {
        return repository.findAllPerPage(page , size);
    }
}
