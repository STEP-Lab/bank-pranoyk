package com.thoughtworks.step.bank;

import java.util.Date;
import java.util.Objects;

public abstract class Transaction {
    protected Date date;
    protected final double amount;
    protected final String to;
    protected double balance;

    public Transaction(Date date, double amount, String to, double balance) {
        this.date = date;
        this.amount = amount;
        this.to = to;
        this.balance = balance;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.amount, amount) == 0 &&
                Double.compare(that.balance, balance) == 0 &&
                Objects.equals(to, that.to) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, to);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", amount=" + amount +
                ", to='" + to + '\'' +
                ", balance=" + balance +
                '}';
    }

    public String toCSV() {
        return String.format("%s,%s,%s,%s",date,amount,to,balance);
    }
}
