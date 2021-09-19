package ir.maktab56.home_work15.base.repository.impl;

import ir.maktab56.home_work15.base.domain.BaseEntity;
import ir.maktab56.home_work15.base.repository.BaseEntityRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public abstract class BaseEntityRepositoryImpl<E extends BaseEntity<ID>, ID extends Serializable>
        implements BaseEntityRepository<E, ID> {

    protected final EntityManager entityManager;

    public BaseEntityRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public abstract Class<E> getEntityClass();

    @Override
    public E save(E e) {
        if (e.getId() == null) {
            entityManager.persist(e);
            return e;
        } else {
            return entityManager.merge(e);
        }

    }

    @Override
    public E findById(ID id) {
        return entityManager.find(getEntityClass(), id);
    }

    @Override
    public List<E> findAll() {
        return entityManager.createQuery(
                "from " + getEntityClass().getSimpleName(),
                getEntityClass()
        ).getResultList();
    }

    @Override
    public void delete(E e) {
        entityManager.remove(e);
    }

    @Override
    public boolean existById(ID id) {
        return entityManager.createQuery(
                        "select count(id) from " + getEntityClass().getSimpleName() +
                                " where id = :id", Long.class
                ).setParameter("id", id).
                getSingleResult() == 1L;
    }

    @Override
    public Long countAll() {
        return entityManager.createQuery(
                "select count(id) from " + getEntityClass().getSimpleName(),
                Long.class
        ).getSingleResult();
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
