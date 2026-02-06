package org.iths.enhetstestninglabb.component;

import org.springframework.stereotype.Component;

@Component
public class AccountComponent {
    // Saldot
    private int balance = 0;

    // Insättning
    public void deposit(int amount) {
        balance += amount;
    }

    // Uttag
    public void withdraw(int amount) {
        balance -= amount;
    }

    // Returnera saldot
    public int getBalance() {
        return balance;
    }
}
