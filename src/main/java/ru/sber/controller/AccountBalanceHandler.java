package ru.sber.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import ru.sber.controller.helpHandler.ErrorHandler;
import ru.sber.controller.helpHandler.GetHandler;
import ru.sber.repository.Card;
import ru.sber.service.CardService;

import java.io.IOException;
import java.sql.SQLException;

public class AccountBalanceHandler implements HttpHandler {
    private final CardService cardService = new CardService();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        long id = GetHandler.getID(exchange);
        String balance = null;
        try {
            balance = cardService.getAccountBalance(id);
        } catch (SQLException throwable) {
            ErrorHandler.errorResponseBody(exchange);
        }
        assert balance != null;
        GetHandler.responseBody(exchange, balance);
    }
}
