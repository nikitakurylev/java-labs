package test.java;

import com.company.entities.*;
import com.company.entities.factories.DebitFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

public class BankTest {
    private CentralBank centralBank;
    private Bank bank;
    private Client trustedClient, suspiciousClient;
    private Account trustedAccount, suspiciousAccount;
    private double creditCommissionRate = 1000, suspiciousLimit = 5000, creditLimit = -50000, debitRate = 0.01;

    @BeforeEach
    void Initialize() {
        centralBank = new CentralBank();
        bank = centralBank.registerBank("Bank", suspiciousLimit);
        trustedClient = bank.addClient("Ivan Ivanov", "Saint-Petersburg", "1234567890");
        trustedAccount = bank.createAccount(trustedClient, new DebitFactory(debitRate));
        suspiciousClient = bank.addClient("Sususs Amongus");
        suspiciousAccount = bank.createAccount(suspiciousClient, new DebitFactory(debitRate));
    }

    @ParameterizedTest
    @CsvSource({
            "1000, 1000",
            "1000, 2000",
            "10000, 10000"
    })
    public void TransferTest(double initialAmount, double transferAmount) {
        trustedAccount.deposit(initialAmount);
        assertEquals(initialAmount >= transferAmount, trustedAccount.transfer(suspiciousAccount, transferAmount));
        assertEquals(initialAmount >= transferAmount && suspiciousLimit >= transferAmount, suspiciousAccount.transfer(trustedAccount, transferAmount));
    }
    
    @ParameterizedTest
    @CsvSource({
            "1000, 1",
            "1000, 30",
            "10000, 365"
    })
    public void InterestTest(double initialAmount, long days)
    {
        trustedAccount.deposit(initialAmount);
        centralBank.applyCommission(Duration.ofDays(days));
        assertEquals(initialAmount * (1 + debitRate / 365 * days) , trustedAccount.getBalance());
    }
}
