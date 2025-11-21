package com.example;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import com.example.repository.PostgreSQLRepository;
import com.example.service.Service;

public class Main {
    private static List<String> moves = new ArrayList<>();
    public static void main(String[] args) throws SQLException{
        PostgreSQLRepository repo = new PostgreSQLRepository();
        Service service = new Service(repo);
        Scanner scanner = new Scanner(System.in);
        greeting();
        String input;
        do{
            System.out.print(">>> ");
            input = scanner.nextLine();
            switch(input.toLowerCase()){
                case "help": service.help(); break;
                case "show best move":
                case "sbm": service.showBestMove(moves); break;
                case "print moves":
                case "pm": printMoves(); break;
                case "undo move":
                case "um": undoMove(); break;
                case "clear moves":
                case "cm": if(moves.isEmpty()){
                            System.out.println("Your list is already empty");
                            } else {moves.clear();}
                            break;
                case "exit": break;
                default: if(service.isNotation(input)){
                            moves.add(input);
                            } else {System.out.println("Invalid entry");}
            }
        } while (!input.equalsIgnoreCase("exit"));
        System.out.println("Goodbye!");
        scanner.close();
    }

    public static void printMoves(){
        for(int i = 0; i < moves.size(); i++){
            System.out.println((i+1) + ". " + moves.get(i));
        }
    }

    public static void undoMove(){
        if(moves.isEmpty()){
            System.out.println("Your list is already empty");
        } else {moves.removeLast();}
    }

    public static void clearMoves(){
        if(moves.isEmpty()){
            System.out.println("Your list is already empty");
        } else {moves.clear();}
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