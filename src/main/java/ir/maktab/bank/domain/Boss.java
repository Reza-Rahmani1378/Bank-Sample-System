package ir.maktab.bank.domain;

import ir.maktab.bank.domain.base.User;
import ir.maktab.bank.domain.enumeration.UserType;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = Boss.TABLE_NAME)
public class Boss extends User {

    public static final String TABLE_NAME = "manager";

    @OneToOne(cascade = CascadeType.ALL)
    private BankBranch branch;

    public Boss() {
    }

    public Boss(String firstname, String lastname, String nationalCode,
                Date birthday, String username, String password, UserType usertype, BankBranch branch) {
        super(firstname, lastname, nationalCode, birthday, username, password, usertype);
        this.branch = branch;
    }

    public Boss(String firstname, String lastname,
                String nationalCode, Date birthday, String username, String password, UserType usertype) {
        super(firstname, lastname, nationalCode, birthday, username, password, usertype);
    }

    public BankBranch getBranch() {
        return branch;
    }

    public void setBranch(BankBranch branch) {
        this.branch = branch;
    }
}
