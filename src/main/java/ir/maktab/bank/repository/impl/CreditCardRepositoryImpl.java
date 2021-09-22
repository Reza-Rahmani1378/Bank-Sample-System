package ir.maktab.bank.repository.impl;

import ir.maktab.bank.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.bank.domain.CreditCard;
import ir.maktab.bank.repository.CreditCardRepository;

import javax.persistence.EntityManager;

public class CreditCardRepositoryImpl extends BaseRepositoryImpl<CreditCard, Long> implements CreditCardRepository {


    public CreditCardRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<CreditCard> getEntityClass() {
        return CreditCard.class;
    }

    @Override
    public Boolean existByCardNumber(String cardNumber) {
        return entityManager.createQuery("select count(cardNumber) from CreditCard where " +
                "cardNumber = :cardNumber", Long.class).setParameter("cardNumber", cardNumber)
                .getSingleResult() == 1L;
    }

    @Override
    public CreditCard findByCardNumber(String cardNumber) {
        return entityManager.createQuery("from CreditCard where cardNumber = :cardNumber"
        , CreditCard.class).setParameter("cardNumber", cardNumber).getSingleResult();
    }
}
