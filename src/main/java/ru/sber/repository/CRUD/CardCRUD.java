package ru.sber.repository.CRUD;

import java.sql.SQLException;

public interface CardCRUD {
    default String createNewCardByAccount(long idAccount) {
        return null;
    }

    default String getListOfCardsByIdAccount(long idAccount) throws SQLException {
        return null;
    }

    default String getListOfCardsByIdClient(int idClient) throws SQLException {
        return null;
    }

    default String getAccountBalance(long accountID) throws SQLException {
        return null;
    }

    default String getCardBalance(long cardID) throws SQLException  {
        return null;
    }

    default String depositCurrency(long idCard, double money)  {
        return null;
    }
}
