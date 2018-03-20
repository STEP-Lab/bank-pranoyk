package com.thoughtworks.step.bank;

public class Account {
    private final String accountNumber;
    private float balance;

    public Account(String accountNumber, float balance) throws MinimumBalanceException, InvalidAccountNumberException {
        validateAccountNumber(accountNumber);
        this.accountNumber = accountNumber;
        validateMinimumBalance(balance);
        this.balance = balance;
    }

    private void validateMinimumBalance(float balance) throws MinimumBalanceException {
        if (balance < 1000) {
            throw new MinimumBalanceException();
        }
    }

    private void validateAccountNumber(String accountNumber) throws InvalidAccountNumberException {
        if (!accountNumber.matches("\\d{4}-\\d{4}")) {
            throw new InvalidAccountNumberException();
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public float getAccountBalance() {
        return balance;
    }

    public void withdraw(float amount) throws MinimumBalanceException {
        validateMinimumBalance(this.balance - amount);
        balance-=amount;
    }
}
