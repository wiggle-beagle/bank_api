package ru.sber.utils;

import com.sun.net.httpserver.HttpServer;
import ru.sber.controller.AccountBalanceHandler;
import ru.sber.controller.CardBalanceHandler;
import ru.sber.controller.CardListHandler;
import ru.sber.controller.CardCreateHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.SQLException;

public class Server {
    public void start() throws SQLException, IOException {
        CreateDataBase.create();
        HttpServer server = HttpServer.create();
        server.bind(new InetSocketAddress(8787), 0);
        server.createContext("/card/list", new CardListHandler());
        server.createContext("/card/create", new CardCreateHandler());
        server.createContext("/card/balance", new CardBalanceHandler());
        server.createContext("/account/balance", new AccountBalanceHandler());
        server.setExecutor(null);
        server.start();
    }
}
