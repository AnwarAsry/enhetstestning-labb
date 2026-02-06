package org.iths.enhetstestninglabb.service;

import org.iths.enhetstestninglabb.component.AccountComponent;
import org.iths.enhetstestninglabb.exception.InsufficientFundsException;
import org.iths.enhetstestninglabb.exception.InvalidAmountException;
import org.iths.enhetstestninglabb.exception.MaxWithdrawalExceededException;
import org.springframework.stereotype.Service;

@Service
public class ATMService {

    // Max belopp som kan tas ut
    private static final int MAX_WITHDRAWAL_AMOUNT = 10000;

    // Referens till AccountComponent
    private final AccountComponent account;

    public ATMService(AccountComponent account) {
        this.account = account;
    }

    // Metod för att sätta in pengar
    public void deposit(int amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be greater than zero.");
        }
        account.deposit(amount);
    }

    // Metod för att ta ut pengar
    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be greater than zero");
        }
        if (amount > MAX_WITHDRAWAL_AMOUNT) {
            throw new MaxWithdrawalExceededException("Withdrawal exceeds max limit");
        }
        if (amount > account.getBalance()) {
            throw new InsufficientFundsException("Insufficient funds");
        }
        account.withdraw(amount);
    }

    // Metod för hämta saldo
    public int getBalance() {
        return account.getBalance();
    }
}
