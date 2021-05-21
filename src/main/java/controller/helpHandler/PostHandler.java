package controller.helpHandler;

import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class PostHandler {
    public static String requestBody(HttpExchange exchange) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8));
        OutputStream os = exchange.getResponseBody();
        int i;
        StringBuilder buf = new StringBuilder(512);
        while ((i = br.read()) != -1) {
            buf.append((char) i);
        }
        br.close();

        return buf.toString();
    }

    public static void responseBody(HttpExchange exchange, String response) throws IOException {
        OutputStream os = exchange.getResponseBody();
        exchange.sendResponseHeaders(200, response.getBytes().length);
        os.write(response.getBytes());
        os.flush();
        os.close();
    }
}
