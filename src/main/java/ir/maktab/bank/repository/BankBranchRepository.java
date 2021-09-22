package ir.maktab.bank.repository;

import ir.maktab.bank.base.repository.BaseRepository;
import ir.maktab.bank.domain.BankBranch;

import java.util.List;

public interface BankBranchRepository extends BaseRepository<BankBranch, Long> {
    List<BankBranch> findAllPerPage(int page, int size);
}
