package ir.maktab.bank.repository.impl;

import ir.maktab.bank.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.bank.domain.Account;
import ir.maktab.bank.domain.BankBranch;
import ir.maktab.bank.domain.Employee;
import ir.maktab.bank.repository.AccountRepository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AccountRepositoryImpl extends BaseRepositoryImpl<Account, Long> implements AccountRepository {

    public AccountRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Account> getEntityClass() {
        return Account.class;
    }

    @Override
    public List<Account> getAccountsByBranch(BankBranch branch) {
        return entityManager.createQuery("from Account a where a.branch = :branch" , Account.class)
                .setParameter("branch", branch)
                .getResultList();
    }

    @Override
    public List<Account> findAllPerPage(int page, int size , BankBranch branch) {
        TypedQuery<Account> query = entityManager.createQuery(
                "select a.branch from Account a ", getEntityClass()
        );

        query.setMaxResults(size).setFirstResult(size * page);

        /*List<Employee> resultList = query.getResultList();*/

        TypedQuery<Account> ty = entityManager.createQuery(
                "from Account a where a.branch = : branch", getEntityClass()
        ).setParameter("branch", branch);

        EntityGraph<Account> entityGraph = entityManager.createEntityGraph(getEntityClass());

        entityGraph.addAttributeNodes("customer");
        ty.setHint("javax.persistence.fetchgraph" , entityGraph);

        return ty.getResultList();

    }
}
