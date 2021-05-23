package ru.sber.repository;

public class Card {
    private long idCard;
    private double balance;
    private long idAccount;

    public Card(double balance, long idAccount) {
        this.balance = balance;
        this.idAccount = idAccount;
    }

    public Card(long idCard, double balance, long idAccount) {
        this.idCard = idCard;
        this.balance = balance;
        this.idAccount = idAccount;
    }

    public Card() {
    }

    public long getIdCard() {
        return idCard;
    }

    public double getBalance() {
        return balance;
    }

    public long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(long idAccount) {
        this.idAccount = idAccount;
    }

    @Override
    public String toString() {
        return "Card{" +
                "idCard=" + idCard +
                ", balance=" + balance +
                ", idAccount=" + idAccount +
                '}';
    }
}
