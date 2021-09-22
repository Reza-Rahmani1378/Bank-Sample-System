package ir.maktab.bank.service;

import ir.maktab.bank.base.service.BaseService;
import ir.maktab.bank.domain.CreditCard;

public interface CreditCardService extends BaseService<CreditCard, Long> {

    Boolean existByCardNumber(String cardNumber);

    CreditCard findByCardNumber(String cardNumber);

}
