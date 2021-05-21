package repository.CRUD;

import repository.Account;
import repository.Client;

import java.sql.*;

public class AccountCRUD {
    private static final String INSERT_ACCOUNT_SQL = "INSERT INTO Account" +
            "  (idClient) VALUES " +
            " ( ?)";


//    public static void main(String[] args) throws SQLException {
//        AccountCRUD accountCRUD = new AccountCRUD();
//        System.out.println(accountCRUD.createAccount(2));
//    }
    public Long createAccount(int idClient) throws SQLException {
        long idAccount = 0;
        try (Connection connection = H2JDBCUtils.getConnection()) {
            PreparedStatement preparedStatementAccount = connection.prepareStatement(INSERT_ACCOUNT_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatementAccount.setLong(1, idClient);
            preparedStatementAccount.executeUpdate();
            ResultSet rs = preparedStatementAccount.getGeneratedKeys();
            while (rs.next()) {
                idAccount = rs.getLong("idAccount");
            }

            return idAccount;
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }

        return idAccount;
    }
}
