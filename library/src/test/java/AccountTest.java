import com.thoughtworks.step.bank.Account;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTest {
    @Test
    public void checkAccountNumber() {
        Account account = new Account("1212-1212", 1000);
        assertThat(account.getAccountNumber(),is("1212-1212"));
    }
}
