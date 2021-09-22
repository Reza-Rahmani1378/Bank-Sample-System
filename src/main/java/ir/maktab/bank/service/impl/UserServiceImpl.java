package ir.maktab.bank.service.impl;

import ir.maktab.bank.base.service.impl.BaseServiceImpl;
import ir.maktab.bank.domain.Account;
import ir.maktab.bank.domain.base.User;
import ir.maktab.bank.repository.AccountRepository;
import ir.maktab.bank.repository.UserRepository;
import ir.maktab.bank.service.AccountService;
import ir.maktab.bank.service.UserService;

public class UserServiceImpl extends BaseServiceImpl<User, Long, UserRepository> implements
        UserService {

    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override
    public Boolean existByUsername(String username) {
        return repository.existByUsername(username);
    }

    @Override
    public User getUserByUsername(String username) {
        return repository.getUserByUsername(username);
    }
}
