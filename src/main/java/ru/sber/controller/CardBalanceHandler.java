package ru.sber.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ru.sber.controller.helpHandler.ErrorHandler;
import ru.sber.controller.helpHandler.GetHandler;
import ru.sber.controller.helpHandler.PostHandler;
import ru.sber.repository.Card;
import ru.sber.service.CardService;

import java.io.IOException;
import java.sql.SQLException;

public class CardBalanceHandler implements HttpHandler {
    private final CardService cardService = new CardService();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();

        if (method.equals("GET")) {
            long id = GetHandler.getID(exchange);
            String balance = null;
            try {
                balance = cardService.getCardBalance(id);
            } catch (SQLException throwable) {
                ErrorHandler.errorResponseBody(exchange);
            }
            assert balance != null;
            GetHandler.responseBody(exchange, balance);

        } else if (method.equals("POST")) {
            ObjectMapper mapper = new ObjectMapper();
            Card card = null;
            try {
                card = mapper.readValue(exchange.getRequestBody(), Card.class);
            } catch (Exception e) {
                ErrorHandler.errorResponseBody(exchange);
            }

            assert card != null;
            String response = cardService.addMoney(card.getIdCard(), card.getBalance());
            PostHandler.responseBody(exchange, response);
        }

    }
}
