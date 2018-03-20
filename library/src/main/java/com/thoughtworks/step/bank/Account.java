package com.thoughtworks.step.bank;

public class Account {
    private final String accountNumber;
    private float balance;

    public Account(String accountNumber, float balance) throws MinimumBalanceException {
        this.accountNumber = accountNumber;
        if (balance < 1000) {
            throw new MinimumBalanceException();
        }
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public float getAccountBalance() {
        return balance;
    }
}
