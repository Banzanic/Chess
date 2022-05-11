package Chess;

import java.util.List;

public abstract class Piece {
    String colour;
    char name;
    Piece(String colour, char name){
        this.colour=colour;
        this.name=name;
    }
    public String getColor(){
        return colour;
    }
    public char getName(){
        return name;
    }
    public abstract List<List<Integer>> getPossibleMoves(ChessField[][] board, int rf, int cf);
}
