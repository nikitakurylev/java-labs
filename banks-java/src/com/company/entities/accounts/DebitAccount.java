package com.company.entities.accounts;

import com.company.entities.Account;
import com.company.entities.Client;

import java.time.Duration;

public class DebitAccount extends Account {
    public DebitAccount(Client client, double suspiciousLimit, double debitRate) {
        super(client, suspiciousLimit);
        this.debitRate = debitRate;
    }

    private double debitRate = 0;

    @Override
    protected boolean canTakeAmount(double amount) {
        return getBalance() >= amount;
    }

    @Override
    public void applyRate(Duration duration) {
        setBalance(getBalance() + getBalance() * getTimePassed(duration) * debitRate);
    }
}
