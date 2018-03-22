import com.thoughtworks.step.bank.AccountNumber;
import com.thoughtworks.step.bank.InvalidAccountNumberException;
import com.thoughtworks.step.bank.MinimumBalanceException;
import org.junit.Test;

public class AccountNumberTest {
    @Test(expected = InvalidAccountNumberException.class)
    public void checkAccountNumberConsistsOfOneSymbol() throws InvalidAccountNumberException {
        new AccountNumber("12121212");
    }

    @Test(expected = InvalidAccountNumberException.class)
    public void checkAccountNumberHasOnlyNumerics() throws InvalidAccountNumberException {
        new AccountNumber("asdf-wqee");
    }

    @Test(expected = InvalidAccountNumberException.class)
    public void checkAccountNumberHasASpecificSymbol() throws InvalidAccountNumberException {
        new AccountNumber("1212$1212");
    }

    @Test(expected = InvalidAccountNumberException.class)
    public void checkAccountNumberHasExactlyEightDigitsAndASymbolInBetween() throws InvalidAccountNumberException {
        new AccountNumber("1212-2121232");
    }
}
