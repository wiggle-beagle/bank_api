package helpHandler;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class GetHandler {
    
    public static void responseBody(HttpExchange exchange, String data) throws IOException {
        OutputStream os = exchange.getResponseBody();
        os = exchange.getResponseBody();
        exchange.sendResponseHeaders(200, data.getBytes().length);
        os.write(data.getBytes());
        os.flush();
        os.close();
        
    }
}
