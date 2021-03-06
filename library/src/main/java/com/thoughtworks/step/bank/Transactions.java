package com.thoughtworks.step.bank;

import java.io.PrintWriter;
import java.util.ArrayList;

public class Transactions {

    protected ArrayList<Transaction> list;
    private Transactions transactions;

    public Transactions() {
        this.list = new ArrayList<>();
    }

    public void debit(double amount, String name, double balance){
        this.list.add(new DebitTransaction(amount, name, balance));
    }

    public void credit(double amount, String name, double balance) {
        this.list.add(new CreditTransaction(amount, name, balance));
    }

    public Transactions getTransactionAbove(double amount) {
        Transactions transactions = new Transactions();
        for (Transaction transaction : list) {
            if (transaction.amount > amount) {
                transactions.list.add(transaction);
            }
        }
        return transactions;
    }

    public void print(PrintWriter writer) {
        for (Transaction transaction : list) {
            writer.println(transaction.toString());
        }
    }

    public Transactions getTransactionBelow(double amount) {
        Transactions transactions = new Transactions();
        for (Transaction transaction : list) {
            if (transaction.amount < amount) {
                transactions.list.add(transaction);
            }
        }
        return transactions;
    }

    public Transactions getAllCreditTransactions() {
        Transactions transactions = new Transactions();
        for (Transaction transaction : list) {
            if (transaction instanceof CreditTransaction){
                transactions.list.add(transaction);
            }
        }
        return transactions;
    }

    public Transactions getAllDebitTransactions() {
        Transactions transactions = new Transactions();
        for (Transaction transaction : list) {
            if (transaction instanceof DebitTransaction){
                transactions.list.add(transaction);
            }
        }
        return transactions;
    }

    public void writeCSVTo(PrintWriter printWriter) {
        String[] headers = {"Date","Amount","To","Balance"};
        CSVPrinter printer = new CSVPrinter(printWriter, headers);
        printer.write(list);
        printer.close();
    }
}
