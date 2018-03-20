package com.thoughtworks.step.bank;

public class Account {
    private final String accountNumber;
    private float balance;

    public Account(String accountNumber, float balance) throws MinimumBalanceException, InvalidAccountNumberException {
        if (!accountNumber.matches("\\d{4}-\\d{4}")) {
            throw new InvalidAccountNumberException();
        }
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

    public void withdraw(float amount) throws MinimumBalanceException {
        if(this.balance - amount < 1000) {
            throw new MinimumBalanceException();
        }
        balance-=amount;
    }
}
