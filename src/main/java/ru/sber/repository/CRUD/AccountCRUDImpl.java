package ru.sber.repository.CRUD;

import java.sql.*;

public class AccountCRUDImpl implements AccountCRUD {
    private static final String INSERT_ACCOUNT_SQL = "INSERT INTO Account" +
            "  (idClient) VALUES " +
            " ( ?)";

    @Override
    public Long createAccount(int idClient) {
        long idAccount = 0;
        try (Connection connection = H2JDBCUtils.getConnection();
             PreparedStatement preparedStatementAccount = connection.prepareStatement(INSERT_ACCOUNT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatementAccount.setLong(1, idClient);
            preparedStatementAccount.executeUpdate();
            ResultSet rs = preparedStatementAccount.getGeneratedKeys();
            while (rs.next()) {
                idAccount = rs.getLong("idAccount");
            }
            rs.close();

        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }

        return idAccount;
    }
}
