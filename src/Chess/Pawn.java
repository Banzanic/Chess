package Chess;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    Pawn(String colour, char name) {
        super(colour, name);
    }

    @Override
    public List<List<Integer>> getPossibleMoves(ChessField[][] board, int rf, int cf) {
        List<List<Integer>> possibleMoves = new ArrayList<>();
        String color = board[rf][cf].getPiece().getColor();
        int[] X = {0, 0, -1, 1};
        int[] Y = {-1, -2, -1, -1};
        if(color=="black"){
            for(int i=0;i<4;i++){
                X[i]=X[i]*-1;
                Y[i]=Y[i]*-1;
            }
        }
        for (int i = 0; i < 4; i++) {
            int x = rf + X[i];
            int y = cf + Y[i];
            if (y >= 0 && y <= 7 && x>=0 && x<=7 && board[x][y].getPiece()==null && (Y[i]==-1 || Y[i]==1) && X[i]==0) {
                List<Integer> move = new ArrayList<>();
                move.add(y);
                move.add(x);
                possibleMoves.add(move);
            } else if(y >= 0 && y <= 7 && x>=0 && x<=7  && board[x][y].getPiece()==null && (Y[i]==-2 || Y[i]==2) && (cf==1 || cf==6)) {
                List<Integer> move = new ArrayList<>();
                move.add(y);
                move.add(x);
                possibleMoves.add(move);
            }
            else if (x>=0 && x<=7 && y >= 0 && y <= 7 && x != rf && board[x][y].getPiece()!=null && !board[x][y].getPiece().getColor().equals(color) && board[x][y].getPiece().getColor() != null) {
                List<Integer> move = new ArrayList<>();
                move.add(y);
                move.add(x);
                possibleMoves.add(move);
            }
        }

        return possibleMoves;
    }
}
