package com.example;

import java.sql.SQLException;
import com.example.repository.PostgreSQLRepository;
import com.example.service.Service;

public class Main {
    public static void main(String[] args) throws SQLException{
        PostgreSQLRepository repo = new PostgreSQLRepository();
        Service service = new Service(repo);
        greeting();
        ChesSQL chesSQL = new ChesSQL(service);
        chesSQL.prompt();
    }

    public static void greeting(){
        System.out.println(
            "   █████████  █████                        █████████     ██████    █████\n" +
            "  ███░░░░░███░░███                        ███░░░░░███  ███░░░░███ ░░███       \n" +
            " ███     ░░░  ░███████    ██████   █████ ░███    ░░░  ███    ░░███ ░███       \n" +
            "░███          ░███░░███  ███░░███ ███░░  ░░█████████ ░███     ░███ ░███       \n" +
            "░███          ░███ ░███ ░███████ ░░█████  ░░░░░░░░███░███   ██░███ ░███       \n" +
            "░░███     ███ ░███ ░███ ░███░░░   ░░░░███ ███    ░███░░███ ░░████  ░███      █\n" +
            " ░░█████████  ████ █████░░██████  ██████ ░░█████████  ░░░██████░██ ███████████\n" +
            "  ░░░░░░░░░  ░░░░ ░░░░░  ░░░░░░  ░░░░░░   ░░░░░░░░░     ░░░░░░ ░░ ░░░░░░░░░░░ \n\n\n" + 
            "Welcome to ChesSQL! The database-backed application where you can see which move\n" + 
            "has led to the most wins from a given position. Type \"help\" to get a list of\n" +
            "options, \"exit\" to quit the application, or a chess move in algebraic notation\n" +
            "to begin setting up a position.\n\n");
    }
}