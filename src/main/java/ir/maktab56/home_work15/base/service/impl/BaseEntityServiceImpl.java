package ir.maktab56.home_work15.base.service.impl;

import ir.maktab56.home_work15.base.domain.BaseEntity;
import ir.maktab56.home_work15.base.repository.BaseEntityRepository;
import ir.maktab56.home_work15.base.service.BaseEntityService;

import java.io.Serializable;
import java.util.List;

public class BaseEntityServiceImpl<E extends BaseEntity<ID>, ID extends Serializable,
        R extends BaseEntityRepository<E, ID>>
        implements BaseEntityService<E, ID> {

    protected final R repository;

    public BaseEntityServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public E save(E e) {
        repository.getEntityManager().getTransaction().begin();
        repository.save(e);
        repository.getEntityManager().getTransaction().commit();
        return e;
    }

    @Override
    public E findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(E e) {
        repository.getEntityManager().getTransaction().begin();
        repository.delete(e);
        repository.getEntityManager().getTransaction().commit();
    }

    @Override
    public boolean existById(ID id) {
        return repository.existById(id);
    }

    @Override
    public Long countAll() {
        return repository.countAll();
    }
}
