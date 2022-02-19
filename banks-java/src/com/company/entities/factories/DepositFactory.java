package com.company.entities.factories;

import com.company.entities.Account;
import com.company.entities.AccountFactory;
import com.company.entities.Client;
import com.company.entities.accounts.DepositAccount;

import java.util.List;

public class DepositFactory implements AccountFactory {
    public DepositFactory(List<Double> depositLimits, List<Double> depositRates) {
        this.depositLimits = depositLimits;
        this.depositRates = depositRates;
    }
    private final List<Double> depositLimits;
    private final List<Double> depositRates;

    @Override
    public Account CreateAccount(Client client, double suspiciousLimit) {
        return new DepositAccount(client, suspiciousLimit, depositRates, depositLimits);
    }
}
