package com.thoughtworks.step.bank;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TransactionTest {

    @Test
    public void mustRecordCorrectTransactionDateForDebit() {
        Date date = new Date();
        Transaction transaction = new DebitTransaction(date, 1000, "Other Account",1000);
        assertThat(transaction.getDate(),is(date));
    }

    @Test
    public void mustRecordCorrectTransactionDateForCredit() {
        Date date = new Date();
        Transaction transaction = new CreditTransaction(date, 1000, "Other Account",1000);
        assertThat(transaction.getDate(),is(date));
    }
//
//    @Test
//    public void mustReturnCSVFormat() {
//        Date date = new Date();
//        DebitTransaction transaction = new DebitTransaction(date, 100, "Other Account", 1200);
//        assertThat(transaction.toCSV(),is(date.toString(),1000,"Other Account",1000));
//    }
}
