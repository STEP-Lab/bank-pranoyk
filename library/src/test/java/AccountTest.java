import com.thoughtworks.step.bank.Account;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTest {

    private Account account;

    @Before
    public void setUp() throws Exception {
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

}
