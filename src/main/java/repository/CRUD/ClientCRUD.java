package repository.CRUD;

import repository.Client;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientCRUD {
    private static final String INSERT_CLIENT_SQL = "INSERT INTO Client" +
            "  (FIO) VALUES " +
            " (?)";

    public void insertClient(Client newClient) throws SQLException {
        Client client = new Client(newClient.getFio());
        try (Connection connection = H2JDBCUtils.getConnection()) {
            PreparedStatement preparedStatementAccount = connection.prepareStatement(INSERT_CLIENT_SQL);
            preparedStatementAccount.setString(1, client.getFio());
            preparedStatementAccount.executeUpdate();
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }
    }

    public static void main(String[] args) throws SQLException, FileNotFoundException {
//        ClientCRUD clientCRUD = new ClientCRUD();
//        Client client = new Client(1, "Никонов Николай Петрович");
//        clientCRUD.insertClient(client);


//        CardCRUD cardCRUD = new CardCRUD();
//        Account account = new Account(100002, 1);
//        cardCRUD.insertCard(account);
//        cardCRUD.getListOfCardsByIdClient(client);
//        CardCRUD cardCRUD = new CardCRUD();
//        cardCRUD.depositCurrency(250,4276373200000002L);
//        Card card = new Card(500, 100001);
//        cardCRUD.checkAccountBalance(100002);
    }
}
