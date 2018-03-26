package com.thoughtworks.step.bank;

import java.util.Date;

public class CreditTransaction extends Transaction {
    public CreditTransaction(Date date, double amount, String otherAccount) {
        super(date, amount, otherAccount);
    }
}
