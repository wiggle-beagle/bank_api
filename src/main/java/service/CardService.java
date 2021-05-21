package service;

import repository.CRUD.AccountCRUD;
import repository.CRUD.CardCRUD;
import java.io.FileNotFoundException;
import java.sql.SQLException;


public class CardService {
    CardCRUD cardCRUD = new CardCRUD();
    AccountCRUD accountCRUD = new AccountCRUD();

    public static void main(String[] args) throws FileNotFoundException, SQLException {
        CardService cardService = new CardService();
        System.out.println(cardService.createCardAndAccount(2));
    }

    public String createCardByAccount(long idAccount) throws FileNotFoundException, SQLException {
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

    public String createCardAndAccount(int idClient) throws SQLException, FileNotFoundException {
        Long idAccount = accountCRUD.createAccount(idClient);
        return cardCRUD.createNewCardByAccount(idAccount) + ". Account created: "+idAccount;
    }

}
