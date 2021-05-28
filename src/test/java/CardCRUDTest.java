import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.sber.repository.CRUD.CardCRUDImpl;
import ru.sber.utils.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;

class CardCRUDTest {

    CardCRUDImpl cardCRUD = new CardCRUDImpl();

    @BeforeAll
    public static void start() throws IOException, SQLException {
        new Server().start();
    }

    @Test
    void test_depositCurrency() throws SQLException {
        long idCard = 4276380011110004L;
        double money = 500;
        String expected = Double.toString(1480);
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
        String expected = Double.toString(3000);
        String result = cardCRUD.getCardBalance(cardID);
        Assertions.assertEquals(expected, result);
    }


    @Test
    void test_getAccountBalance() {
        long idAccount = 10114444321L;
        String expected = Double.toString(53150);
        String result = cardCRUD.getAccountBalance(idAccount);
        Assertions.assertEquals(expected, result);
    }


    @Test
    void test_getCardBalance_GET() throws IOException, SQLException {
        String address = "http://localhost:8182/card/balance?idCard=4276380011110002";
        URL url = new URL(address);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String result = in.readLine();
        String balance = cardCRUD.getCardBalance(4276380011110002L);
        String expected = "{\"idCard\":4276380011110002,\"balance\":" + balance + "}";
        Assertions.assertEquals(expected, result);


    }
    @Test
    void test_getCardByIdAccount_GET() throws IOException {
        String address = "http://localhost:8182/card/list?idAccount=10114444321";
        URL url = new URL(address);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        String input;
        StringBuilder sb = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while((input=in.readLine())!=null){
            sb.append(input);
        }
        String result = sb.toString();
        String expected = "[{\"idCard\":4276380011110000},{\"idCard\":4276380011110001},{\"idCard\":4276380011110002}]";
        Assertions.assertEquals(expected, result);


    }
}
