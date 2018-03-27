package com.thoughtworks.step.bank;

import java.util.Date;

public class CreditTransaction extends Transaction {

    protected CreditTransaction(Date date, double amount, String otherAccount) {
        super(date, amount, otherAccount);
        this.isCredit =true;
    }

    public CreditTransaction(double amount, String name) {
        this(new Date(), amount, name);
    }
}
