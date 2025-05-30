package bank;

import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import static org.testng.Assert.*;

/**
As a user, I want to be able to create a bank account. A bank account should have an identifier (integer), the status of the account (active/inactive) and a balance:
 1 -  By default, a bank account has the identifier 0, is active and has a balance equal to 0.0.
 2 -  It should also be possible to create active bank accounts with a given identifier and balance.
 3 - Check that criation of an account writes on the log.
*/

@Test
public class AccountTest {
    
    private Log log;
    
    @BeforeMethod
    private void setUp() {
        log = mock(Log.class);
    }
    
    public void whenConstructThenAccoutnIsActiveAndHasId0AndBalance0() {
        Account acc = new Account(log);
        
        assertTrue(acc.isActive());
        assertEquals(acc.getId(), 0);
        assertEquals(acc.getBalance(), 0);
    }
    
    public void givenAnIdAndBalanceWhenCreateAccountThenAccoutnIsActiveAndHasGivenId0AndBalance0() {
        int id = 12;
        double balance = 25;
        
        Account acc = new Account(id, balance, log);
        
        assertTrue(acc.isActive());
        assertEquals(acc.getId(), id);
        assertEquals(acc.getBalance(), balance);
    }
    
    public void whenCreateADefaultAccountThenALogIsUpdated() {
        new Account(log);
        
        verify(log).writeTo(0, OperationType.CREATION, 0, true);
    }
    
    public void givenAnIdAndBalanceWhenCreateAccountThenLogIsUpdated() {
        int id = 12;
        double balance = 25;
        
        new Account(id, balance, log);
        
        verify(log).writeTo(id, OperationType.CREATION, balance, true);
    }

}
