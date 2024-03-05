package bank;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    public void startup(){
        account = new BankAccount(0);
    }

    @Test
    public boolean getBalance_shouldReturnTrue(){
        return account.getBalance() == 0;
    }

    @Test
    public void deposit_shouldDepositFunds(){
        account.deposit(100);
        assertEquals(100, account.getBalance());
    }

    @Test
    public void withdraw_shouldWithdrawFunds(){
        account.deposit(100);
        assertTrue(account.withdraw(30));
        assertEquals(70, account.getBalance());
    }

    @Test
    public void testWithdrawInsufficientBalance() {
        account.deposit(100);
        assertFalse(account.withdraw(150));
        assertEquals(100, account.getBalance());
    }

    @Test
    public void testPayment() {
        double total_amount = 10000;
        double interest = 0.001;
        int npayments = 12;
        double expectedPayment = 838.759926; // valor esperado por mes
        double payment = account.payment(total_amount, interest, npayments);
        assertEquals(expectedPayment, payment, 0.01);
    }

    @Test
    public void testPendingFirstMonth() {
        double amount = 10000;
        double interest = 0.001;
        int npayments = 12;
        int month = 0;
        double expectedPending = 10000; // el primer mes seguimos debiendo el mismo dinero
        double pending = account.pending(amount, interest, npayments, month);
        assertEquals(expectedPending, pending, 0.01);
    }

    @Test
    public void testPendingSecondMonth() {
        double amount = 10000;
        double interest = 0.001;
        int npayments = 12;
        int month = 1;
        double expectedPending = 9171.240074; // el segundo mes calculamos el dinero que debemos y lo introducimos
        double pending = account.pending(amount, interest, npayments, month);
        assertEquals(expectedPending, pending, 0.01);
    }

}
