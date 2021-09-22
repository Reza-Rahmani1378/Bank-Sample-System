package ir.maktab.bank.repository.impl;

import ir.maktab.bank.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.bank.domain.BankBranch;
import ir.maktab.bank.domain.Employee;
import ir.maktab.bank.repository.EmployeeRepository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeRepsitoryImpl extends BaseRepositoryImpl<Employee, Long> implements EmployeeRepository {

    public EmployeeRepsitoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Employee> getEntityClass() {
        return Employee.class;
    }

    @Override
    public List<Employee> findAllByBranchId(BankBranch branch) {
        return entityManager.createQuery("from Employee e where e.branch = :branch" , Employee.class)
                .setParameter("branch", branch)
                .getResultList();
    }

    @Override
    public List<Employee> findAllPerPage(int page, int size , BankBranch branch) {
        TypedQuery<Employee> query = entityManager.createQuery(
                "select e.branch from Employee e ", Employee.class
        );

        query.setMaxResults(size).setFirstResult(size * page);

        /*List<Employee> resultList = query.getResultList();*/

        TypedQuery<Employee> ty = entityManager.createQuery(
                "from Employee e where e.branch = : branch", Employee.class
        ).setParameter("branch", branch);

        EntityGraph<Employee> entityGraph = entityManager.createEntityGraph(getEntityClass());

        entityGraph.addAttributeNodes("branch");
        ty.setHint("javax.persistence.fetchgraph" , entityGraph);

        return ty.getResultList();

    }
}
