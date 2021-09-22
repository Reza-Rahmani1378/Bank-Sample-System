package ir.maktab.bank.util;

import ir.maktab.bank.repository.*;
import ir.maktab.bank.repository.impl.*;
import ir.maktab.bank.service.*;
import ir.maktab.bank.service.impl.*;

import javax.persistence.EntityManager;

public class ApplicationContext {

    private ApplicationContext() {
    }

    private static final AccountRepository accountRepository;
    private static final AccountService accountService;
    private static final BankBranchRepository BANK_BRANCH_REPOSITORY;
    private static final BankBranchService BANK_BRANCH_SERVICE;
    private static final CreditCardRepository creditCardRepository;
    private static final CreditCardService creditCardService;
    private static final CustomerRepository customerRepository;
    private static final CustomerService customerService;
    private static final EmployeeRepository employeeRepository;
    private static final EmployeeService employeeService;
    private static final TransactionRepository TRANSACTION_REPOSITORY;
    private static final TransactionService TRANSACTION_SERVICE;
    private static final BossRepository bossRepository;
    private static final BossService bossService;
    private static final UserRepository userRepository;
    private static final UserService userService;


    static {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        accountRepository = new AccountRepositoryImpl(entityManager);
        accountService = new AccountServiceImpl(accountRepository);

        BANK_BRANCH_REPOSITORY = new BankBranchRepositoryImpl(entityManager);
        BANK_BRANCH_SERVICE = new BankBranchServiceImpl(BANK_BRANCH_REPOSITORY);

        creditCardRepository = new CreditCardRepositoryImpl(entityManager);
        creditCardService = new CreditCardServiceImpl(creditCardRepository);

        customerRepository = new CustomerRepsitoryImpl(entityManager);
        customerService = new CustomerServiceImpl(customerRepository);

        employeeRepository = new EmployeeRepsitoryImpl(entityManager);
        employeeService = new EmployeeServiceImpl(employeeRepository);

        TRANSACTION_REPOSITORY = new TransactionRepositoryImpl(entityManager);
        TRANSACTION_SERVICE = new TransactionServiceImpl(TRANSACTION_REPOSITORY);

        bossRepository = new BossRepositoryImpl(entityManager);
        bossService = new BossServiceImpl(bossRepository);

        userRepository = new UserRepositoryImpl(entityManager);
        userService = new UserServiceImpl(userRepository);
    }

    public static UserService getUserService() {
        return userService;
    }

    public static EmployeeService getEmployeeService() {
        return employeeService;
    }

    public static CreditCardService getCreditCardService() {
        return creditCardService;
    }

    public static CustomerService getCustomerService() {
        return customerService;
    }

    public static BossService getBossService() {
        return bossService;
    }

    public static BankBranchService getBankBranchService() {
        return BANK_BRANCH_SERVICE;
    }

    public static TransactionService getLogService() {
        return TRANSACTION_SERVICE;
    }

    public static AccountService getAccountService() {
        return accountService;
    }

}
