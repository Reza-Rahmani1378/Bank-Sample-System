package ir.maktab.bank.service;

import ir.maktab.bank.base.service.BaseService;
import ir.maktab.bank.domain.BankBranch;
import ir.maktab.bank.domain.Employee;

import java.util.List;

public interface EmployeeService extends BaseService<Employee, Long> {

    List<Employee> findAllByBranchId(BankBranch branch);

    List<Employee> findAllPerPage(int page , int size , BankBranch branch);

}
