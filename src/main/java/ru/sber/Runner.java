package ru.sber;

import ru.sber.utils.Server;

import java.io.IOException;
import java.sql.SQLException;

public class Runner {
    public static void main(String[] args) throws IOException, SQLException {

        new Server().start();
    }
}
