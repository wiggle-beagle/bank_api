package ru.sber.repository.CRUD;


import java.sql.*;

public class CardCRUDImpl implements CardCRUD {
    private static final String INSERT_CARD_SQL = "INSERT INTO Card" +
            "  (balance, idAccount) VALUES " +
            " (?,?)";
    private static final String READ_CARDS_SQL_BY_ID_ACCOUNT = "SELECT Card.idCard FROM Card" +
            " JOIN Account ON Card.idAccount = Account.idAccount" +
            " WHERE (Account.idAccount) = " +
            " (?)";

    private static final String READ_CARDS_SQL_BY_ID_CLIENT = "SELECT Card.idCard FROM Card" +
            " JOIN Account ON Card.idAccount = Account.idAccount" +
            " WHERE Account.idAccount IN " +
            "(SELECT ACCOUNT.idAccount FROM ACCOUNT " +
            "JOIN CLIENT ON Account.idClient = Client.idClient " +
            "WHERE (Client.idClient) = (?))";

    private static final String CHECK_CARD_BALANCE = "SELECT balance FROM Card" +
            " WHERE idCard = (?)";

    private static final String CHECK_ACCOUNT_BALANCE = "SELECT SUM(balance) as sumBalance FROM Card" +
            " WHERE (idAccount) = (?)";

    private static final String UPDATE_CARD_BALANCE = "UPDATE Card set balance = balance + (?) WHERE idCard = (?)";

    @Override
    public String createNewCardByAccount(long idAccount) {
        long idCard = 0;
        try (Connection connection = H2JDBCUtils.getConnection();
             PreparedStatement preparedStatementCard = connection.prepareStatement(INSERT_CARD_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatementCard.setDouble(1, 0);
            preparedStatementCard.setLong(2, idAccount);
            int i = preparedStatementCard.executeUpdate();
            if (i > 0) {
                ResultSet resultSet = preparedStatementCard.getGeneratedKeys();
                if (resultSet.next()) {
                    idCard = resultSet.getLong(1);
                }
                return Long.toString(idCard);
            }
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
            return "error";
        }
        return null;
    }

    @Override
    public String getListOfCardsByIdAccount(long idAccount) throws SQLException {
        StringBuilder sb = new StringBuilder();
        try (Connection connection = H2JDBCUtils.getConnection();
             PreparedStatement preparedStatementCard = connection.prepareStatement(READ_CARDS_SQL_BY_ID_ACCOUNT)) {
            preparedStatementCard.setLong(1, idAccount);
            ResultSet resultSet = preparedStatementCard.executeQuery();
            while (resultSet.next()) {
                sb.append(resultSet.getLong("idCard"));
                sb.append("\n");
            }
            resultSet.close();
        }
        return sb.toString();
    }

    @Override
    public String getListOfCardsByIdClient(int idClient) throws SQLException {
        StringBuilder sb = new StringBuilder();
        try (Connection connection = H2JDBCUtils.getConnection();
             PreparedStatement preparedStatementCard = connection.prepareStatement(READ_CARDS_SQL_BY_ID_CLIENT)) {
            preparedStatementCard.setLong(1, idClient);
            ResultSet resultSet = preparedStatementCard.executeQuery();

            while (resultSet.next()) {
                sb.append(resultSet.getLong("idCard"));
                sb.append("\n");
            }
            resultSet.close();
        }
        return sb.toString();
    }

    @Override
    public String getAccountBalance(long accountID) {
        try (Connection connection = H2JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_ACCOUNT_BALANCE)) {
            preparedStatement.setLong(1, accountID);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return Double.toString(rs.getDouble("sumBalance"));
        } catch (Exception e) {
            return "error";
        }
    }

    @Override
    public String getCardBalance(long cardID) throws SQLException {
        double balance;
        try (Connection connection = H2JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_CARD_BALANCE)) {
            preparedStatement.setLong(1, cardID);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                balance = rs.getDouble("balance");
                return Double.toString(balance);
            } else {
                return "error";
            }
        }
    }

    @Override
    public String depositCurrency(long idCard, double money) {
        try (Connection connection = H2JDBCUtils.getConnection();
             PreparedStatement preparedStatementCard = connection.prepareStatement(UPDATE_CARD_BALANCE)) {
            preparedStatementCard.setDouble(1, money);
            preparedStatementCard.setLong(2, idCard);
            int i = preparedStatementCard.executeUpdate();
            if (i > 0) {
                return "money deposited";
            } else {
                return "error";
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return "error";
        }

    }
}
