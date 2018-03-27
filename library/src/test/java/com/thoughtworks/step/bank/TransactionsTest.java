package com.thoughtworks.step.bank;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;

public class TransactionsTest {

    private Transactions transactions;

    @Before
    public void setUp() {
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

    @Test
    public void printTransaction() throws FileNotFoundException, UnsupportedEncodingException {
        ArrayList<String> result = new ArrayList<>();
        transactions.credit(1000,"Ashish");
        CreditTransaction creditTransaction = new CreditTransaction(1000, "Ashish");
        PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8"){
            @Override
            public void println(String x) {
                result.add(x);
            }
        };
        transactions.print(writer);
        writer.close();
        assertThat(result,hasItem(creditTransaction.toString()));
    }

    @Test
    public void filterTransactionsByAmountGreaterThan() {
        transactions.credit(1000,"Ashish");
        transactions.credit(100,"Ashish");
        transactions.credit(5000,"Ashish");
        Transactions filteredTransaction = this.transactions.filterByAmountGreaterThan(1000);
        assertThat(filteredTransaction.list,hasItems(new CreditTransaction(5000,"Ashish")));
    }

    @Test
    public void filterTransactionsByAmountLesserThan() {
        transactions.credit(1000,"Ashish");
        transactions.credit(100,"Ashish");
        transactions.credit(5000,"Ashish");
        Transactions filteredTransaction = this.transactions.filterByAmountLesserThan(1000);
        assertThat(filteredTransaction.list,hasItems(new CreditTransaction(100,"Ashish")));
    }

}
