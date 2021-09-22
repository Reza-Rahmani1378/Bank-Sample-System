package ir.maktab.bank.base.repository.impl;

import ir.maktab.bank.base.domain.BaseEntity;
import ir.maktab.bank.base.repository.BaseRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public abstract class BaseRepositoryImpl<E extends BaseEntity<ID>, ID extends Serializable>
        implements BaseRepository<E, ID> {

    protected final EntityManager entityManager;

    public BaseRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public abstract Class<E> getEntityClass();

    @Override
    public E save(E e) {
        if (e.getId() == null) {
            entityManager.persist(e);
        } else {
            return entityManager.merge(e);
        }
        return e;
    }

    @Override
    public void delete(E e) {
        if (entityManager.contains(e)){
            entityManager.remove(e);
        } else {
            BaseEntity baseEntity = entityManager.getReference(e.getClass(), e.getId());
            entityManager.remove(baseEntity);
        }

    }

    @Override
    public E findById(ID id) {
        return entityManager.find(getEntityClass(), id);
    }

    @Override
    public List<E> findAll() {
        return entityManager.createQuery("from " + getEntityClass().getSimpleName(), getEntityClass())
                .getResultList();
    }

    @Override
    public boolean existById(ID id) {
        return entityManager.createQuery("select count(id) from "
                        + getEntityClass().getSimpleName() + " where id = :id"
                , Long.class)
                .setParameter("id", id).getSingleResult() == 1L;
    }

    @Override
    public EntityManager getEntityManager() {
        return this.entityManager;
    }
}
