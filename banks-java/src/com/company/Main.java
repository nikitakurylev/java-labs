package com.company;

import com.company.entities.*;
import com.company.entities.factories.DebitFactory;
import com.company.services.*;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<Double> depositRates = List.of(0.03, 0.035, 0.04);
        List<Double> depositLimits = List.of(0., 50000., 100000.);
        Scanner scanner = new Scanner(System.in);
        CentralBank _centralBank = new CentralBank();
        double creditCommissionRate = 1000;
        double suspiciousLimit = 5000;
        double creditLimit = -50000;
        double debitRate = 0.01;
        Bank _bank = _centralBank.registerBank("Bank", suspiciousLimit);
        Client _trustedClient = _bank.addClient("Ivan", "Saint-Petersburg", "1234567890");
        Account _trustedAccount = _bank.createAccount(_trustedClient, new DebitFactory(debitRate));
        _trustedAccount.deposit(1000);
        Client _suspiciousClient = _bank.addClient("Nikita");
        Account _suspiciousAccount = _bank.createAccount(_suspiciousClient, new DebitFactory(debitRate));

        System.out.println("Enter your name");
        String name = scanner.nextLine();
        System.out.println("Welcome, " + name + "!\nType 'help' for help");
        var consoleUi = new ConsoleUi(new UserService(name, _centralBank), scanner);

        while (!consoleUi.isFinished())
        {
            consoleUi.waitForInput();
        }
    }
}
