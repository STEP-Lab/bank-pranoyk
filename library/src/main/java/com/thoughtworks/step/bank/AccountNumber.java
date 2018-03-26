package com.thoughtworks.step.bank;

public class AccountNumber {
    private final String accountNumber;

    protected AccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    private static void validateAccountNumber(String accountNumber) throws InvalidAccountNumberException {
        if (!accountNumber.matches("\\d{4}-\\d{4}")) {
            throw new InvalidAccountNumberException();
        }
    }

    public static AccountNumber createAccountNumber(String number) throws InvalidAccountNumberException {
        validateAccountNumber(number);
        return new AccountNumber(number);
    }
}
