package com.company.entities;

public record Transaction(Account from, Account to, double amount) {

    public void revert() {
        to.withdraw(amount);
        from.deposit(amount);
    }
}
