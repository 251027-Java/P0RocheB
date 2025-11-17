package com.example.service;

import com.example.Game;
import com.example.Move;
import com.example.repository.PostgreSQLRepository;

import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Service {
    private PostgreSQLRepository repo;

    public Service(PostgreSQLRepository repo){
        this.repo = repo;
        if(repo.notationsIsEmpty()){ loadNotations(); }
        if(repo.gamesIsEmpty()){ loadGames(); }
    }

    public void loadNotations(){
        System.out.println("Loading notations");
        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream("./demo/src/main/resources/notations.txt");
            sc = new Scanner(inputStream, "UTF-8");
            double lineCount = Files.lines(Paths.get("./demo/src/main/resources/notations.txt")).count();
            double i = 1;
            String progressBar = "";
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                repo.createNotations(new Move(0, "", line));
                i++;
                progressBar = "[";
                for(int j = 0; j < (i/lineCount)*100; j++){
                    progressBar += "=";
                }
                for(int j = 0; j < 100-((i/lineCount)*100); j++){
                    progressBar += " ";
                }
                progressBar += "]\r";
                System.out.print(progressBar);
            }
            System.out.println(progressBar);
            System.out.println("Successfully loaded all notations into chessql");
        } catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        finally {
            if (inputStream != null) {
                try{
                    inputStream.close();
                } catch (IOException e){}
            }
            if (sc != null) {
                sc.close();
            }
        }
    }

    public void loadGames(){
        System.out.println("Loading games");
        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream("./demo/src/main/resources/DATABASE4U.txt");
            sc = new Scanner(inputStream, "UTF-8");
            double lineCount = Files.lines(Paths.get("./demo/src/main/resources/DATABASE4U.txt"), StandardCharsets.UTF_8).count();
            double i = 1;
            int id = 1;
            String progressBar = "";
            while (sc.hasNextLine()) {
                String game = "";
                String line = sc.nextLine();
                while(!line.equals("\n")){
                    game += line;
                    i++;
                }
                repo.createGame(new Game(id, game));
                id++;
                progressBar = "[";
                for(int j = 0; j < (i/lineCount)*100; j++){
                    progressBar += "=";
                }
                for(int j = 0; j < 100-((i/lineCount)*100); j++){
                    progressBar += " ";
                }
                progressBar += "]\r";
                System.out.print(progressBar);
            }
            System.out.println(progressBar);
            System.out.println("Successfully loaded all games into chessql");
        } catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        finally {
            if (inputStream != null) {
                try{
                    inputStream.close();
                } catch (IOException e){}
            }
            if (sc != null) {
                sc.close();
            }
        }
    }
}
