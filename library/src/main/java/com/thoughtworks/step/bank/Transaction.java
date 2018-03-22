package com.thoughtworks.step.bank;

import java.util.Date;

public class Transaction {
    protected Date date;
    protected final float amount;
    protected final String to;

    public Transaction(Date date, float amount, String to) {
        this.date = date;
        this.amount = amount;
        this.to = to;
    }

    public Date getDate() {
        return date;
    }
}
