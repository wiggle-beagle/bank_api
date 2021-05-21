package repository.CRUD;

import java.sql.*;

public class CardCRUD {
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


    public String createNewCardByAccount(long idAccount) {
        long idCard = 0;
        try (Connection connection = H2JDBCUtils.getConnection()) {
            PreparedStatement preparedStatementCard = connection.prepareStatement(INSERT_CARD_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatementCard.setDouble(1, 0);
            preparedStatementCard.setLong(2, idAccount);
            preparedStatementCard.executeUpdate();

            ResultSet resultSet = preparedStatementCard.getGeneratedKeys();
            if (resultSet.next()) {
                idCard = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }

        return "card created: " + idCard;
    }

    public String getListOfCardsByIdAccount(long idAccount) throws SQLException {
        StringBuilder sb = new StringBuilder();
        try (Connection connection = H2JDBCUtils.getConnection()) {
            PreparedStatement preparedStatementCard = connection.prepareStatement(READ_CARDS_SQL_BY_ID_ACCOUNT);
            preparedStatementCard.setLong(1, idAccount);
            ResultSet resultSet = preparedStatementCard.executeQuery();

            while (resultSet.next()) {
                //System.out.println(resultSet.getLong("idCard"));
                sb.append(resultSet.getLong("idCard"));
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public String getListOfCardsByIdClient(int idClient) throws SQLException {
        StringBuilder sb = new StringBuilder();
        try (Connection connection = H2JDBCUtils.getConnection()) {
            PreparedStatement preparedStatementCard = connection.prepareStatement(READ_CARDS_SQL_BY_ID_CLIENT);
            preparedStatementCard.setLong(1, idClient);
            ResultSet resultSet = preparedStatementCard.executeQuery();

            while (resultSet.next()) {
                sb.append(resultSet.getLong("idCard"));
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public String getAccountBalance(long accountID) throws SQLException {
        double balance = 0L;
        try (Connection connection = H2JDBCUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_ACCOUNT_BALANCE);
            preparedStatement.setLong(1, accountID);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                balance = rs.getDouble("sumBalance");
                System.out.println(balance);
            }
        }
        return "Account balance = " + Double.toString(balance);
    }

    public String getCardBalance(long cardID) throws SQLException {
        double balance = 0;
        try (Connection connection = H2JDBCUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_CARD_BALANCE);
            preparedStatement.setLong(1, cardID);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                balance = rs.getDouble("balance");
            }
        }
        return "Card balance = " + Double.toString(balance);
    }

    public String depositCurrency(long idCard, double money) {
        try (Connection connection = H2JDBCUtils.getConnection()) {
            PreparedStatement preparedStatementCard = connection.prepareStatement(UPDATE_CARD_BALANCE);
            preparedStatementCard.setDouble(1, money);
            preparedStatementCard.setLong(2, idCard);
            preparedStatementCard.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return "money deposited";
    }
}
