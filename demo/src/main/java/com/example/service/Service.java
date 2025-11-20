package com.example.service;

import com.example.Game;
import com.example.Move;
import com.example.repository.PostgreSQLRepository;

import java.util.*;
import java.io.*;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Service {
    private PostgreSQLRepository repo;
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

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
            double byteCount = Files.lines(Paths.get("./demo/src/main/resources/notations.txt")).count();
            double i = 1;
            String progressBar = "";
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                repo.createNotations(new Move(0, "", line));
                i++;
                progressBar = String.format("%05.2f%%", ((i/byteCount)*100));
                progressBar = "[" + ANSI_GREEN;
                for(int j = 0; j < (i/byteCount)*100; j++){
                    progressBar += "=";
                }
                for(int j = 0; j < 100-((i/byteCount)*100); j++){
                    progressBar += " ";
                }
                progressBar += ANSI_RESET+ "]\r";
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
        try {
            CharsetDecoder decoder = StandardCharsets.UTF_8
            .newDecoder()
            .onMalformedInput(CodingErrorAction.REPLACE)
            .onUnmappableCharacter(CodingErrorAction.REPLACE);
            BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("./demo/src/main/resources/DATABASE4U.txt"), decoder)
            );
            long byteCount = new File("./demo/src/main/resources/DATABASE4U.txt").length();
            br.close();
            br = new BufferedReader(
                new InputStreamReader(new FileInputStream("./demo/src/main/resources/DATABASE4U.txt"), decoder)
            );
            double i = 1;
            int id = 1;
            String percent = "";
            StringBuilder progressBar = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                StringBuilder game = new StringBuilder();
                while (line != null && line.trim().isEmpty() == false) {
                    game.append(line).append("\n");
                    line = br.readLine();
                    i += line.length();
                }
                game.append("\n");
                line = br.readLine();
                while (line != null && line.trim().isEmpty() == false) {
                    game.append(line).append("\n");
                    line = br.readLine();
                    i += line.length();
                }
                Game newGame = new Game(id, game.toString());
                if(newGame.getID() != 0){
                    repo.createGame(newGame);
                    id++;
                }
                double progress = (i/byteCount)*100;
                if(!percent.equals(String.format("%05.2f%%", (progress)))){
                    percent = String.format("%05.2f%%", (progress));
                    progressBar.setLength(0);
                    progressBar.append(percent).append("[").append(ANSI_GREEN);
                    for(int j = 0; j < progress; j++){
                        progressBar.append("=");
                    }
                    for(int j = 0; j < 100-(progress); j++){
                        progressBar.append(" ");
                    }
                    progressBar.append(ANSI_RESET).append("]\r");
                    System.out.print(progressBar.toString());
                }
            }
            System.out.print(progressBar.toString());
            System.out.println("Successfully loaded all games into chessql");
            br.close();
        } catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }
}
