package com.company.entities.factories;

import com.company.entities.Account;
import com.company.entities.AccountFactory;
import com.company.entities.Client;
import com.company.entities.accounts.DebitAccount;

public class DebitFactory implements AccountFactory {
    public DebitFactory(double debitRate) {
        this.debitRate = debitRate;
    }

    private final double debitRate;

    @Override
    public Account CreateAccount(Client client, double suspiciousLimit) {
        return new DebitAccount(client, suspiciousLimit, debitRate);
    }
}
