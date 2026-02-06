package org.iths.enhetstestninglabb.service;

import org.iths.enhetstestninglabb.component.AccountComponent;
import org.iths.enhetstestninglabb.exception.InsufficientFundsException;
import org.iths.enhetstestninglabb.exception.InvalidAmountException;
import org.iths.enhetstestninglabb.exception.MaxWithdrawalExceededException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ATMServiceTestMock {
    @Mock
    private AccountComponent account;

    @InjectMocks
    private ATMService atmService;

    @Test
    @DisplayName("Test deposit successfully")
    void validDepositCall() {
        atmService.deposit(100);
        verify(account).deposit(100);
    }

    @Test
    @DisplayName("Test deposit fail with invalid amount")
    void depositWithInvalidAmountThrowsException() {
        assertThrows(InvalidAmountException.class, () -> atmService.deposit(0));
    }

    @Test
    @DisplayName("Test withdraw successfully")
    void validWithdrawCall() {
        // För annars jämförs 200 med 0 på tredje if satsen och testet misslyckas
        when(account.getBalance()).thenReturn(500);

        atmService.withdraw(200);

        verify(account).withdraw(200);
    }

    @Test
    @DisplayName("Test withdraw fail with invalid amount")
    void withdrawWithInvalidAmountThrowsException() {
        assertThrows(InvalidAmountException.class,
                () -> atmService.withdraw(-10));
    }

    @Test
    @DisplayName("Test withdraw fail with amount over max limit")
    void withdrawOverMaxThrowsException() {
        assertThrows(MaxWithdrawalExceededException.class,
                () -> atmService.withdraw(20000));
    }

    @Test
    @DisplayName("Test withdraw fail with insufficient funds")
    void withdrawWithInsufficientFundsThrowsException() {
        when(account.getBalance()).thenReturn(100);

        assertThrows(InsufficientFundsException.class,
                () -> atmService.withdraw(200));
    }

    @Test
    @DisplayName("Test get balance successfully")
    void validGetBalanceCall() {
        when(account.getBalance()).thenReturn(500);
        int balance = atmService.getBalance();
        assertEquals(500, balance);
        verify(account).getBalance();
    }
}
