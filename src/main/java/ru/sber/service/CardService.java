package ru.sber.service;

import ru.sber.repository.CRUD.AccountCRUD;
import ru.sber.repository.CRUD.AccountCRUDImpl;
import ru.sber.repository.CRUD.CardCRUD;
import ru.sber.repository.CRUD.CardCRUDImpl;

import java.sql.SQLException;


public class CardService {
    private final CardCRUD cardCRUD = new CardCRUDImpl();
    private final AccountCRUD accountCRUD = new AccountCRUDImpl();

    public String createCardByAccount(long idAccount) throws SQLException {
        return cardCRUD.createNewCardByAccount(idAccount);
    }

    public String getListOfCardsByIdAccount(long idAccount) throws SQLException {
        return cardCRUD.getListOfCardsByIdAccount(idAccount);
    }

    public String addMoney(long id, double money) {
        return cardCRUD.depositCurrency(id, money);
    }

    public String getCardBalance(long idCard) throws SQLException {
        return cardCRUD.getCardBalance(idCard);
    }

    public String getAccountBalance(long idAccount) throws SQLException {
        return cardCRUD.getAccountBalance(idAccount);
    }

    public String getListOfCardsByIdClient(int idClient) throws SQLException {
        return cardCRUD.getListOfCardsByIdClient(idClient);
    }

    public String createCardAndAccount(int idClient) throws SQLException {
        Long idAccount = accountCRUD.createAccount(idClient);
        return cardCRUD.createNewCardByAccount(idAccount) + ". Account created: " + idAccount;
    }

}
