package com.example;

public class Move {
    private int moveNumber;
    private String color;
    private String notation;

    public Move(int mn, String c, String n){
        moveNumber = mn;
        color = c;
        notation = n;
    }

    public int getMoveNumber(){ return moveNumber; }
    public String getColor(){ return color; }
    public String getNotation(){ return notation; }

    public String getPiece(){
        switch (notation.charAt(0)){
            case 'N': return "Knight";
            case 'B': return "Bishop";
            case 'R': return "Rook";
            case 'Q': return "Queen";
            case 'K': return "King";
            case 'a': 
            case 'b':
            case 'c':
            case 'd':
            case 'e':
            case 'f':
            case 'g':
            case 'h':return "Pawn";
            default: return "ERROR";
        }
    }

}
