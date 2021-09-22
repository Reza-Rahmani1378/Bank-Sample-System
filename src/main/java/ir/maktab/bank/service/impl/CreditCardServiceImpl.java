package ir.maktab.bank.service.impl;

import ir.maktab.bank.base.service.impl.BaseServiceImpl;
import ir.maktab.bank.domain.CreditCard;
import ir.maktab.bank.repository.CreditCardRepository;
import ir.maktab.bank.service.CreditCardService;

public class CreditCardServiceImpl extends BaseServiceImpl<CreditCard, Long, CreditCardRepository>
        implements CreditCardService {

    public CreditCardServiceImpl(CreditCardRepository repository) {
        super(repository);
    }

    @Override
    public Boolean existByCardNumber(String cardNumber) {
        return repository.existByCardNumber(cardNumber);
    }

    @Override
    public CreditCard findByCardNumber(String cardNumber) {
        return repository.findByCardNumber(cardNumber);
    }
}
