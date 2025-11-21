package com.example;

import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    @Test
    public void testGameCreation_CreatesGame(){
        String pgn = "[Event \"Leipzig Casual Games\"]\n" + 
                        "[Site \"Leipzig\"]\n" + 
                        "[Date \"1864.??.??\"]\n" + 
                        "[Round \"?\"]\n" + 
                        "[White \"Saalbach, August\"]\n" + 
                        "[Black \"Paulsen, Louis\"]\n" + 
                        "[Result \"0-1\"]\n" + 
                        "[ECO \"C45\"]\n" + 
                        "[PlyCount \"38\"]\n" + 
                        "[EventDate \"1864.??.??\"]\n" + 
                        "[EventType \"game\"]\n" + 
                        "[EventRounds \"1\"]\n" + 
                        "[EventCountry \"GER\"]\n" + 
                        "[SourceTitle \"EXT 2012\"]\n" + 
                        "[Source \"ChessBase\"]\n" + 
                        "[SourceDate \"2011.11.24\"]\n" + 
                        "[SourceVersion \"1\"]\n" + 
                        "[SourceVersionDate \"2011.11.24\"]\n" + 
                        "[SourceQuality \"1\"]\n" + 
                        "\n" + 
                        "1. e4 e5 2. Nf3 Nc6 3. d4 exd4 4. Nxd4 Bc5 5. Be3 Qf6 6. c3 Nge7 7. Be2 d5 8.\n" + 
                        "exd5 Nxd5 9. O-O Nxe3 10. fxe3 Qh6 11. Bb5 Qxe3+ 12. Kh1 O-O 13. Nxc6 bxc6 14.\n" + 
                        "Bxc6 Rb8 15. b3 Rb6 16. Bf3 Rh6 17. Qd2 Qe5 18. g3 Ba6 19. Qe1 Qf6 {Suhle &\n" + 
                        "Neumann: Die neueste Theorie und Praxis des Schachspiels, 1865, p. 416.} 0-1";
        Game game = new Game(1, pgn);
        List<Move> moves = game.getMoves();
        assertEquals(1, game.getID());
        assertEquals("0-1", game.getResult());
        assertEquals("e4", moves.get(0).getNotation());
    }

    @Test
    public void testMoveCreation_CreatesMove(){
        Move move = new Move(1, "white", "Naxc6#");
        assertEquals(1, move.getMoveNumber());
        assertTrue(move.isMate());
        assertTrue(move.isCapture());
        assertFalse(move.isLongCastle());
    }

    @Test
    public void testPrintMovesWithNoMoves_PrintsNoMoves(){
        ChesSQL chesSQL= new ChesSQL(null);
        assertDoesNotThrow(() -> chesSQL.printMoves());
    }

    @Test
    public void testUndoMoveWithNoMoves_UndosNoMoves(){
        ChesSQL chesSQL= new ChesSQL(null);
        assertDoesNotThrow(() -> chesSQL.undoMove());
    }

    @Test
    public void testClearMovesWithNoMoves_ClearsNoMoves(){
        ChesSQL chesSQL= new ChesSQL(null);
        assertDoesNotThrow(() -> chesSQL.clearMoves());
    }
}
