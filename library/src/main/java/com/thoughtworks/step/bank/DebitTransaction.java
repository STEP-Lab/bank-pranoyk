package com.thoughtworks.step.bank;

import java.util.Date;

public class DebitTransaction extends Transaction {

    protected DebitTransaction(Date date, double amount, String to, double balance) {
        super(date, amount, to, balance);
    }

    public DebitTransaction(double amount, String name, double balance) {
        this(new Date(), amount, name, balance);
    }
}
