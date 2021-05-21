package repository;

public class Card {
    long idCard;
    double balance;
    long idAccount;

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

    public void setIdCard(long idCard) {
        this.idCard = idCard;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
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
