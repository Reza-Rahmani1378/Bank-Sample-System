package ir.maktab56.home_work15.base.repository;

import ir.maktab56.home_work15.base.domain.BaseEntity;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public interface BaseEntityRepository<E extends BaseEntity<ID>, ID extends Serializable> {

    E save(E e);

    E findById(ID id);

    List<E> findAll();

    void delete(E e);

    boolean existById(ID id);

    Long countAll();

    EntityManager getEntityManager();
}
