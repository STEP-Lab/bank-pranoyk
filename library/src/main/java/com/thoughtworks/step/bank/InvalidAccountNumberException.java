package com.thoughtworks.step.bank;

public class InvalidAccountNumberException extends Exception {
    public InvalidAccountNumberException() {
        super("Invalid account number");
    }
}
