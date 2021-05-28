package ru.sber.controller.helpHandler;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;


public class PostHandler {

    public static void responseBody(HttpExchange exchange, String response) throws IOException {

        OutputStream os = exchange.getResponseBody();
        exchange.sendResponseHeaders(200, response.getBytes().length);
        os.write(response.getBytes());
        os.flush();
        os.close();
    }
}
