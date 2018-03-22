package com.thoughtworks.step.bank;

public class AccountNumber {
    public AccountNumber(String accountNumber) throws InvalidAccountNumberException {
        validateAccountNumber(accountNumber);
    }

    private void validateAccountNumber(String accountNumber) throws InvalidAccountNumberException {
        if (!accountNumber.matches("\\d{4}-\\d{4}")) {
            throw new InvalidAccountNumberException();
        }
    }
}
