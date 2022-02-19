package com.company.entities.accounts;

import com.company.entities.Account;
import com.company.entities.Client;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

public class DepositAccount extends Account {
    public DepositAccount(Client client, double suspiciousLimit, List<Double> depositRates, List<Double> depositLimits) {
        super(client, suspiciousLimit);
        this.depositRates = depositRates;
        this.depositLimits = depositLimits;
    }

    @Override
    public void applyRate(Duration duration) {
        Double limit = depositLimits.stream().filter(l -> getBalance() < l).findFirst().orElse(0.);
        Double rate = depositRates.get(depositLimits.indexOf(limit) - 1);
        setBalance(getBalance() + getBalance() * rate);
    }

    List<Double> depositRates;
    List<Double> depositLimits;

    @Override
    protected boolean canTakeAmount(double amount) {
        return  amount <= getBalance();
    }
}
