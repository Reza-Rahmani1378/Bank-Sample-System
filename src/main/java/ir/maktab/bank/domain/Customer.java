package ir.maktab.bank.domain;

import ir.maktab.bank.domain.base.User;
import ir.maktab.bank.domain.enumeration.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends User {

    public static final String TABLE_NAME = "customer";

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Set<Account> accounts = new HashSet<>();


    public Customer(String firstname, String lastname, String nationalCode, Date birthday,
                    String username, String password, UserType usertype, Set<Account> accounts) {
        super(firstname, lastname, nationalCode, birthday, username, password, usertype);
        this.accounts = accounts;
    }

}
