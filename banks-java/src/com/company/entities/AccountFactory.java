package com.company.entities;

public interface AccountFactory {
    Account CreateAccount(Client client, double suspiciousLimit);
}
