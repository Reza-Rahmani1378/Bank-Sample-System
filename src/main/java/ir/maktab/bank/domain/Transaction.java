package ir.maktab.bank.domain;

import ir.maktab.bank.base.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = Transaction.TABLE_NAME)
public class Transaction extends BaseEntity<Long> {

    public static final String TABLE_NAME = "transaction_table";

    private String fromCardNumber;

    private Long initialCredit;

    private Long logAmountChange;

    private Long afterLogCredit;

    private Date logDate;

    private String toCardNumber;

    public Transaction() {
    }

    public Transaction(String fromCardNumber, Long initialCredit,
                       Long logAmountChange, Long afterLogCredit, Date logDate, String toCardNumber) {
        this.fromCardNumber = fromCardNumber;
        this.initialCredit = initialCredit;
        this.logAmountChange = logAmountChange;
        this.afterLogCredit = afterLogCredit;
        this.logDate = logDate;
        this.toCardNumber = toCardNumber;
    }
}
