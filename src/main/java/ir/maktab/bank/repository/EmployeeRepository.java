package ir.maktab.bank.repository;

import ir.maktab.bank.base.repository.BaseRepository;
import ir.maktab.bank.domain.BankBranch;
import ir.maktab.bank.domain.Employee;

import java.util.List;

public interface EmployeeRepository extends BaseRepository<Employee, Long> {

    List<Employee> findAllByBranchId(BankBranch branch);

    List<Employee> findAllPerPage(int page, int size , BankBranch bankBranch);

}
