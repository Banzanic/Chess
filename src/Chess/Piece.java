package Chess;

import java.util.List;

public abstract class Piece {
    String colour;
    char name;
    private int moveCounter;
    Piece(String colour, char name){
        this.colour=colour;
        this.name=name;
        this.moveCounter=0;
    }
    public String getColor(){
        return colour;
    }
    public int getMoveCounter() { return moveCounter; }
    public void setMoveCounter() { moveCounter++; }
    public char getName(){
        return name;
    }
    public abstract List<List<Integer>> getPossibleMoves(ChessField[][] board, int rf, int cf, Piece pieceLastMoved, int lastRow, int lastCol, boolean is2);
}
