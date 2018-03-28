package com.thoughtworks.step.bank;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class TransactionsTest {

    private Transactions transactions;

    @Before
    public void setUp() {
        transactions = new Transactions();
    }

    @Test
    public void mustRecordDebitTransaction() {
        transactions.debit(1000, "Pranav",1000);
        assertThat(transactions.list,hasItem(new DebitTransaction(new Date(),1000,"Pranav",1000)));
    }

    @Test
    public void mustRecordCreditTransaction() {
        transactions.credit(1000, "Pranav",1000);
        assertThat(transactions.list,hasItem(new CreditTransaction(new Date(),1000,"Pranav",1000)));
    }

    @Test
    public void mustRecordBothCreditAndDebitTransaction(){
        transactions.debit(1000,"Pranav",1000);
        Date debitDate = transactions.list.get(0).getDate();
        transactions.credit(1000,"Ashish",1000);
        Date creditDate = transactions.list.get(1).getDate();
        assertThat(transactions.list, hasItem(new DebitTransaction(debitDate,1000,"Pranav",1000)));
        assertThat(transactions.list, hasItem(new CreditTransaction(creditDate,1000,"Ashish",1000)));
    }

    @Test
    public void printTransaction() throws FileNotFoundException, UnsupportedEncodingException {
        ArrayList<String> result = new ArrayList<>();
        transactions.credit(1000,"Ashish",1000);
        CreditTransaction creditTransaction = new CreditTransaction(1000, "Ashish",1000);
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
        transactions.credit(1000,"Ashish",1000);
        transactions.credit(100,"Ashish",1000);
        transactions.credit(5000,"Ashish",1000);
        Transactions filteredTransaction = this.transactions.getTransactionAbove(1000);
        assertThat(filteredTransaction.list,hasItems(new CreditTransaction(5000,"Ashish",1000)));
    }

    @Test
    public void filterTransactionsByAmountLesserThan() {
        transactions.credit(1000,"Ashish",1000);
        transactions.credit(100,"Ashish",1000);
        transactions.credit(5000,"Ashish",1000);
        Transactions filteredTransaction = this.transactions.getTransactionBelow(1000);
        assertThat(filteredTransaction.list,hasItems(new CreditTransaction(100,"Ashish",1000)));
    }

    @Test
    public void filterAllCreditTransactions() {
        transactions.credit(1000,"Ashish",1000);
        transactions.debit(100,"Ashish",1000);
        transactions.credit(5000,"Ashish",1000);
        Transactions allCreditTransactions = this.transactions.getAllCreditTransactions();
        CreditTransaction creditTransaction = new CreditTransaction(1000, "Ashish",1000);
        CreditTransaction creditTransaction1 = new CreditTransaction(5000, "Ashish",1000);
        DebitTransaction debitTransaction = new DebitTransaction(100, "Ashish",1000);
        assertThat(allCreditTransactions.list,hasItems(creditTransaction,creditTransaction1));
        assertThat(allCreditTransactions.list,not(hasItems(debitTransaction)));
    }

    @Test
    public void filterAllDebitTransactions() {
        transactions.credit(1000,"Ashish",1000);
        transactions.debit(100,"Ashish",1000);
        transactions.credit(5000,"Ashish",1000);
        Transactions allDebitTransactions = this.transactions.getAllDebitTransactions();
        CreditTransaction creditTransaction = new CreditTransaction(1000, "Ashish",1000);
        CreditTransaction creditTransaction1 = new CreditTransaction(5000, "Ashish",1000);
        DebitTransaction debitTransaction = new DebitTransaction(100, "Ashish",1000);
        assertThat(allDebitTransactions.list,hasItems(debitTransaction));
        assertThat(allDebitTransactions.list,not(hasItems(creditTransaction,creditTransaction1)));
    }

    @Test
    public void writeCSVToFile() throws FileNotFoundException, UnsupportedEncodingException {
        String[] headers = {"Date","Amount","To","Balance"};
        ArrayList<String> result = new ArrayList<>();
        PrintWriter printWriter = new PrintWriter("foo.txt", "UTF-8") {
            @Override
            public void println(String x) {
                result.add(x);
            }
        };
        transactions.credit(1000,"Ashish", 1000);
        transactions.credit(2000,"Pranav", 1000);
        transactions.writeCSVTo(printWriter);
        assertThat(result, hasItems(String.join(",", Arrays.asList(headers))
                ,new DebitTransaction(transactions.list.get(0).getDate(), 1000,"Ashish", 1000).toCSV()
                ,new DebitTransaction(transactions.list.get(1).getDate(), 2000,"Pranav", 1000).toCSV()));
    }
}
