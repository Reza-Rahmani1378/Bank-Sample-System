package ir.maktab.bank.domain.base;

import ir.maktab.bank.base.domain.BaseEntity;
import ir.maktab.bank.domain.enumeration.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = User.TABLE_NAME)
@Inheritance
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity<Long> {

    public static final String TABLE_NAME = "user_table";

    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String NATIONAL_CODE = "national_code";
    public static final String BIRTHDAY = "birthday";
    public static final String USER_TYPE = "user_type";
    public static final String USER_NAME = "user_name";
    public static final String PASSWORD = "password";


    @Column(name = FIRST_NAME)
    private String firstname;

    @Column(name = LAST_NAME)
    private String lastname;

    @Column(name = NATIONAL_CODE, unique = true)
    private String nationalCode;

    @Column(name = BIRTHDAY)
    private Date birthday;

    @Column(name = USER_NAME, unique = true)
    private String username;

    @Column(name = PASSWORD)
    private String password;

    @Column(name = USER_TYPE)
    @Enumerated(EnumType.STRING)
    private UserType usertype;

}
