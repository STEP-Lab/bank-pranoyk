package com.thoughtworks.step.bank;

import java.util.Date;

public class DebitTransaction extends Transaction {

    public DebitTransaction(Date date, float amount, String to) {
        super(date, amount, to);
    }
}
