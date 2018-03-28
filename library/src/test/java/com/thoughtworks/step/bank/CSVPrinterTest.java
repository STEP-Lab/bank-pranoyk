package com.thoughtworks.step.bank;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;

public class CSVPrinterTest {

    private ArrayList<String> result;
    private PrintWriter printWriter;
    private Date date;
    private String[] headers;
    private CSVPrinter csvPrinter;

    @Before
    public void setUp() throws Exception {
        result = new ArrayList<>();
        printWriter = new PrintWriter("foo.txt", "UTF-8") {
            @Override
            public void println(String x) {
                result.add(x);
            }
        };
        date = new Date();
        headers = new String[]{"Date","Amount","To","Balance"};
        csvPrinter = new CSVPrinter(printWriter, headers);
    }

    @Test
    public void shouldWriteCSVToFile() {
        csvPrinter.write(new DebitTransaction(date,1000,"Other Account", 2000));
        csvPrinter.close();
        assertThat(result, hasItems(String.join(",", Arrays.asList(headers))
                ,date.toString()+",1000.0,Other Account,2000.0"));
    }

    @Test
    public void shouldWriteCSVOfListToFile() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(new DebitTransaction(date, 100,"Ashish", 1000));
        transactions.add(new DebitTransaction(date, 200,"Viraj", 2000));
        csvPrinter.write(transactions);
        csvPrinter.close();
        assertThat(result.size(), is(3));
        assertThat(result, hasItems(String.join(",", Arrays.asList(headers))
                ,new DebitTransaction(date, 100,"Ashish", 1000).toCSV()
                ,new DebitTransaction(date, 200,"Viraj", 2000).toCSV()));
    }
}
