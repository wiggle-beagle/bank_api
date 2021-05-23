package ru.sber.controller.helpHandler;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class GetHandler {

    public static void responseBody(HttpExchange exchange, String data) throws IOException {
        OutputStream os = exchange.getResponseBody();
        exchange.sendResponseHeaders(200, data.getBytes().length);
        os.write(data.getBytes());
        os.flush();
        os.close();
    }
    public static long getID(HttpExchange exchange) {
        String query = exchange.getRequestURI().getQuery();
        String[] param = query.split("=");

        return Long.parseLong(param[1]);
    }

    public static String getField(HttpExchange exchange) {
        String query = exchange.getRequestURI().getQuery();
        String[] param = query.split("=");

        return param[0];
    }
}
