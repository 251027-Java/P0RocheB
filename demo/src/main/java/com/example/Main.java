package com.example;

import java.sql.SQLException;

import com.example.repository.PostgreSQLRepository;
import com.example.service.Service;

public class Main {
    public static void main(String[] args) throws SQLException{
        PostgreSQLRepository repo = new PostgreSQLRepository();
        Service service = new Service(repo);
        /*
        String string = "[Event \"Berlin Casual Games\"]\n" + //
                        "[Site \"Berlin\"]\n" + //
                        "[Date \"1839.05.25\"]\n" + //
                        "[Round \"?\"]\n" + //
                        "[White \"Bledow, Ludwig Erdmann\"]\n" + //
                        "[Black \"Von Heydebrand und der Lasa, Tassilo\"]\n" + //
                        "[Result \"1-0\"]\n" + //
                        "[ECO \"C53\"]\n" + //
                        "[Annotator \"Tartakower/du Mont\"]\n" + //
                        "[PlyCount \"53\"]\n" + //
                        "[EventDate \"1839.??.??\"]\n" + //
                        "[EventType \"game\"]\n" + //
                        "[EventCountry \"GER\"]\n" + //
                        "[SourceTitle \"EXT 1999\"]\n" + //
                        "[Source \"ChessBase\"]\n" + //
                        "[SourceDate \"1998.11.16\"]\n" + //
                        "[SourceVersion \"1\"]\n" + //
                        "[SourceVersionDate \"1998.11.16\"]\n" + //
                        "[SourceQuality \"1\"]\n" + //
                        "\n" + //
                        "{Tartakower, S., & Du Mont, J. (1952). 500 Master Games of Chess. London: G.\n" + //
                        "Bell & Sons, p. 1. All comments are Tartakower's unless otherwise noted.\n" + //
                        "Morphy's great principles-the formation of a pawn centre, the most rapid\n" + //
                        "development of the pieces (even at the cost of material), the opening up of\n" + //
                        "lines of attack, the deadly effect of an advanced pawn-all are already In\n" + //
                        "evidence in the following old-time game.} 1. e4 e5 2. Nf3 Nc6 3. Bc4 Bc5 4. c3\n" + //
                        "{With the idea - known and appreciated ever since chess was played - of\n" + //
                        "establishing a pawn centre by e4.} Qe7 {One of the earliest defences.} 5. d4\n" + //
                        "Bb6 {A complement of his last move. Black evidently wishes to maintain a hold\n" + //
                        "on e5.} ({If he abandons the centre by} 5... exd4 {White plays} 6. O-O {\n" + //
                        "and his pressure, at the expense of a pawn (} dxc3 7. Nxc3 {) would become\n" + //
                        "irksome.}) 6. O-O d6 7. a4 {White's flank attack at a4 already threatens to\n" + //
                        "win a piece by 8.a5 Bxa5 9.Rxa5? (9.d5+-) 9...Nxa5 10.Qa4+ Nc6 11.d5 a6 12.\n" + //
                        "dxc6 b5 13.Bxb5!} a5 8. Be3 Nf6 $146 ({Here the gain of a pawn by} 8... exd4 9.\n" + //
                        "cxd4 Qxe4 {would be refuted by} 10. Re1 {with a winning frontal attack.}) 9.\n" + //
                        "dxe5 ({It is also feasible to maintain the tension in the centre by} 9. Nbd2)\n" + //
                        "9... Nxe5 10. Nxe5 dxe5 11. Bxb6 cxb6 12. Nd2 O-O 13. Qe2 Bd7 ({Instead of\n" + //
                        "seeking simplification by} 13... Be6 {Black pursues more ambitious plans.}) 14.\n" + //
                        "Rad1 {Disregarding the loss of a pawn, White speeds up his development.} Bxa4\n" + //
                        "15. b3 Bc6 16. f4 {The opening of the f-file is important.} Rad8 17. fxe5 Qxe5\n" + //
                        "18. Rf5 Qd6 ({Or} 18... Qe7 19. e5 b5 20. Ne4 {and White's pressure is more\n" + //
                        "effective still.}) 19. e5 Qc5+ 20. Kh1 Ne4 ({Black's hope of obtaining relief\n" + //
                        "by exchanges is doomed to disappointment. But if} 20... Nd5 21. e6) ({and if}\n" + //
                        "20... Nd7 21. Rdf1) ({or} 20... Rde8 21. Rdf1 {and in any case White has the\n" + //
                        "advantage.}) 21. Nxe4 $18 Rxd1+ 22. Qxd1 Bxe4 {Hoping to drive away the\n" + //
                        "bellicose rook. More interesting is the ensuing drama which unfolds round the\n" + //
                        "sensitive point f7.} 23. Rxf7 (23. Rg5 h6 24. Rh5 Bg6 {/\\Qxe5}) ({or} 23. Rh5\n" + //
                        "Bg6 24. Rg5 Qe3) 23... Rxf7 24. Qd8+ Qf8 25. Bxf7+ Kxf7 26. e6+ {[%cal Be5e6,\n" + //
                        "Be6e7]} Kg8 27. e7 {Black Resigns. The mobility of this pawn has played the\n" + //
                        "decisive rôle in the attack.} 1-0";
        Game game = new Game(1, string);
        System.out.println(game);
        */
        System.out.println(repo.readGame(1));
        System.out.println(repo.readGame(2));
        System.out.println(repo.readGame(3));
    }
}