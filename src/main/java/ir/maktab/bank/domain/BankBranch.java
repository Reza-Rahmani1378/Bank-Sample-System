package ir.maktab.bank.domain;

import ir.maktab.bank.base.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = BankBranch.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankBranch extends BaseEntity<Long> {

    public static final String TABLE_NAME = "bank_branch";

    private static final String BRANCH_CODE = "branch_code";
    private static final String BRANCH_NAME = "branch_name";


    @Column(name = BRANCH_CODE)
    private String branchCode;

    @Column(name = BRANCH_NAME)
    private String branchName;

    @OneToMany
    @JoinColumn(name = "branch_id")
    private Set<Account> accounts = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "branch_id")
    private Set<Employee> employees = new HashSet<>();

    @OneToOne(mappedBy = "branch", cascade = CascadeType.PERSIST)
    private Boss boss;



    public BankBranch(String branchCode, String branchName, Boss boss) {
        this.branchCode = branchCode;
        this.branchName = branchName;
        this.boss = boss;
    }

    @Override
    public String toString() {
        return "BankBranch{" +
                "branchCode='" + branchCode + '\'' +
                ", branchName='" + branchName + '\'' +
                '}';
    }
}
