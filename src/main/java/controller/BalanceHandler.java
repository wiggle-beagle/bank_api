package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import controller.helpHandler.GetHandler;
import controller.helpHandler.PostHandler;
import repository.Card;
import service.CardService;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public class BalanceHandler implements HttpHandler {
    CardService cardService = new CardService();
    String balance;
    OutputStream os = null;

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();

        if (method.equals("GET")) {
            String query = exchange.getRequestURI().getQuery();
            String[] param = query.split("=");

            long id = Long.parseLong(param[1]);

            try {
                if (param[0].equals("idCard")) {
                    balance = cardService.getCardBalance(id);
                } else {
                    balance = cardService.getAccountBalance(id);
                }
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
            GetHandler.responseBody(exchange, balance);

        } else if (method.equals("POST")) {
            String buf = PostHandler.requestBody(exchange);
            ObjectMapper mapper = new ObjectMapper();
            Card card = mapper.readValue(buf, Card.class);

            String response = cardService.addMoney(card.getIdCard(), card.getBalance());
            PostHandler.responseBody(exchange, response);
        }

    }
}
