package com.thoughtworks.step.bank;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
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
        Transactions filteredTransaction = this.transactions.getTransactionAbove(1000);
        assertThat(filteredTransaction.list,hasItems(new CreditTransaction(5000,"Ashish")));
    }

    @Test
    public void filterTransactionsByAmountLesserThan() {
        transactions.credit(1000,"Ashish");
        transactions.credit(100,"Ashish");
        transactions.credit(5000,"Ashish");
        Transactions filteredTransaction = this.transactions.getTransactionBelow(1000);
        assertThat(filteredTransaction.list,hasItems(new CreditTransaction(100,"Ashish")));
    }

    @Test
    public void filterAllCreditTransactions() {
        transactions.credit(1000,"Ashish");
        transactions.debit(100,"Ashish");
        transactions.credit(5000,"Ashish");
        Transactions allCreditTransactions = this.transactions.getAllCreditTransactions();
        CreditTransaction creditTransaction = new CreditTransaction(1000, "Ashish");
        CreditTransaction creditTransaction1 = new CreditTransaction(5000, "Ashish");
        DebitTransaction debitTransaction = new DebitTransaction(100, "Ashish");
        assertThat(allCreditTransactions.list,hasItems(creditTransaction,creditTransaction1));
        assertThat(allCreditTransactions.list,not(hasItems(debitTransaction)));
    }

    @Test
    public void filterAllDebitTransactions() {
        transactions.credit(1000,"Ashish");
        transactions.debit(100,"Ashish");
        transactions.credit(5000,"Ashish");
        Transactions allDebitTransactions = this.transactions.getAllDebitTransactions();
        CreditTransaction creditTransaction = new CreditTransaction(1000, "Ashish");
        CreditTransaction creditTransaction1 = new CreditTransaction(5000, "Ashish");
        DebitTransaction debitTransaction = new DebitTransaction(100, "Ashish");
        assertThat(allDebitTransactions.list,hasItems(debitTransaction));
        assertThat(allDebitTransactions.list,not(hasItems(creditTransaction,creditTransaction1)));
    }

//    @Test
//    public void should_write_to_csv_file() throws IOException {
//        transactions.credit(1000,"Ashish");
//        ArrayList<String> result = new ArrayList<>();
//        String headers = "date,amount,source,type";
//        CsvPrinter csvPrinter;
//        try (FileWriter fileWriter = new FileWriter("foo.csv") {
//            @Override
//            public Writer append(CharSequence csq) {
//                result.add((String) csq);
//                return this;
//            }
//        }) {
//            csvPrinter = new CsvPrinter(fileWriter, headers);
//        }
//        csvPrinter.writeHeaders();
//        assertThat(result,hasItems(headers,String.valueOf(creditOf1000.getAmount()),"CREDIT"));
//        assertThat(result,not(hasItems("DEBIT")));
//        transactions.debit(100,"AnotherAccount");
//        assertThat(result,hasItems("DEBIT"));
//        csvPrinter.close();
}
