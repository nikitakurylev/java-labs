package com.company.entities.factories;

import com.company.entities.Account;
import com.company.entities.AccountFactory;
import com.company.entities.Client;
import com.company.entities.accounts.CreditAccount;

public class CreditFactory implements AccountFactory {
    public CreditFactory(double debitRate, double creditLimit) {
        this.commissionRate = debitRate;
        this.creditLimit = creditLimit;
    }

    private final double commissionRate;
    private final double creditLimit;

    @Override
    public Account CreateAccount(Client client, double suspiciousLimit) {
        return new CreditAccount(client, suspiciousLimit, creditLimit, commissionRate);
    }
}
