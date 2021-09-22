package ir.maktab.bank;

import ir.maktab.bank.util.HibernateUtil;
import ir.maktab.bank.util.RunMenu;

import javax.persistence.EntityManager;

public class BankApplication {
    public static void main(String[] args) {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        entityManager.close();

        RunMenu.runSystem();
    }
}
