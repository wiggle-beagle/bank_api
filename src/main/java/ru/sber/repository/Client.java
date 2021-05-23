package ru.sber.repository;

public class Client {
    private int idClient;
    private String fio;

    public Client(int idClient, String fio) {
        this.idClient = idClient;
        this.fio = fio;
    }

    public Client() {
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", fio='" + fio + '\'' +
                '}';
    }
}
