package com.company.entities;

import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Bank implements TransactionListener{
    private final double suspiciousLimit;
    private final List<Account> accounts;
    private final List<Transaction> _transactions;
    private TransactionEvent transactionEvent;

    public Bank(String name, double suspiciousLimit, ClientBuilder clientBuilder) {
        this.name = name;
        this.suspiciousLimit = suspiciousLimit;
        this.clientBuilder = clientBuilder;
        clients = new HashSet<>();
        accounts = new ArrayList<>();
        _transactions = new ArrayList<>();
    }

    private final HashSet<Client> clients;

    public HashSet<Client> getClients() {
        return clients;
    }

    private final String name;

    public String getName() {
        return name;
    }

    private final ClientBuilder clientBuilder;

    public Client addClient(String fullName) {
        clientBuilder.setFullName(fullName);
        Client client = clientBuilder.getClient();
        clients.add(client);
        return client;
    }

    public Client addClient(String fullName, String address, String passport) {
        clientBuilder.setFullName(fullName);
        clientBuilder.setAddress(address);
        clientBuilder.setPassport(passport);
        Client client = clientBuilder.getClient();
        clients.add(client);
        return client;
    }

    public Account createAccount(Client client, AccountFactory accountFactory)
    {
        clients.add(client);
        var account = accountFactory.CreateAccount(client, suspiciousLimit);
        account.transactionEvent.addListener(this);
        accounts.add(account);
        return account;
    }

    public void applyCommission(@NotNull Duration duration) {
        for(Account account : accounts)
            account.applyRate(duration);
    }

    public void revertTransaction(Transaction transaction) {
        if (!_transactions.contains(transaction))
            return;
        transaction.revert();
        _transactions.remove(transaction);
    }

    public void revertLastTransaction() {
        revertTransaction(_transactions.get(_transactions.size() - 1));
    }

    public Client findClient(String name) {
        return clients.stream().filter(client -> name.equals(client.getFullName())).findAny().orElse(null);
    }

    public boolean hasClient(Client client) {
        return clients.contains(client);
    }

    public List<Account> getAccountsOfClient(Client client) {
        return accounts.stream().filter(account -> account.getClient() == client).toList();
    }

    public void onTransaction(Transaction accountTransaction) {
        _transactions.add(accountTransaction);
    }
}
