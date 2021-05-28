package ru.sber.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ru.sber.controller.helpHandler.ErrorHandler;
import ru.sber.controller.helpHandler.GetHandler;
import ru.sber.controller.helpHandler.PostHandler;
import ru.sber.repository.Account;
import ru.sber.service.CardService;

import java.io.IOException;
import java.sql.SQLException;

public class AccountBalanceHandler implements HttpHandler {
    private final CardService cardService = new CardService();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        long id = GetHandler.getID(exchange);
        Account account = new Account();
        account.setIdAccount(id);
        String balance = null;
        try {
            balance = cardService.getAccountBalance(id);
        } catch (SQLException throwable) {
            ErrorHandler.errorResponseBody(exchange);
        }
        assert balance != null;
        if (!balance.equals("error")) {
            account.setAccountBalance(Double.parseDouble(balance));
            ObjectMapper mapper = new ObjectMapper();
            PostHandler.responseBody(exchange, mapper.writeValueAsString(account));
        } else {
            ErrorHandler.errorResponseBody(exchange);
        }
    }
}
