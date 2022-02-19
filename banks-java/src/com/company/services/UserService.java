package com.company.services;

import com.company.entities.*;

import java.util.List;

public class UserService {
    private final CentralBank centralBank;
    private final String clientName;

    public UserService(String clientName, CentralBank centralBank) {
        this.clientName = clientName;
        this.centralBank = centralBank;
    }

    private Bank bank;
    private Client client;

    private boolean isLoggedOut() {
        return bank == null || client == null;
    }

    public String logInBank(String bankName) {
        bank = centralBank.findBank(bankName);
        client = bank.findClient(clientName);
        if (isLoggedOut())
            return "No such bank or client is not registered";
        return "Logged in " + bank.getName();
    }

    public String showClients() {
        if (isLoggedOut())
            return "Not logged in";
        StringBuilder result = new StringBuilder();
        for (Client client : bank.getClients())
            result.append(client.getFullName()).append("\n");

        return result.toString();
    }

    public String showAccounts(String clientName) {
        if (isLoggedOut())
            return "Not logged in";
        assert bank != null;
        Client client = bank.findClient(clientName);
        if (client == null)
            return "No such client";
        List<Account> accounts = bank.getAccountsOfClient(client);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < accounts.size(); i++)
            result.append(i).append(") ").append(accounts.get(i)).append(": ").append(accounts.get(i).getBalance()).append("\n");
        return result.toString();
    }

    public String showAccounts() {
        return showAccounts(clientName);
    }

    public String transfer(int accountNumber, String destinationName, int destinationAccountNumber, double amount) {
        if (isLoggedOut())
            return "Not logged in";
        assert bank != null;
        Client destinationClient = bank.findClient(destinationName);
        if (destinationClient == null)
            return "No such client";
        List<Account> accounts = bank.getAccountsOfClient(destinationClient);
        if (destinationAccountNumber >= accounts.size())
            return "No account with this number";
        return bank.getAccountsOfClient(client).get(accountNumber).transfer(accounts.get(destinationAccountNumber), amount) ? "Transfer successful" : "Transfer failed";
    }
}
