package Chess;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    Knight(String colour, char name) {
        super(colour, name);
    }

    @Override
    public List<List<Integer>> getPossibleMoves(ChessField[][] board, int rf, int cf, Piece pieceLastMoved, int lastRow, int lastCol, boolean is2) {
        List<List<Integer>> possibleMoves = new ArrayList<>();
        String color = board[rf][cf].getPiece().getColor();
        int[] X = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] Y = {1, 2, 2, 1, -1, -2, -2, -1};
        for (int i = 0; i < 8; i++) {
            if (color == "white") {
                X[i] = X[i] * -1;
                Y[i] = Y[i] * -1;
            }
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
}
