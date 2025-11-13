package com.example;

import java.util.List;
import java.sql.Date;

public class Game {
    private List<Move> moves;
    private String white, black, outcome, event;
    private Date date;

    public Game(String pgn){
        
    }
}

/* PGN example

[Event "Leipzig Casual Games"]
[Site "Leipzig"]
[Date "1864.??.??"]
[Round "?"]
[White "Saalbach, August"]
[Black "Paulsen, Louis"]
[Result "0-1"]
[ECO "C45"]
[PlyCount "38"]
[EventDate "1864.??.??"]
[EventType "game"]
[EventRounds "1"]
[EventCountry "GER"]
[SourceTitle "EXT 2012"]
[Source "ChessBase"]
[SourceDate "2011.11.24"]
[SourceVersion "1"]
[SourceVersionDate "2011.11.24"]
[SourceQuality "1"]

1. e4 e5 2. Nf3 Nc6 3. d4 exd4 4. Nxd4 Bc5 5. Be3 Qf6 6. c3 Nge7 7. Be2 d5 8.
exd5 Nxd5 9. O-O Nxe3 10. fxe3 Qh6 11. Bb5 Qxe3+ 12. Kh1 O-O 13. Nxc6 bxc6 14.
Bxc6 Rb8 15. b3 Rb6 16. Bf3 Rh6 17. Qd2 Qe5 18. g3 Ba6 19. Qe1 Qf6 {Suhle &
Neumann: Die neueste Theorie und Praxis des Schachspiels, 1865, p. 416.} 0-1

POTENTIAL WAYS ON HOW TO TRAVERSE PGN STRING:

Split string on '[' to parse through game information, then split on ' ' (spaces) to parse through moves
    not sure what to do about random '{' and '}' instances. Could check for them and skip them

Don't split, and instead use pgn.positionOf(info) to grab information and a for loop with pgn.positionOf(move#) to grab each move
    seems like it would take a lot of time to actually run. Surely there's a more efficient way

Use a chess API to do it all for me
    Feels like cheating?

Gonna need to think about how to grab outcome as well, especially if outcome is draw
*/