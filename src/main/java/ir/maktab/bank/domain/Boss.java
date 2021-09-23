package ir.maktab.bank.domain;

import ir.maktab.bank.domain.enumeration.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Boss extends User {


    @OneToOne(cascade = CascadeType.ALL)
    private BankBranch branch;


    public Boss(String firstname, String lastname, String nationalCode,
                Date birthday, String username, String password, UserType usertype, BankBranch branch) {
        super(firstname, lastname, nationalCode, birthday, username, password, usertype);
        this.branch = branch;
    }

    public Boss(String firstname, String lastname,
                String nationalCode, Date birthday, String username, String password, UserType usertype) {
        super(firstname, lastname, nationalCode, birthday, username, password, usertype);
    }

    @Override
    public String toString() {
        return "Boss{" +
                "branch=" + branch +
                '}';
    }
}
