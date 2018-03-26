package com.thoughtworks.step.bank;

import org.junit.Test;

public class AccountNumberTest {
    @Test(expected = InvalidAccountNumberException.class)
    public void checkAccountNumberConsistsOfOneSymbol() throws InvalidAccountNumberException {
        AccountNumber.createAccountNumber("12121212");
    }

    @Test(expected = InvalidAccountNumberException.class)
    public void checkAccountNumberHasOnlyNumerics() throws InvalidAccountNumberException {
        AccountNumber.createAccountNumber("asdf-wqee");
    }

    @Test(expected = InvalidAccountNumberException.class)
    public void checkAccountNumberHasASpecificSymbol() throws InvalidAccountNumberException {
        AccountNumber.createAccountNumber("1212$1212");
    }

    @Test(expected = InvalidAccountNumberException.class)
    public void checkAccountNumberHasExactlyEightDigitsAndASymbolInBetween() throws InvalidAccountNumberException {
        AccountNumber.createAccountNumber("1212-2121232");
    }
}
