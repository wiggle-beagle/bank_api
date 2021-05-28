package ru.sber.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ru.sber.controller.helpHandler.ErrorHandler;
import ru.sber.controller.helpHandler.GetHandler;
import ru.sber.service.CardService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardListHandler implements HttpHandler {
    private final CardService cardService = new CardService();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        List<ObjectNode> cardsList = new ArrayList<>();
        long id = GetHandler.getID(exchange);
        String field = GetHandler.getField(exchange);
        String accounts = null;
        try {
            if (field.equals("idAccount")) {
                accounts = cardService.getListOfCardsByIdAccount(id);
            } else if (field.equals("idClient"))
                accounts = cardService.getListOfCardsByIdClient((int) id);

        } catch (SQLException throwable) {
            ErrorHandler.errorResponseBody(exchange);
        }
        assert accounts != null;
        if (!accounts.isEmpty()) {
            ObjectMapper mapper = new ObjectMapper();
            String[] cards = accounts.split("\n");
            for (String card : cards) {
                ObjectNode node = mapper.createObjectNode();
                node.put("idCard", Long.parseLong(card));
                cardsList.add(node);
            }
            GetHandler.responseBody(exchange, mapper.writeValueAsString(cardsList));
        } else {
            ErrorHandler.errorResponseBody(exchange);
        }
    }
}
