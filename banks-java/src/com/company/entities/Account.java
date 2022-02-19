package com.company.entities;

import java.time.Duration;

public abstract class Account {
    private final double suspiciousLimit;

    protected Account(Client client, double suspiciousLimit) {
        this.client = client;
        this.suspiciousLimit = suspiciousLimit;
    }

    public TransactionEvent transactionEvent = new TransactionEvent();

    private final Client client;

    public Client getClient() {
        return client;
    }

    private double balance;

    public double getBalance() {
        return balance;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }

    protected double getTimePassed(Duration duration) {
        return duration.toDays() / 365.0;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (!canTakeAmount(amount))
            return false;
        if (client.isSuspicious() && amount > suspiciousLimit)
            return false;
        balance -= amount;
        return true;
    }

    public boolean transfer(Account account, double amount) {
        if (!withdraw(amount))
            return false;
        account.deposit(amount);
        transactionEvent.invoke(new Transaction(this, account, amount));
        return true;
    }

    abstract public void applyRate(Duration duration);

    protected abstract boolean canTakeAmount(double amount);
}
