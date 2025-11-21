package com.example;

import com.example.service.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChesSQL {
    private List<String> moves;
    Scanner scanner;
    Service service;

    public ChesSQL(Service service){
        scanner = new Scanner(System.in);
        this.service = service;
        moves = new ArrayList<>();
    }

    public void prompt(){
        String input;
        do{
            System.out.print(">>> ");
            input = scanner.nextLine();
            switch(input.trim().toLowerCase()){
                case "help": service.help(); break;
                case "show best move":
                case "sbm": service.showBestMove(moves); break;
                case "print moves":
                case "pm": printMoves(); break;
                case "undo move":
                case "um": undoMove(); break;
                case "clear moves":
                case "cm": clearMoves(); break;
                case "exit": break;
                default: if(service.isNotation(input)){
                            moves.add(input);
                            } else {System.out.println("Invalid entry");}
            }
        } while (!input.equalsIgnoreCase("exit"));
        System.out.println("Goodbye!");
        scanner.close();
    }

    public void printMoves(){
        if(moves.isEmpty()){
            System.out.println("No moves to print");
        } else{
            for(int i = 0; i < moves.size(); i++){
                System.out.println((i+1) + ". " + moves.get(i));
            }
        }   
    }

    public void undoMove(){
        if(moves.isEmpty()){
            System.out.println("Your list is already empty");
        } else {moves.removeLast();}
    }

    public void clearMoves(){
        if(moves.isEmpty()){
            System.out.println("Your list is already empty");
        } else {moves.clear();}
    }
}
