import com.sun.net.httpserver.HttpServer;
import controller.CardAndAccountHandler;
import controller.CardHandler;
import controller.BalanceHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws IOException, SQLException {

        CreateDataBase.create("src/main/resources/createDB.sql");
//        CardCRUD cardCRUD = new CardCRUD();
//        cardCRUD.depositCurrency(4276380011110013L, 123);
//        System.out.println(cardCRUD.getCardBalance(4276380011110013L));
        HttpServer server = HttpServer.create();
        server.bind(new InetSocketAddress(8000), 0);
        server.createContext("/cardAndAccount", new CardAndAccountHandler());
        server.createContext("/card", new CardHandler());
        server.createContext("/balance", new BalanceHandler());
        server.setExecutor(null);
        server.start();
    }
}
