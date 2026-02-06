package org.iths.enhetstestninglabb.component;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AccountComponentTest {

    // Klassattribut
    private AccountComponent account;

    // Initialisering
    @BeforeEach
    void setUp() {
        account = new AccountComponent();
    }

    @Test
    @DisplayName("Testa att startsaldo är 0")
    void initialBalanceIsZero() {
        // Assert
        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    @DisplayName("Testa insättning ökar saldot")
    void depositIncreasesBalance() {
        // Act
        account.deposit(100); // Sätt in 100
        // Assert
        Assertions.assertEquals(100, account.getBalance());
    }

    @Test
    @DisplayName("Testa uttag minskar saldot")
    void withdrawDecreasesBalance() {
        // Arrange
        account.deposit(200); // Sätt upp ett saldo att ta ut ifrån
        // Act
        account.withdraw(50); // Ta ut 50
        // Assert
        Assertions.assertEquals(150, account.getBalance());
    }

    @Test
    @DisplayName("Testa uttag och insättning tillsammans")
    void depositAndWithdrawTogether() {
        // Act
        account.deposit(300); // Sätt in 300
        account.withdraw(100); // Ta ut 100
        account.deposit(150); // Sätt in 150
        account.withdraw(25); // Ta ut 25
        // Assert
        Assertions.assertEquals(325, account.getBalance());
    }
}
