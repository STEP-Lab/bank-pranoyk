package com.thoughtworks.step.bank;

import java.io.PrintWriter;
import java.util.ArrayList;

public class Transactions {

    protected ArrayList<Transaction> list;
    private Transactions transactions;

    public Transactions() {
        this.list = new ArrayList<>();
    }

    public void debit(double amount, String name){
        this.list.add(new DebitTransaction(amount, name));
    }

    public void credit(double amount, String name) {
        this.list.add(new CreditTransaction(amount, name));
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
            if (transaction.isCreditTransaction()){
                transactions.list.add(transaction);
            }
        }
        return transactions;
    }

    public Transactions getAllDebitTransactions() {
        Transactions transactions = new Transactions();
        for (Transaction transaction : list) {
            if (transaction.isDebitTransaction()){
                transactions.list.add(transaction);
            }
        }
        return transactions;
    }
}
