package com.thoughtworks.step.bank;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;

public class TransactionsTest {

    @Test
    public void mustRecordDebitTransaction() {
        Transactions transactions = new Transactions();
        transactions.debit(1000, "Pranav");
        assertThat(transactions.list,hasItem(new DebitTransaction(new Date(),1000,"Pranav")));
    }

    @Test
    public void mustRecordCreditTransaction() {
        Transactions transactions = new Transactions();
        transactions.credit(1000, "Pranav");
        assertThat(transactions.list,hasItem(new CreditTransaction(new Date(),1000,"Pranav")));
    }
}
