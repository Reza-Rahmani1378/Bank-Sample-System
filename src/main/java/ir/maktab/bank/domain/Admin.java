package ir.maktab.bank.domain;

import ir.maktab.bank.domain.enumeration.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Admin extends User {


    public Admin(String firstname, String lastname, String nationalCode,
                 Date birthday, String username, String password, UserType usertype) {
        super(firstname, lastname, nationalCode, birthday, username, password, usertype);
    }

}
