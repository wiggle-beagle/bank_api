package ru.sber.repository.CRUD;

public interface AccountCRUD {
    default Long createAccount(int idClient) {
        return null;
    }
}
