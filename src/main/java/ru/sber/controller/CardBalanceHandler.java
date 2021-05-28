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
            Card card = new Card();
            card.setIdCard(id);
            String balance = null;
            try {
                balance = cardService.getCardBalance(id);
            } catch (SQLException throwable) {
                ErrorHandler.errorResponseBody(exchange);
            }
            assert balance != null;
            if (!balance.equals("error")) {
                card.setBalance(Double.parseDouble(balance));
                ObjectMapper mapper = new ObjectMapper();
                GetHandler.responseBody(exchange, mapper.writeValueAsString(card));
            } else {
                ErrorHandler.errorResponseBody(exchange);
            }
        } else if (method.equals("POST")) {
            ObjectMapper mapper = new ObjectMapper();
            Card card;
            String response = null;
            try {
                card = mapper.readValue(exchange.getRequestBody(), Card.class);
                response = cardService.addMoney(card.getIdCard(), card.getBalance());
            } catch (Exception e) {
                ErrorHandler.errorResponseBody(exchange);
            }
            assert response != null;
            if (!response.equals("error")) {
                PostHandler.responseBody(exchange, response);
            } else {
                ErrorHandler.errorResponseBody(exchange);
            }
        } else {
            ErrorHandler.errorResponseBody(exchange);
        }
    }
}
