package com.example;

import java.sql.SQLException;

import com.example.repository.PostgreSQLRepository;
import com.example.service.Service;

public class Main {
    public static void main(String[] args) throws SQLException{
        PostgreSQLRepository repo = new PostgreSQLRepository();
        Service service = new Service(repo);
        /*
        String string = "[Event \"London International Masters\"]                                                                      ]\n" + //
                        "[Site \"London\"]\n" + //
                        "[Date \"1883.05.31\"]\n" + //
                        "[Round \"16\"]\n" + //
                        "[White \"Mason, James\"]\n" + //
                        "[Black \"Winawer, Szymon\"]\n" + //
                        "[Result \"0-1\"]\n" + //
                        "[SetUp \"1\"]\n" + //
                        "[FEN \"7k/3n4/4p2p/3pPp1P/1Np2P2/6K1/PP2Q3/7q b - - 0 43\"]\n" + //
                        "[PlyCount \"13\"]\n" + //
                        "[EventDate \"1883.04.26\"]\n" + //
                        "[EventType \"tourn\"]\n" + //
                        "[EventRounds \"26\"]\n" + //
                        "[SourceTitle \"EXT 2018\"]\n" + //
                        "[SourceDate \"2017.10.13\"]\n" + //
                        "[SourceVersion \"1\"]\n" + //
                        "[SourceVersionDate \"2017.10.13\"]\n" + //
                        "[SourceQuality \"1\"]\n" + //
                        "\n" + //
                        "{Correction of the adjourned position. See previous game.} 43... Nc5 {White is\n" + //
                        "lost} 44. Qg2 Ne4+ 45. Kf3 Qe1 (45... Qxh5+ 46. Ke3 Qd1) 46. Qh2 Nd2+ (46...\n" + //
                        "Qf1+ 47. Ke3 Qd1 48. Qe2 Qg1+) 47. Kg2 Qf1+ 48. Kg3 Qf3+ 49. Kh4 Qg4# 0-1";
        Game game = new Game(1, string);
        */
        System.out.println(repo.readGame(1));
        System.out.println(repo.readGame(2));
        System.out.println(repo.readGame(3));
    }
}