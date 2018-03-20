package com.thoughtworks.step.bank;

public class Account {
    private final String accountNumber;
    private float balance;

    public Account(String accountNumber, float balance) {

        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
