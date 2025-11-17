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
    public String getNotation(){ return toString(); }

    public boolean isCheck(){ return notation.charAt(notation.length()-1) == '+' || isMate(); }
    public boolean isMate(){ return notation.charAt(notation.length()-1) == '#'; }

    public boolean isShortCastle(){ return notation.startsWith("O-O") && !isLongCastle(); }
    public boolean isLongCastle(){ return notation.startsWith("O-O-O"); }

    public boolean isCapture(){ return notation.contains("x"); }

    public boolean isPromotion(){ return notation.contains("="); }

    public String getPiece(){
        switch (notation.charAt(0)){
            case 'N': return "Knight";
            case 'B': return "Bishop";
            case 'R': return "Rook";
            case 'Q': return "Queen";
            case 'O':
            case 'K': return "King";
            case 'a': 
            case 'b':
            case 'c':
            case 'd':
            case 'e':
            case 'f':
            case 'g':
            case 'h': return "Pawn";
            default: return "ERROR";
        }
    }

    public String getSquare(){
        int adjust = 0;
        if(isShortCastle() || isLongCastle()){
            char number = color.equals("white") ? '1' : '8';
            char letter = isShortCastle() ? 'g' : 'c';
            return "" + letter + number;
        }
        if(notation.charAt(notation.length()-1) == '+' || notation.charAt(notation.length()-1) == '#'){
            adjust++;
        }
        if(notation.charAt(notation.length()-(1+adjust)) == 'Q' || notation.charAt(notation.length()-(1+adjust)) == 'N'
        || notation.charAt(notation.length()-(1+adjust)) == 'B' || notation.charAt(notation.length()-(1+adjust)) == 'R'){
            adjust += 2;
        }
        return notation.substring(notation.length()-(adjust+2), notation.length()-adjust);
    }

    public String toString(){ return notation; }

}
