package com.thoughtworks.step.bank;

import java.util.Date;

public class DebitTransaction extends Transaction {

    protected DebitTransaction(Date date, float amount, String to) {
        super(date, amount, to);
    }

    public DebitTransaction(float amount, String name) {
        this(new Date(), amount, name);
    }
}
