package ir.maktab.bank.domain;

import ir.maktab.bank.domain.base.User;
import ir.maktab.bank.domain.enumeration.UserType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Getter
@Setter
public class Employee extends User {

    public static final String TABLE_NAME = "employee";

    @ManyToOne
    private BankBranch branch;

    public Employee() {
    }

    public Employee(String firstname, String lastname, String nationalCode, Date birthday, String username, String password, UserType usertype, BankBranch branch) {
        super(firstname, lastname, nationalCode, birthday, username, password, usertype);
        this.branch = branch;
    }


}
