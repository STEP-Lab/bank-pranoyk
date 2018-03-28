package com.thoughtworks.step.bank;

public class Account {
    private final AccountNumber accountNumber;
    private double balance;

    private Account(AccountNumber accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public static Account createAccount(String number, double balance) throws InvalidAccountNumberException, MinimumBalanceException {
        AccountNumber accountNumber = new AccountNumber(number);
        validateMinimumBalance(balance);
        return new Account(accountNumber, balance);
    }

    private static void validateMinimumBalance(double balance) throws MinimumBalanceException {
        if (balance < 1000) {
            throw new MinimumBalanceException();
        }
    }

    public double getAccountBalance() {
        return balance;
    }

    public void debit(double amount) throws MinimumBalanceException {
        validateMinimumBalance(this.balance - amount);
        balance-=amount;
    }
}
