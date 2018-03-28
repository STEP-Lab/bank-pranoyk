package com.thoughtworks.step.bank;

import java.util.Date;

public class CreditTransaction extends Transaction {

    protected CreditTransaction(Date date, double amount, String otherAccount, double balance) {
        super(date, amount, otherAccount, balance);
    }

    public CreditTransaction(double amount, String name, double balance) {
        this(new Date(), amount, name, balance);
    }
}
