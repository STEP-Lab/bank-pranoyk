package com.thoughtworks.step.bank;

public class Account {
    private float balance;

    public Account(AccountNumber accountNumber, float balance) throws MinimumBalanceException {
        validateMinimumBalance(balance);
        this.balance = balance;
    }

    private void validateMinimumBalance(float balance) throws MinimumBalanceException {
        if (balance < 1000) {
            throw new MinimumBalanceException();
        }
    }

    public float getAccountBalance() {
        return balance;
    }

    public void withdraw(float amount) throws MinimumBalanceException {
        validateMinimumBalance(this.balance - amount);
        balance-=amount;
    }
}
