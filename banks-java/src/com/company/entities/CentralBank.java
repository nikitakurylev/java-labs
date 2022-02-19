package com.company.entities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CentralBank {
    private final List<Bank> banks;

    public CentralBank() {
        banks = new ArrayList<>();
    }

    public Bank registerBank(String name, double suspiciousLimit) {
        var bank = new Bank(name, suspiciousLimit, new ClientBuilder());
        banks.add(bank);
        return bank;
    }

    public Bank findBank(String name) {
        return banks.stream().filter(bank -> bank.getName().equals(name)).findAny().orElse(null);
    }

    public void applyCommission(Duration term) {
        for (Bank bank : banks)
            bank.applyCommission(term);
    }
}
