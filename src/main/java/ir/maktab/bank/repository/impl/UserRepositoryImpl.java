package ir.maktab.bank.repository.impl;

import ir.maktab.bank.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.bank.domain.base.User;
import ir.maktab.bank.repository.UserRepository;

import javax.persistence.EntityManager;

public class UserRepositoryImpl extends BaseRepositoryImpl<User, Long> implements UserRepository {
    public UserRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public Boolean existByUsername(String username) {
        return entityManager.createQuery("select count(username) from User where username = :username"
                , Long.class ).setParameter("username", username).getSingleResult() == 1L;
    }

    @Override
    public User getUserByUsername(String username) {
        return entityManager.createQuery(
                "from User u where u.username = :username", User.class
        ).setParameter("username", username).getSingleResult();
    }
}
