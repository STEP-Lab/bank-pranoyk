package com.thoughtworks.step.bank;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;

public class TransactionsTest {

    private Transactions transactions;

    @Before
    public void setUp() throws Exception {
        transactions = new Transactions();
    }

    @Test
    public void mustRecordDebitTransaction() {
        transactions.debit(1000, "Pranav");
        assertThat(transactions.list,hasItem(new DebitTransaction(new Date(),1000,"Pranav")));
    }

    @Test
    public void mustRecordCreditTransaction() {
        transactions.credit(1000, "Pranav");
        assertThat(transactions.list,hasItem(new CreditTransaction(new Date(),1000,"Pranav")));
    }

    @Test
    public void mustRecordBothCreditAndDebitTransaction(){
        transactions.debit(1000,"Pranav");
        Date debitDate = transactions.list.get(0).getDate();
        transactions.credit(1000,"Ashish");
        Date creditDate = transactions.list.get(1).getDate();
        assertThat(transactions.list, hasItem(new DebitTransaction(debitDate,1000,"Pranav")));
        assertThat(transactions.list, hasItem(new CreditTransaction(creditDate,1000,"Ashish")));
    }
}
