package Chess;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece{
    private boolean castle=false;
    King(String colour,char name) {
        super(colour, name);
    }

    @Override
    public List<List<Integer>> getPossibleMoves(ChessField[][] board, int rf, int cf) {
        List<List<Integer>> possibleMoves = new ArrayList<>();
        String color = board[rf][cf].getPiece().getColor();
        int[] X = {1, 1, 1, 0, -1, -1, -1, 0};
        int[] Y = {-1, 0, 1, 1, 1, 0, -1, -1};
        for (int i = 0; i < 8; i++) {
            int x = rf + X[i];
            int y = cf + Y[i];
            if (x >= 0 && x <= 7 && y >= 0 && y <= 7 && (board[x][y].getPiece() == null || !board[x][y].getPiece().getColor().equals(color))) {
                List<Integer> move = new ArrayList<>();
                move.add(y);
                move.add(x);
                possibleMoves.add(move);
            }
        }
        return possibleMoves;
    }

    public boolean getCastle(){
        return castle;
    }
    public void setCastle(boolean castle){
        this.castle=castle;
    }
}