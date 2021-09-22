package ir.maktab.bank.repository;

import ir.maktab.bank.base.repository.BaseRepository;
import ir.maktab.bank.domain.CreditCard;

public interface CreditCardRepository extends BaseRepository<CreditCard, Long> {

    Boolean existByCardNumber(String cardNumber);

    CreditCard findByCardNumber(String cardNumber);
}
