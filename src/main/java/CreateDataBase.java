import repository.CRUD.H2JDBCUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDataBase {
    public static void create(String path) throws SQLException, IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        String s = new String(encoded, StandardCharsets.UTF_8);
        Connection connection = H2JDBCUtils.getConnection();
        Statement preparedStatement = connection.createStatement();
        preparedStatement.execute(s);
    }
}
