package com.example;

import java.util.List;
import java.util.ArrayList;
import java.sql.Date;

public class Game {
    private int game_id;
    private List<Move> moves;
    private String white, black, result, event;
    private Date date;

    public Game(int game_id, List<Move> moves, String white, String black, String result, String event, Date date){
        this.game_id = game_id;
        this.moves = moves;
        this.white = white;
        this.black = black;
        this.result = result;
        this.event = event;
        this.date = date;
    }

    public Game(int game_id, String pgn){
        this.game_id = game_id;
        String[] separate = pgn.split("\n\n");
        String[] info = separate[0].split("\\]\n");
        String moveList = separate[1];
        //Parse through beginning section PGN file and grab general information
        for(int i = 0; i < info.length; i++){
            String key = info[i].substring(1, info[i].indexOf('\"')-1);
            String value = info[i].substring(info[i].indexOf('\"')+1, info[i].lastIndexOf('\"'));
            switch(key){
                case "Event":
                    this.event = value;
                    break;
                case "Date":
                    if(value.indexOf('.') == 2){
                        value = "00" + value;
                    }
                    value = value.replace("??", "01");
                    value = value.replace('.', '-');
                    this.date = Date.valueOf(value);
                    break;
                case "White":
                    this.white = value;
                    break;
                case "Black":
                    this.black = value;
                    break;
                case "Result":
                    this.result = value;
                    break;
                case "PlyCount":
                    if(value.equals("0")){
                        this.game_id = 0;
                        return;
                    }
            }
        }

        //Edit the rest of the PGN file, leaving just moves in algabreic chess notation
        int braceCount = 0;
        int parenCount = 0;
        StringBuilder editedNotation = new StringBuilder();
        for(int i = 0; i < moveList.length(); i++){
            char c = moveList.charAt(i);
            if(c == '{') {braceCount++; continue;}
            if(c == '}') {braceCount--; continue;}
            if(c == '(') {parenCount++; continue;}
            if(c == ')') {parenCount++; continue;}

            if(braceCount == 0 && parenCount == 0) editedNotation.append(c);
        }
        moveList = editedNotation.toString();
        if(moveList.indexOf("1.") == -1){ this.game_id = 0; return; }
        moveList = moveList.substring(moveList.indexOf("1."));
        moveList = moveList.replaceAll("[\\d]*\\.|\\$[\\d]+|\n", " ");
        
        String[] notations = moveList.split("[ ]+");
        moves = new ArrayList<>();

        //Store array of Move objects
        String color = "white";
        for(int i = 1; i < notations.length-1; i++){
            moves.add(new Move(i, color, notations[i]));
            if(color.equals("white")) color = "black";
            else { color = "white"; }
        }
    }

    public int getID(){ return game_id; }
    public List<Move> getMoves(){ return moves; }
    public String getWhite(){ return white; }
    public String getBlack(){ return black; }
    public String getEvent(){ return event; }
    public String getResult(){ return result; }
    public Date getDate(){ return date; }

    public String toString(){
        String ret =
            "[Event \"" + event + "\"]\n" +
            "[Date \"" + date + "\"]\n" +
            "[White \"" + white + "\"]\n" +
            "[Black \"" + black + "\"]\n" +
            "[Result \"" + result + "\"]\n\n";
        for(int i = 0; i < ((moves.size()+1)/2); i++){
            ret += (i+1) + ". " + moves.get(i*2);
            if((i*2)+1 < moves.size()){
                ret +=  " " + moves.get((i*2)+1);
            }
            ret += " ";
        }
        ret += result;
        return ret;

    }
}

/* PGN examples

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

[Event "Polerio"]
[Site "Rome"]
[Date "1610.??.??"]
[Round "?"]
[White "Polerio, Giulio Cesare"]
[Black "D'Arminio, Domenico"]
[Result "1-0"]
[ECO "C57"]
[Annotator "Kahn,V"]
[PlyCount "47"]
[EventDate "1610.??.??"]
[EventType "game"]
[EventCountry "ITA"]
[SourceTitle "EXT 1999"]
[Source "ChessBase"]
[SourceDate "1998.11.16"]
[SourceVersion "1"]
[SourceVersionDate "1998.11.16"]
[SourceQuality "1"]

1. e4 {La plus ancienne partie connue après la réforme du jeu des échecs date
de 1485. jouée entre Francisco De CALTEVI et Narcisco VIGNOLES, cette partie
qu'on trouve dans "the golden Treasury of chess", New-York de F.J WELLMUTH est
assez faible. Rien d'étonnant, les échecs modernes venaient seulement de
naître. Mais le même ouvrage contient une partie que le génial POLERIO joua en
1580. Nous y voyons au 06ème coup, ce sacrifice qu'EUWE, entre autres
commentateurs attribua à GRECO.  Le "Fegatello" aurait donc été pratiqué 20
ans avant sa naissance présumée.} e5 2. Nf3 Nc6 3. Bc4 Nf6 4. Ng5 d5 5. exd5
Nxd5 {Nous ne mettons pas de point d'interrogation usuel à ce coup car les
analyses n'en donnent pas de réfutation pratique. Si on ne voit guère ce coup
en pratique, c'est pour des raisons psychologiques. Les noirs craignent le
désagréable exode du N} 6. Nxf7 $1 {Ce point d'exclamation ne signifie pas non
plus que le sacrifice est la continuation la plus forte des blancs mais nous
tenons à saluer le génie de POLERIO, inventeur du Fegatello.} (6. d4 {Les
analyses récentes préfèrent 6.d4}) 6... Kxf7 7. Qf3+ Ke6 8. Nc3 Nce7 (8... Ncb4
{Déjà STEINITZ avait renforcé avec Nb4} 9. Qe4 (9. a3 Nxc2+ 10. Kd1 Nd4 11.
Bxd5+ Kd6 $11) 9... c6 10. a3 Na6 11. d4 Nac7 $1 12. Bf4 (12. Qxe5+ Kf7) 12...
Kf7 13. Bxe5 {Et comme dit EUWE, il faut encore prouver que l'attaque blanche
vaut la pièce sacrifiée}) 9. d4 $1 c6 10. Bg5 h6 11. Bxe7 Bxe7 12. O-O-O Rf8
13. Qe4 Rxf2 14. dxe5 Bg5+ 15. Kb1 Rd2 16. h4 $1 Rxd1+ 17. Rxd1 Bxh4 18. Nxd5
cxd5 19. Rxd5 Qg5 20. Rd6+ Ke7 21. Rg6 $1 Qd2 22. Rxg7+ Kf8 23. Rg8+ Ke7 24.
Qh7# 1-0

POTENTIAL WAYS ON HOW TO TRAVERSE PGN STRING:

Split string on '[' to parse through game information, then split on ' ' (spaces) to parse through moves
    not sure what to do about random '{' and '}' instances. Could check for them and skip them

Don't split, and instead use pgn.positionOf(info) to grab information and a for loop with pgn.positionOf(move#) to grab each move
    seems like it would take a lot of time to actually run. Surely there's a more efficient way

Use a chess API to do it all for me
    Feels like cheating?

Gonna need to think about how to grab outcome as well, especially if outcome is draw
*/