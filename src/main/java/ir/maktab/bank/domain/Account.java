package ir.maktab.bank.domain;

import ir.maktab.bank.base.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = Account.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity<Long> {

    public static final String TABLE_NAME = "account";

    private static final String IS_ACTIVE = "is_active";

    @Column(unique = true)
    private String accountNumber;
    @Column
    private Double credit;
    @Column(name = IS_ACTIVE )
    private boolean isActive;

    @OneToOne(cascade = CascadeType.ALL)
    private CreditCard creditcard;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private BankBranch branch;

    public Account(String accountNumber, Double credit,
                   boolean isActive, CreditCard creditcard, BankBranch branch) {
        this.accountNumber = accountNumber;
        this.credit = credit;
        this.isActive = isActive;
        this.creditcard = creditcard;
        this.branch = branch;
    }
}
