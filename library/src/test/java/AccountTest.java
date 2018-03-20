import com.thoughtworks.step.bank.Account;
import com.thoughtworks.step.bank.MinimumBalanceException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTest {

    private Account account;

    @Before
    public void setUp() throws Exception, MinimumBalanceException {
        account = new Account("1212-1212", 1000);
    }

    @Test
    public void checkAccountNumber() {
        assertThat(account.getAccountNumber(),is("1212-1212"));
    }

    @Test
    public void checkAccountBalance() {
        assertThat(account.getAccountBalance(),is(1000.0f));
    }

    @Test(expected = MinimumBalanceException.class)
    public void checkMinimumBalance() throws MinimumBalanceException {
        new Account("1212-1212",500);
    }
}
