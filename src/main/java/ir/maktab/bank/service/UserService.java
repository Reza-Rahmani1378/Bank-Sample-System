package ir.maktab.bank.service;

import ir.maktab.bank.base.service.BaseService;
import ir.maktab.bank.domain.User;

public interface UserService extends BaseService<User, Long> {

    Boolean existByUsername(String username);

    User getUserByUsername(String username);

}
