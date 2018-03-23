package com.thoughtworks.step.bank;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TransactionTest {
    @Test
    public void mustRecordCorrectTransactionDate() {
        Date date = new Date();
        Transaction transaction = new DebitTransaction(date, 1000, "Other Account");
        assertThat(transaction.getDate(),is(date));
    }
}
