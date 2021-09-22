package ir.maktab.bank.domain;

import ir.maktab.bank.base.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = CreditCard.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard extends BaseEntity<Long> {

    public static final String TABLE_NAME = "credit_card";

    private String cardNumber;

    private String password;

    private String secondPassword;

    private String cvv2;

    private Date expireDate;

    @OneToOne(mappedBy = "creditcard")
    private Account account;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "creditCard_id")
    private Set<Transaction> transactions = new HashSet<>();


    public CreditCard(String cardNumber, String password,
                      String cvv2, Date expireDate) {
        this.cardNumber = cardNumber;
        this.password = password;
        this.cvv2 = cvv2;
        this.expireDate = expireDate;
    }


}
