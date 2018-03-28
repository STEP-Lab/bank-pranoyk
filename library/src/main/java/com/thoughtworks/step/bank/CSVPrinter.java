package com.thoughtworks.step.bank;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class CSVPrinter {
    private final PrintWriter printWriter;
    private final CharSequence COMA_DELIMETER = ",";

    public CSVPrinter(PrintWriter printWriter, String[] headers) {
        this.printWriter = printWriter;
        printWriter.println(String.join(COMA_DELIMETER, Arrays.asList(headers)));
    }

    public void write(Transaction transaction) {
        printWriter.println(transaction.toCSV());
    }

    public void write(ArrayList<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            printWriter.println(transaction.toCSV());
        }
    }

    public void close() {
        printWriter.close();
    }
}
