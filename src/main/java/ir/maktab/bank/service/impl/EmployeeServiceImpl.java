package ir.maktab.bank.service.impl;

import ir.maktab.bank.base.service.impl.BaseServiceImpl;
import ir.maktab.bank.domain.BankBranch;
import ir.maktab.bank.domain.Employee;
import ir.maktab.bank.repository.EmployeeRepository;
import ir.maktab.bank.service.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl extends BaseServiceImpl<Employee, Long, EmployeeRepository>
        implements EmployeeService {
    public EmployeeServiceImpl(EmployeeRepository repository) {
        super(repository);
    }

    @Override
    public List<Employee> findAllByBranchId(BankBranch branch) {
        return repository.findAllByBranchId(branch);
    }

    @Override
    public List<Employee> findAllPerPage(int page, int size , BankBranch branch) {
        return repository.findAllPerPage(page , size , branch);
    }
}
