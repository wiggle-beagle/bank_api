package repository;

public class Client {
    int idClient;
    String fio;


    public Client(int idClient, String fio) {
        this.idClient = idClient;
        this.fio = fio;

    }

    public Client(int idClient) {
        this.idClient = idClient;
    }

    public Client() {
    }

    public Client(String fio) {
        this.fio = fio;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", fio='" + fio + '\'' +
                '}';
    }
}
