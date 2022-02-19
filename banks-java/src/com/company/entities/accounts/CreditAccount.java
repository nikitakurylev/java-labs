package com.company.entities.accounts;

import com.company.entities.Account;
import com.company.entities.Client;

import java.time.Duration;

public class CreditAccount extends Account {
    public CreditAccount(Client client, double suspiciousLimit, double creditLimit, double commissionRate) {
        super(client, suspiciousLimit);
        this.creditLimit = creditLimit;
        this.commissionRate = commissionRate;
    }

    private final double creditLimit;
    private final double commissionRate;

    @Override
    public void applyRate(Duration duration) {
        setBalance(getBalance() - getBalance() * commissionRate * getTimePassed(duration));
    }

    @Override
    protected boolean canTakeAmount(double amount) {
        return getBalance() - amount >= creditLimit;
    }
}
