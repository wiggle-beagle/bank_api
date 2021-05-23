package ru.sber.controller.helpHandler;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class ErrorHandler {
    public static void errorResponseBody(HttpExchange exchange) throws IOException {
        OutputStream os = exchange.getResponseBody();
        String error = "Данные некорректны";
        exchange.sendResponseHeaders(404, error.getBytes().length);
        os.write(error.getBytes());
        os.flush();
        os.close();
    }
}
