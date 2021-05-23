package ru.sber.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ru.sber.controller.helpHandler.ErrorHandler;
import ru.sber.controller.helpHandler.GetHandler;
import ru.sber.service.CardService;

import java.io.*;
import java.sql.SQLException;

public class CardListHandler implements HttpHandler {
    private final CardService cardService = new CardService();

    @Override
    public void handle(HttpExchange exchange) throws IOException {

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
        GetHandler.responseBody(exchange, accounts);
    }
}
