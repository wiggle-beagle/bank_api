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
    void depositCurrency() {
    }

    @Test
    void createNewCardByAccount() {


    }

    @Test
    void getListOfCardsByIdAccount() throws SQLException {
        long idAccount = 10114444321L;
        String expected = "4276380011110000\n" +
                "4276380011110001\n" +
                "4276380011110002\n";
        String result = cardCRUD.getListOfCardsByIdAccount(idAccount);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void getListOfCardsByIdClient() throws SQLException {
        int idClient = 2;
        String expected = "4276380011110004\n" +
                "4276380011110005\n";
        String result = cardCRUD.getListOfCardsByIdClient(idClient);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void getCardBalance() throws SQLException {
        long cardID = 4276380011110001L;
        String expected = "Card balance = " + Double.toString(3000);
        String result = cardCRUD.getCardBalance(cardID);
        Assertions.assertEquals(expected, result);
    }


    @Test
    void getAccountBalance() throws SQLException {
        long idAccount = 10114444321L;
        String expected = "Account balance = " + Double.toString(53150);
        String result = cardCRUD.getAccountBalance(idAccount);
        Assertions.assertEquals(expected, result);
    }
}
