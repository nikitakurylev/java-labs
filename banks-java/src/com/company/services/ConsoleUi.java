package com.company.services;

import java.io.Console;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.Double.parseDouble;

public class ConsoleUi {
    private final UserService userService;
    Scanner scanner;

    public ConsoleUi(UserService userService, Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;
    }

    private boolean isFinished;

    public boolean isFinished() {
        return isFinished;
    }

    public void waitForInput() {
        String command = scanner.nextLine();
        if (command.isEmpty())
            return;
        var splitCommand = command.split(" ");
        String methodName = "user" + splitCommand[0].substring(0, 1).toUpperCase() + splitCommand[0].substring(1);
        Class<?> c = this.getClass();
        java.lang.reflect.Method method;
        try {
            var args = Arrays.stream(splitCommand).skip(1).toList();
            Class[] cArg = new Class[1];
            cArg[0] = List.class;
            method = c.getMethod(methodName, cArg);
            try {
                method.invoke(this, args);
            } catch (IllegalAccessException ignored) {
            } catch (InvocationTargetException e) {
                System.out.println("Wrong arguments");
            }
        } catch (NoSuchMethodException e) {
            System.out.println("No such command");
        }
    }

    public void userLogin(List<String> args) {
        System.out.println(userService.logInBank(args.get(0)));
    }

    public void userTransfer(List<String> args) {
        double actualAmount;
        try {
            actualAmount = parseDouble(args.get(1));
        } catch (NumberFormatException ex) {
            System.out.println("Not a number");
            return;
        }

        System.out.println("From which account?\n" + userService.showAccounts());
        int fromNumber = getChoice();
        System.out.println("To which account?\n" + userService.showAccounts(args.get(0)));
        int toNumber = getChoice();
        System.out.println(userService.transfer(fromNumber, args.get(0), toNumber, actualAmount));
    }

    public void userQuit(List<String> args) {
        isFinished = true;
    }

    public void userHelp(List<String> args) {
        System.out.println("Commands:\nhelp\nlogin [bank]\ntransfer [client] [amount]\nquit");
    }

    private int getChoice() {
        int choice = -1;
        boolean choosing = true;
        while (choosing) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                choosing = false;
            } catch (NumberFormatException ex) {
                System.out.println("Not a number");
            }
        }

        return choice;
    }
}
