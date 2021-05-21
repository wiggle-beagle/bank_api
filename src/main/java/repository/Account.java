package repository;

public class Account {
    long idAccount;
    int idClient;

    public Account(int idClient) {
        this.idClient = idClient;
    }

    public Account(long idAccount, int idClient) {
        this.idAccount = idAccount;
        this.idClient = idClient;
    }

    public Account() {
    }

    public long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(long idAccount) {
        this.idAccount = idAccount;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    @Override
    public String toString() {
        return "Account{" +
                "idAccount=" + idAccount +
                ", idClient=" + idClient +
                '}';
    }
}



