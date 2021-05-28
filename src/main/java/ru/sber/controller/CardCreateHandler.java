package ru.sber.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ru.sber.controller.helpHandler.ErrorHandler;
import ru.sber.controller.helpHandler.PostHandler;
import ru.sber.repository.Account;
import ru.sber.service.CardService;

import java.io.IOException;
import java.sql.SQLException;


public class CardCreateHandler implements HttpHandler {
    private final CardService cardService = new CardService();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = null;
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(exchange.getRequestBody(), Account.class);
        try {
            if (account.getIdAccount() != 0) {
                response = cardService.createCardByAccount(account.getIdAccount());
            } else {
                response = cardService.createCardAndAccount(account.getIdClient());
            }
        } catch (SQLException throwable) {
            ErrorHandler.errorResponseBody(exchange);
        }
        assert response != null;
        if (!response.equals("error")) {
            PostHandler.responseBody(exchange, response);
        }
        else {
            ErrorHandler.errorResponseBody(exchange);
        }

    }
}
