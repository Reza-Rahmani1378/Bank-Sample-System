package ir.maktab.bank.repository;

import ir.maktab.bank.base.repository.BaseRepository;
import ir.maktab.bank.domain.User;

public interface UserRepository extends BaseRepository<User, Long> {

    Boolean existByUsername(String username);

    User getUserByUsername(String username);

}
