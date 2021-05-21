package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import controller.helpHandler.GetHandler;
import controller.helpHandler.PostHandler;
import repository.Client;
import service.CardService;

import java.io.*;
import java.sql.SQLException;

public class CardAndAccountHandler implements HttpHandler {
    CardService cardService = new CardService();
    String accounts;


    @Override
    public void handle(HttpExchange exchange) throws IOException {
        OutputStream os = null;
        String method = exchange.getRequestMethod();
        String response = null;

        if (method.equals("GET")) {
            String query = exchange.getRequestURI().getQuery();
            String[] param = query.split("=");
            int idClient = Integer.parseInt(param[1]);
            try {
                accounts = cardService.getListOfCardsByIdClient(idClient);
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }

            GetHandler.responseBody(exchange, accounts);


        } else if (method.equals("POST")) {
            String buf = PostHandler.requestBody(exchange);
            ObjectMapper mapper = new ObjectMapper();
            Client client = mapper.readValue(buf, Client.class);
            try {
                response = cardService.createCardAndAccount(client.getIdClient());
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
            PostHandler.responseBody(exchange, response);
        }
    }
}
