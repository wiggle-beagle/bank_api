import ru.sber.utils.Server;

import java.io.IOException;
import java.sql.SQLException;


public class ServerTest {
    public static void main(String[] args) throws SQLException, IOException {
        new Server().start();
    }

}
