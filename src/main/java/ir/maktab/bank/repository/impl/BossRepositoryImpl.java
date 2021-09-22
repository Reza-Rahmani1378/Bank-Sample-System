package ir.maktab.bank.repository.impl;

import ir.maktab.bank.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.bank.domain.Boss;
import ir.maktab.bank.repository.BossRepository;

import javax.persistence.EntityManager;

public class BossRepositoryImpl extends BaseRepositoryImpl<Boss, Long> implements BossRepository {

    public BossRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Boss> getEntityClass() {
        return Boss.class;
    }
}
