import com.sun.net.httpserver.HttpServer;
import controller.BalanceHandler;
import controller.CardAndAccountHandler;
import controller.CardHandler;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.SQLException;

public class ServerTest {
     public static void createServer() throws IOException {
        HttpServer server = HttpServer.create();
        server.bind(new InetSocketAddress(8090), 0);
        server.createContext("/cardAndAccount", new CardAndAccountHandler());
        server.createContext("/card", new CardHandler());
        server.createContext("/balance", new BalanceHandler());
        server.setExecutor(null);
        server.start();

    }

     public static void createDB() throws IOException, SQLException {
        CreateDataBase.create("src/main/resources/createDBTest.sql");
    }

}
