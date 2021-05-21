package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import controller.helpHandler.GetHandler;
import controller.helpHandler.PostHandler;
import repository.Account;
import service.CardService;

import java.io.IOException;
import java.sql.SQLException;


public class CardHandler implements HttpHandler {
    CardService cardService = new CardService();
    String cards;

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String response = null;

        if (method.equals("GET")) {
            String query = exchange.getRequestURI().getQuery();
            String[] param = query.split("=");
            long idAccount = Long.parseLong(param[1]);
            try {
                cards = cardService.getListOfCardsByIdAccount(idAccount);
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
            GetHandler.responseBody(exchange, cards);


        } else if (method.equals("POST")) {
            String buf = PostHandler.requestBody(exchange);
            ObjectMapper mapper = new ObjectMapper();
            Account account = mapper.readValue(buf, Account.class);
            System.out.println(account.getIdAccount());
            try {
                response = cardService.createCardByAccount(account.getIdAccount());
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
            PostHandler.responseBody(exchange, response);
        }
    }
}
