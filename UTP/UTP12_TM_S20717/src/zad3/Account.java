package zad3;

import java.beans.*;

public class Account {
    private static int c = 1;
    private int n;
    private double balance;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private VetoableChangeSupport vetoableChangeSupport = new VetoableChangeSupport(this);

    public Account(double balance) {
        this.balance = balance;
        n=c;
        c++;
    }

    public Account() {
        n=c;
        c++;
    }

    synchronized void withdraw(double cash) throws PropertyVetoException {
        double oldValue = balance;
        vetoableChangeSupport.fireVetoableChange("balance",oldValue,balance-cash);
        balance = balance - cash;
        propertyChangeSupport.firePropertyChange("balance",oldValue,balance);
    }

    synchronized void transfer(Account acc, double cash) throws PropertyVetoException {
        double oldValue = balance;
        vetoableChangeSupport.fireVetoableChange("balance",oldValue,balance-cash);
        acc.deposit(cash);
        balance = balance - cash;
        propertyChangeSupport.firePropertyChange("balance",oldValue,balance);
    }

    synchronized void deposit(double cash) throws PropertyVetoException {
        double oldValue = balance;
        vetoableChangeSupport.fireVetoableChange("balance",oldValue,balance+cash);
        balance = balance + cash;
        propertyChangeSupport.firePropertyChange("balance",oldValue,balance);
    }

    synchronized void addProperty(PropertyChangeListener propertyChangeListener){
        propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
    }

    synchronized void addVeto(VetoableChangeListener vetoableChangeListener){
        vetoableChangeSupport.addVetoableChangeListener(vetoableChangeListener);
    }

    @Override
    public String toString() {
        return "Acc: "+n+" "+balance;
    }
}
