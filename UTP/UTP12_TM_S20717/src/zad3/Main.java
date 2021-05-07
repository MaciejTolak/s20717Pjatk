package zad3;

import java.beans.PropertyVetoException;

public class Main {
    public static void main(String[] args) {

        Account acc1 = null, acc2 = null;

        try {
            acc1 = new Account(100);
            acc2 = new Account();


            AccountLimitator acclim = new AccountLimitator(-200);
            acc1.addVeto(acclim);
            acc2.addVeto(acclim);



            AccountChange accountChange = new AccountChange();

            acc1.addProperty(accountChange);
            acc2.addProperty(accountChange);

            System.out.println(acc1);
            System.out.println(acc2);
            System.out.println();

            acc2.deposit(1000);
            System.out.println(acc1);
            System.out.println(acc2);
            System.out.println();

        acc1.withdraw(250);
            System.out.println(acc1);
            System.out.println(acc2);
            System.out.println();

           acc2.transfer(acc1, 1200);

            System.out.println(acc1);
            System.out.println(acc2);
            System.out.println();

            acc2.transfer(acc1, 1);
            System.out.println(acc1);
            System.out.println(acc2);

        } catch (PropertyVetoException e) {
            System.out.println(e.getMessage());
            System.out.println(acc1);
            System.out.println(acc2);
        }
    }
}



