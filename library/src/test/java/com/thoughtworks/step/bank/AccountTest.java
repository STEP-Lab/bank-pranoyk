package com.thoughtworks.step.bank;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTest {

    private Account account;

    @Before
    public void setUp() throws Exception, MinimumBalanceException {
        account = Account.createAccount("1212-1212",2000);
    }

    @Test
    public void checkAccountBalance() {
        assertThat(account.getAccountBalance(),is(2000.0));
    }

    @Test(expected = MinimumBalanceException.class)
    public void checkMinimumBalance() throws MinimumBalanceException, InvalidAccountNumberException {
        Account.createAccount("1212-1212",500);
    }

    @Test
    public void withdrawAmount() throws MinimumBalanceException {
        account.withdraw(1000);
        assertThat(account.getAccountBalance(),is(1000.0));
    }

    @Test(expected = MinimumBalanceException.class)
    public void checkWithdrawAmount() throws MinimumBalanceException {
        account.withdraw(1500);
    }
}
