package ir.maktab.bank.util;

import ir.maktab.bank.domain.*;
import ir.maktab.bank.domain.User;
import ir.maktab.bank.domain.enumeration.UserType;
import ir.maktab.bank.service.AccountService;
import ir.maktab.bank.util.input.Input;
import ir.maktab.bank.util.menu.CheckMenu;
import ir.maktab.bank.util.menu.Menu;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RunMenu {

    private static Account customerAccount;
    private static String destinationCardNumber;
    private static double transferCredit;

    public static void runSystem() {
        login();
        System.out.println("Welcome to your workbench : "
                + "|" + SecurityContext.getActiveUser().getFirstname()
                + " " + SecurityContext.getActiveUser().getLastname()
                + "|");
        mainMenu();
    }

    private static void login() {


        while (true) {
            String username = new Input("Enter your username :").getInputString();
            String password = new Input("Enter your password").getInputString();
            if (ApplicationContext.getUserService().existByUsername(username)) {
                User user = ApplicationContext.getUserService().getUserByUsername(username);
                if (user.getPassword().equals(password)) {
                    SecurityContext.setActiveUser(user);
                    break;
                } else {
                    System.out.println("Your Password or username is incorrect...");
                }
            } else {
                System.out.println("User Not found...");
            }
        }

    }

    private static void mainMenu() {
        UserType userType = SecurityContext.getActiveUser().getUsertype();
        User activeUser = SecurityContext.getActiveUser();
        switch (userType) {
            case ADMIN -> adminMenu();
            case BOSS -> {
                Boss activeBoss =
                        ApplicationContext.getBossService().findById(activeUser.getId());
                bossMenu(activeBoss);
            }
            case EMPLOYEE -> {
                Employee activeEmployee =
                        ApplicationContext.getEmployeeService().findById(activeUser.getId());
                employeeMenu(activeEmployee);
            }
            case CUSTOMER -> {
                Customer activeCustomer =
                        ApplicationContext.getCustomerService().findById(activeUser.getId());
                customerMenu(activeCustomer);
            }
        }
    }

    private static void adminMenu() {
        Menu menu = new Menu();
        menu.setItems(new String[]{"Add Branch", "Delete Branch", "Log out"});
        while (true) {

            menu.print();

            switch (menu.chooseOperation()) {
                case 1:
                    addBranch();
                    break;
                case 2:
                    deleteBranch();
                    break;
                case 3:
                    return;
            }
        }
    }

    private static void addBranch() {

        ApplicationContext.getBankBranchService().save(
                new BankBranch(
                        InputInformation.getCodeBranch(),
                        InputInformation.getBranchName(),
                        getInformationBoss()));
    }

    private static Boss getInformationBoss() {
        System.out.println("Enter Boss Of Branch.");
        return new Boss(
                InputInformation.getFirstName(),
                InputInformation.getLastName(),
                InputInformation.getNationalCode(),
                InputInformation.getBirthDay(),
                InputInformation.getUsername(),
                InputInformation.getPassword(),
                UserType.BOSS);

    }

    private static void deleteBranch() {
        selectBranch();

    }

    private static void selectBranch() {
        int pageSize = 5;
        for (int page = 0; ; ) {
            List<BankBranch> bankBranches = ApplicationContext.getBankBranchService().findAllPerPage(page, pageSize);
            for (int index = 1; index <= bankBranches.size(); index++) {
//                System.out.format("%d %s" , index , bankBranches.get(index -1).toString());
                System.out.println(bankBranches.get(index - 1).toString());
            }
            int choice;
            if (page == 0) {
                choice = new Input("""
                        1: back to menu
                        2: next page
                        Enter id of Branch:""",
                        1, pageSize, null).intSetValue();
            } else {
                choice = new Input(

                        """
                                1: back to main menu
                                2: next page
                                3: previous page
                                Enter id of Branch:""",
                        1, pageSize, null).intSetValue();
            }
            if (choice == 1)
                break;
            else if (choice == 2)
                page++;
            else if (choice == 3)
                page--;
            else {
                try {

                    System.out.println(" Are You Sure  Remove This Branch ?");
                    boolean check = new CheckMenu().check();

                    if (check) {
                        ApplicationContext.getBankBranchService().delete(bankBranches.get(choice - 1));
                        System.out.println(" Branch Deleted Successfully");
                    }
                } catch (Exception e) {
                    System.out.println("is not exist this branch");
                }
            }

        }
    }

    private static void bossMenu(Boss activeBoss) {
        Menu menu = new Menu();
        menu.setItems(new String[]{"Add Employee(s)",
                "Remove Employee(s)",
                "Show My Employees", "Show My Customers", "Log out"}
        );
        while (true) {
            menu.print();

            switch (menu.chooseOperation()) {
                case 1:
                    addEmployee(activeBoss);
                    break;
                case 2:
//                    deleteEmployee(activeBoss);
                    removeEmployee(activeBoss);
                    break;
                case 3:
//                    viewBranchEmployees(activeBoss);
                    showBranchEmployees(activeBoss);
                    break;
                case 4:
//                    viewBranchCustomers(activeBoss);
                    showBranchCustomers(activeBoss);
                    break;
                case 5:
                    return;
            }
        }
    }

    private static void addEmployee(Boss activeBoss) {
        ApplicationContext.getEmployeeService().save(
                new Employee(
                        InputInformation.getFirstName(),
                        InputInformation.getLastName(),
                        InputInformation.getNationalCode(),
                        InputInformation.getBirthDay(),
                        InputInformation.getUsername(),
                        InputInformation.getPassword(),
                        UserType.EMPLOYEE,
                        activeBoss.getBranch()));

    }

    private static void deleteEmployee(Boss activeBoss) {
        selectEmployee(activeBoss);
    }

    private static void selectEmployee(Boss activeBoss) {
        int pageSize = 5;
        for (int page = 0; ; ) {
            List<Employee> employees = getAllPerPageEmployee(activeBoss, pageSize, page);
            for (int index = 1; index < employees.size(); index++) {
                System.out.printf("%d %s", index, employees.get(index - 1));
            }
            int choice;
            if (page == 0) {
                choice = new Input("""
                        1: back to menu
                        2: next page
                        Enter id of user:""",
                        1, pageSize, null).intSetValue();
            } else {
                choice = new Input(

                        """
                                1: back to main menu
                                2: next page
                                3: previous page
                                Enter id of user:""",
                        1, pageSize, null).intSetValue();
            }
            if (choice == 1)
                break;
            else if (choice == 2)
                page++;
            else if (choice == 3)
                page--;
            else {
                try {

                    System.out.println(" Are You Sure  Remove This Employee ?");
                    boolean check = new CheckMenu().check();

                    if (check) {
                        ApplicationContext.getEmployeeService().delete(employees.get(choice - 1));
                        System.out.println(" Employee Deleted Successfully");
                    }
                } catch (Exception e) {
                    System.out.println("not exist this id.");
                }
            }

        }
    }

    private static void removeEmployee(Boss activeBoss) {
        long id = 0;
        List<Employee> employees = ApplicationContext
                .getEmployeeService().findAllByBranchId(activeBoss.getBranch());
        viewBranchEmployees(activeBoss);

        id = new Input("enter id of employee you wanna remove :",Long.MAX_VALUE,0L).getInputLong();
        long finalId = id;

        try {
            Employee employee = employees.stream().filter(em -> em.getId() == finalId)
                    .collect(Collectors.toList()).get(0);

            System.out.println("Are you sure you wanna remove this employee ?");
            boolean check = new CheckMenu().check();
            if (check) {
                ApplicationContext.getEmployeeService().delete(employee);
                System.out.println("employee deleted successfully");
            }
        } catch (Exception e) {
            System.out.println("wrong id");
        }
    }

    private static void viewBranchEmployees(Boss activeBoss) {
        int pageSize = 5;
        for (int page = 0; ; ) {
            List<Employee> employees = getAllPerPageEmployee(activeBoss, pageSize, page);
            for (int index = 1; index < employees.size(); index++) {
                System.out.printf("%d %s", index, employees.get(index - 1));
            }
            int choice;
            if (page == 0) {
                choice = new Input("""
                        1: back to menu
                        2: next page
                        """,
                        1, pageSize, null).intSetValue();
            } else {
                choice = new Input(

                        """
                                1: back to main menu
                                2: next page
                                3: previous page
                                """,
                        1, pageSize, null).intSetValue();
            }
            if (choice == 1)
                break;
            else if (choice == 2)
                page++;
            else if (choice == 3)
                page--;


        }

    }

    public static void showBranchEmployees(Boss bossActive) {
        List<Employee> employees = ApplicationContext
                .getEmployeeService().findAllByBranchId(bossActive.getBranch());
        employeeViewList(employees);
    }

    public static void employeeViewList(List<Employee> employees){
        for (Employee employee : employees){
            System.out.println(
                    "ID : " + employee.getId() + "\n" +
                            "First Name : " + employee.getFirstname() + "\n" +
                            "Last Name : " + employee.getLastname() + "\n" +
                            "National Code : " + employee.getNationalCode() + "\n" +
                            "Birthday : " + employee.getBirthday() + "\n"
            );
        }

    }

    private static List<Employee> getAllPerPageEmployee(Boss activeBoss, int pageSize, int page) {
        return ApplicationContext.getEmployeeService().findAllPerPage(page, pageSize, activeBoss.getBranch());
    }


    private static void viewBranchCustomers(Boss activeBoss) {
        int pageSize = 5;
        for (int page = 0; ; ) {
            List<Account> accounts = ApplicationContext.getAccountService().findAllPerPage(page, pageSize, activeBoss.getBranch());
            List<Customer> customers = new ArrayList<>();
            for (Account account : accounts) {
                customers.add(account.getCustomer());
            }
            for (int index = 0; index < customers.size(); index++) {
                System.out.printf("%d %s", index + 1, customers.get(index));
            }

            int choice;
            if (page == 0) {
                choice = new Input("""
                        1: back to menu
                        2: next page
                        """,
                        1, pageSize, null).intSetValue();
            } else {
                choice = new Input(

                        """
                                1: back to main menu
                                2: next page
                                3: previous page
                                """,
                        1, pageSize, null).intSetValue();
            }
            if (choice == 1)
                break;
            else if (choice == 2)
                page++;
            else if (choice == 3)
                page--;


        }
    }

    public static void showBranchCustomers(Boss activeBoss){
        List<Account> accounts = ApplicationContext
                .getAccountService().getAccountsByBranch(activeBoss.getBranch());
        List<Customer> customers = new ArrayList<>();
        for (Account account : accounts) {
            customers.add(account.getCustomer());
        }
        customerViewList(customers);
    }


    private static void customerMenu(Customer activeCustomer) {
        Menu menu = new Menu();
        menu.setItems(new String[]{"Edit your profile", "Add/Edit second Password of Credit Card", "Transfer money to card","Delete Account", "Log out"}
        );
        while (true) {
            menu.print();
            switch (menu.chooseOperation()) {
                case 1:
                    editProfile(activeCustomer);
                    activeCustomer = ApplicationContext.getCustomerService().findById(activeCustomer.getId());
                    break;
                case 2:
                    setOrChangeSecondPassword(activeCustomer);
                    break;
                case 3:
                    transferMoney(activeCustomer);
                    break;
                case 4:
                    deleteAccountCustomer(activeCustomer);
                case 5:
                    return;
            }
        }

    }

    private static void deleteAccountCustomer(Customer activeCustomer) {
        List<Account> accounts =
                ApplicationContext.getAccountService().getAccountsByCustomerId(activeCustomer.getId());

    }

    private static void editProfile(Customer activeCustomer) {
        customerProfile(activeCustomer);
        Menu menu = new Menu();
        menu.setItems(new String[]{"Edit First Name", "Edit Last Name", "Edit User Name", "Edit National Code", "Edit Birthday", "Back"});
        while (true) {
            menu.print();
            switch (menu.chooseOperation()) {
                case 1:
                    activeCustomer.setFirstname(InputInformation.getFirstName());
                    break;
                case 2:
                    activeCustomer.setLastname(InputInformation.getLastName());
                    break;
                case 3:
                    activeCustomer.setUsername(InputInformation.getUsername());
                    break;
                case 4:
                    activeCustomer.setNationalCode(InputInformation.getNationalCode());
                    break;
                case 5:
                    activeCustomer.setBirthday(InputInformation.getBirthDay());
                case 6:
                    ApplicationContext.getCustomerService().save(activeCustomer);
                    return;

            }
        }
    }


    public static void customerProfile(Customer customer) {
        System.out.println(
                "First Name : " + customer.getFirstname() + "\n" +
                        "Last Name : " + customer.getLastname() + "\n" +
                        "Username : " + customer.getUsername() + "\n" +
                        "National Code : " + customer.getNationalCode() + "\n" +
                        "Birthday : " + customer.getBirthday() + "\n"
        );
    }

    private static void setOrChangeSecondPassword(Customer activeCustomer) {

        chooseAccount(activeCustomer);
    }

    private static void chooseAccount(Customer activeCustomer) {
        Set<Account> accounts = activeCustomer.getAccounts();
        customerAccountsListView(accounts);
        String accountNumber = new Input("Enter Account Number :").getInputString();
        for (Account account : accounts) {
            if (accountNumber.equals(account.getAccountNumber())) {
                CreditCard creditCard = accounts.stream().filter(
                                acc -> acc.getAccountNumber().equals(accountNumber))
                        .collect(Collectors.toList()).get(0).getCreditcard();
                String secondPassword;
                if (creditCard.getSecondPassword() == null) {
                    System.out.println("You did not Set, Enter Your Password : ");
                    secondPassword = InputInformation.getPasswordCard();
                    creditCard.setSecondPassword(secondPassword);
                    ApplicationContext.getCreditCardService().save(creditCard);
                    System.out.println("Second Password Set Successfully.");
                } else {
                    System.out.println("Enter Your Current password: ");
                    secondPassword = InputInformation.getPasswordCard();
                    if (secondPassword.equals(creditCard.getSecondPassword())) {
                        System.out.println("Enter Your New Password : ");
                        secondPassword = InputInformation.getPassword();
                        creditCard.setSecondPassword(secondPassword);
                        ApplicationContext.getCreditCardService().save(creditCard);
                        System.out.println("Second Password Changed Successfully");
                    } else {
                        System.out.println("Your Password is wrong.");
                    }

                }
            } else {
                System.out.println("Your Account Number is wrong.");
            }
        }
    }

    public static void customerAccountsListView(Set<Account> accounts) {
        getAccounts(accounts);
    }

    public static void getAccounts(Set<Account> accounts) {
        CreditCard creditCard;
        for (Account account : accounts) {
            System.out.println(
                    "Account Number : " + account.getAccountNumber() + "\n" +
                            "Account Credit : " + account.getCredit() + "\n"
            );
            creditCard = account.getCreditcard();
            System.out.println(
                    "Credit Card Information : \n" +
                            "Card Number : " + creditCard.getCardNumber() + "\n" +
                            "Card CVV2 : " + creditCard.getCvv2() + "\n" +
                            "Card ExpireDate : " + creditCard.getExpireDate() + "\n"
            );
        }
    }

    private static void transferMoney(Customer activeCustomer) {

        if (authenticationForTransfer(activeCustomer)) {
            Account destinationAccount =
                    ApplicationContext.getCreditCardService().findByCardNumber(destinationCardNumber).getAccount();
//            Customer destinationCustomer = destinationAccount.getCustomer();

            double customerInitCredit = customerAccount.getCredit();
            double destinationInitCredit = destinationAccount.getCredit();

            customerAccount.setCredit(customerInitCredit - transferCredit - 600.0);
            ApplicationContext.getAccountService().save(customerAccount);

            destinationAccount.setCredit(destinationInitCredit + transferCredit);
            ApplicationContext.getAccountService().save(destinationAccount);

            System.out.println("Transfer done...");
            ;


        }

    }


    private static Boolean authenticationForTransfer(Customer activeCustomer) {
        Set<Account> accounts = activeCustomer.getAccounts();
        List<CreditCard> creditCards = new ArrayList<>();
        for (Account account : accounts) {
            creditCards.add(account.getCreditcard());
        }

        String customerCardNumber;
        customerCardNumber = new Input("Enter your Card Number :").getInputString();
        String finalCardNumber = customerCardNumber;
        if (creditCards.stream().filter(creditCard -> creditCard.getCardNumber().equals(finalCardNumber))
                .collect(Collectors.toList()).get(0) == null) {
            System.out.println("You don't have this card number");
            return false;
        } else if (!creditCards.stream().filter(creditCard -> creditCard.getCardNumber().equals(finalCardNumber))
                .collect(Collectors.toList()).get(0).getAccount().isActive()) {
            System.out.println("Your Account is Blocked...");
        } else {
            CreditCard customerCard = creditCards.stream().
                    filter(creditCard -> creditCard.getCardNumber().equals(finalCardNumber))
                    .collect(Collectors.toList()).get(0);

            destinationCardNumber = new Input("Enter destination card number for transfer money ").getInputString();

            if (ApplicationContext.getCreditCardService().existByCardNumber(destinationCardNumber)) {
                if (checkCardDetails(customerCard)) {
                    System.out.println();
                    transferCredit = new Input("How Much You Wanna Transfer ?",Double.MAX_VALUE ,0.0,null).getInputDouble();
                    customerAccount = customerCard.getAccount();

                    if (transferCredit + 600.0 > customerAccount.getCredit()) {
                        System.out.println("Inventory is not enough");
                        return false;
                    }
                    return true;
                }
            } else {
                System.out.println("Wrong destination card number.");
                return false;
            }
        }
        return false;
    }

    private static Boolean checkCardDetails
            (CreditCard creditCard) {
        String cvv2;
        String secondPassword;
        Date expireDate;
        boolean result = true;
        int count = 0;
        boolean flag = false;

        while (count <= 3) {
            count++;

            cvv2 = new Input("Enter Your Card CVV2 : ").getInputString();
            expireDate = InputInformation.getBirthDay();

            secondPassword = new Input("Enter your card second password : ").getInputString();

            if (!creditCard.getCvv2().equals(cvv2)) {
                System.out.println("Wrong CVV2");
                result = false;
            } else if (!creditCard.getExpireDate().equals(expireDate)) {
                System.out.println("Wrong expire date");
                result = false;
            } else if (!creditCard.getSecondPassword().equals(secondPassword)) {
                System.out.println(" Wrong second password ");
                result = false;

            }
            if (count == 3)
                flag = true;
            if (result)
                break;

        }
        if (flag) {
            customerAccount.setActive(false);
            System.out.println("Your account details entered wrong more than 3 times\n" +
                    "Your account is blocked, " +
                    "please contact staff to activate the account.");
        }
        return result;
    }

    private static void employeeMenu(Employee activeEmployee) {
        Menu menu = new Menu();
        menu.setItems(new String[]{"Add a Customer", "Show List of Customer Accounts", "Change Customer Account to Active", "Log out"}
        );
        while (true) {
            menu.print();
            switch (menu.chooseOperation()) {
                case 1:
                    addCustomer(activeEmployee);
                    break;
                case 2:
                    showListOfCustomerAccounts(activeEmployee);
                    break;
                case 3:
                    activeAccount();
                    break;
                case 4:
                    return;
            }
        }

    }

    private static void addCustomer(Employee activeEmployee) {

        System.out.println("Add customer's account");
        Set<Account> customerAccounts = new HashSet<>();
        customerAccounts.add(addAccount(activeEmployee));

        Customer newCustomer = new Customer(
                InputInformation.getFirstName(),
                InputInformation.getLastName(),
                InputInformation.getNationalCode(),
                InputInformation.getBirthDay(),
                InputInformation.getUsername(),
                new Input("Enter password :").getInputString(),
                UserType.CUSTOMER,
                customerAccounts);

        try {
            ApplicationContext.getCustomerService().save(newCustomer);
            System.out.println("Customer Added Successfully");
        } catch (Exception e) {
            System.out.println("Error...");
        }

    }

    private static Account addAccount(Employee activeEmployee) {
        System.out.println("Add account's credit card.");
        CreditCard accountCreditCard = addCreditCard();

        return new Account(
                InputInformation.getAccountNumber(),
                10000.0,
                true,
                accountCreditCard,
                activeEmployee.getBranch());
    }

    private static CreditCard addCreditCard() {

        return new CreditCard(
                InputInformation.getCardNumber(),
                InputInformation.getPasswordCard(),
                InputInformation.getCVV2(),
                InputInformation.getBirthDay());
    }

    private static void showListOfCustomerAccounts(Employee activeEmployee) {

        List<Account> branchAccounts = ApplicationContext.getAccountService()
                .getAccountsByBranch(activeEmployee.getBranch());
        long id = 0L;
        List<Customer> customers = new ArrayList<>();
        for (Account account : branchAccounts) {
            customers.add(account.getCustomer());
        }
        customerViewList(customers);

        try {
            id = new Input("Enter customer id to show accounts :",Long.MAX_VALUE, 0L).getInputLong();
            long finalId = id;

            Customer chosenCustomer = customers.stream().filter(customer -> customer.getId() == finalId)
                    .collect(Collectors.toList()).get(0);

            customerAccountsListView(chosenCustomer.getAccounts());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("wrong id...");
            ;
        }


    }

    public static void customerViewList(List<Customer> customers) {
        for (Customer customer : customers) {
            System.out.println(
                    "ID : " + customer.getId() + "\n" +
                            "First Name : " + customer.getFirstname() + "\n" +
                            "Last Name : " + customer.getLastname() + "\n" +
                            "National Code : " + customer.getNationalCode() + "\n" +
                            "Birthday : " + customer.getBirthday() + "\n"
            );
        }

    }

    public static void activeAccount() {

        AccountService accountService = ApplicationContext.getAccountService();
        Account account = accountService.findById(
                new Input(
                        "Enter your id of account that is blocked",
                        Long.MAX_VALUE,
                        0L
                ).getInputLong());
        account.setActive(true);
        accountService.save(account);
        System.out.println("Your account is active now...");

    }


}
