import com.thoughtworks.step.bank.Account;
import com.thoughtworks.step.bank.InvalidAccountNumberException;
import com.thoughtworks.step.bank.MinimumBalanceException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTest {

    private Account account;

    @Before
    public void setUp() throws Exception, MinimumBalanceException {
        account = new Account("1212-1212", 2000);
    }

    @Test
    public void checkAccountNumber() {
        assertThat(account.getAccountNumber(),is("1212-1212"));
    }

    @Test
    public void checkAccountBalance() {
        assertThat(account.getAccountBalance(),is(2000.0f));
    }

    @Test(expected = MinimumBalanceException.class)
    public void checkMinimumBalance() throws MinimumBalanceException, InvalidAccountNumberException {
        new Account("1212-1212",500);
    }

    @Test(expected = InvalidAccountNumberException.class)
    public void checkAccountNumberConsistsOfOneSymbol() throws InvalidAccountNumberException, MinimumBalanceException {
        new Account("12121212",1000);
    }

    @Test(expected = InvalidAccountNumberException.class)
    public void checkAccountNumberHasOnlyNumerics() throws InvalidAccountNumberException, MinimumBalanceException {
        new Account("asdf-wqee",1000);//not working
    }

    @Test(expected = InvalidAccountNumberException.class)
    public void checkAccountNumberHasASpecificSymbol() throws InvalidAccountNumberException, MinimumBalanceException {
        new Account("1212$1212",1000);//not working
    }

    @Test(expected = InvalidAccountNumberException.class)
    public void checkAccountNumberHasExactlyEightDigitsAndASymbolInBetween() throws InvalidAccountNumberException, MinimumBalanceException {
        new Account("1212-2121232",1000);//not working
    }

    @Test
    public void withdrawAmount() throws MinimumBalanceException {
        account.withdraw(1000);
        assertThat(account.getAccountBalance(),is(1000.0f));
    }

    @Test(expected = MinimumBalanceException.class)
    public void checkWithdrawAmount() throws MinimumBalanceException {
        account.withdraw(1500);
    }
}
