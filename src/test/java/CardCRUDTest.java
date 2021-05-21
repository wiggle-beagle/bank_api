import org.junit.jupiter.api.*;
import repository.CRUD.CardCRUD;

import java.io.IOException;
import java.sql.SQLException;

class CardCRUDTest {
    CardCRUD cardCRUD = new CardCRUD();

    @BeforeAll
    public static void start() throws IOException, SQLException {
        ServerTest.createDB();
    }


    @Test
    void test_depositCurrency() throws SQLException {
        long idCard = 4276380011110004L;
        double money = 500;
        String balance = cardCRUD.getCardBalance(idCard);
        String expected = "Card balance = " + Double.toString(1480);
        cardCRUD.depositCurrency(idCard, money);
        String result = cardCRUD.getCardBalance(idCard);
        Assertions.assertEquals(expected, result);

    }

    @Test
    void test_createNewCardByAccount() throws SQLException {
        long idAccount = 10114444321L;
        String expected = cardCRUD.getListOfCardsByIdAccount(idAccount);
        String result = cardCRUD.getListOfCardsByIdAccount(idAccount);
        cardCRUD.createNewCardByAccount(idAccount);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void test_getListOfCardsByIdAccount() throws SQLException {
        long idAccount = 10114444321L;
        String expected = "4276380011110000\n" +
                "4276380011110001\n" +
                "4276380011110002\n";
        String result = cardCRUD.getListOfCardsByIdAccount(idAccount);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void test_getListOfCardsByIdClient() throws SQLException {
        int idClient = 2;
        String expected = "4276380011110004\n" +
                "4276380011110005\n";
        String result = cardCRUD.getListOfCardsByIdClient(idClient);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void test_getCardBalance() throws SQLException {
        long cardID = 4276380011110001L;
        String expected = "Card balance = " + Double.toString(3000);
        String result = cardCRUD.getCardBalance(cardID);
        Assertions.assertEquals(expected, result);
    }


    @Test
    void test_getAccountBalance() throws SQLException {
        long idAccount = 10114444321L;
        String expected = "Account balance = " + Double.toString(53150);
        String result = cardCRUD.getAccountBalance(idAccount);
        Assertions.assertEquals(expected, result);
    }
}
