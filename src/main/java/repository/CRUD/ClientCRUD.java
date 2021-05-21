package repository.CRUD;

import repository.Client;
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
}
