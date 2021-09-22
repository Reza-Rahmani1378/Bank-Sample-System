package ir.maktab.bank.repository.impl;

import ir.maktab.bank.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.bank.domain.BankBranch;
import ir.maktab.bank.repository.BankBranchRepository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class BankBranchRepositoryImpl extends BaseRepositoryImpl<BankBranch, Long> implements BankBranchRepository {

    public BankBranchRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<BankBranch> getEntityClass() {
        return BankBranch.class;
    }

    @Override
    public List<BankBranch> findAllPerPage(int page, int size) {
        /*CriteriaBuilder criteriaBuilder =entityManager.getCriteriaBuilder();
        CriteriaQuery<BankBranch> query = criteriaBuilder.createQuery(getEntityClass());
        Root<BankBranch> from = query.from(getEntityClass());
        query.select(from);*/

        /*TypedQuery<BankBranch> typedQuery = entityManager.createQuery(query);
        typedQuery.
                setMaxResults(size).
                setFirstResult(page * size);

        return typedQuery.getResultList();*/

        TypedQuery<Long> query = entityManager.createQuery(
                "select id from BankBranch ", Long.class
        );

        query.setMaxResults(size).setFirstResult(size * page);

        List<Long> resultList = query.getResultList();

        TypedQuery<BankBranch> typedQuery = entityManager.createQuery(
                "from BankBranch bb where bb.id in :ids", BankBranch.class
        ).setParameter("ids", resultList);

        EntityGraph<BankBranch> entityGraph = entityManager.createEntityGraph(BankBranch.class);

        entityGraph.addAttributeNodes("accounts","employees","boss");

        typedQuery.setHint("javax.persistence.fetchgraph" , entityGraph);

        return typedQuery.getResultList();

    }
}
