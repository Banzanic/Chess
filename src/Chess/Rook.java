package Chess;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    Rook(String colour, char name) {
        super(colour, name);
    }

    @Override
    public List<List<Integer>> getPossibleMoves(ChessField[][] board, int rf, int cf, Piece pieceLastMoved, int lastRow, int lastCol, boolean is2) {
        List<List<Integer>> possibleMoves = new ArrayList<>();
        String color = board[rf][cf].getPiece().getColor();
        int x1 = rf + 1;
        int x2 = rf - 1;
        int y1 = cf + 1;
        int y2 = cf - 1;
        if (color.equals("white")) {
            x1 = rf - 1;
            x2 = rf + 1;
            y1 = cf - 1;
            y2 = cf + 1;
        }
        while (x1 <= 7 && x1 >= 0) {
            if (board[x1][cf].getPiece() == null || !board[x1][cf].getPiece().getColor().equals(color)) {
                List<Integer> move = new ArrayList<>();
                move.add(cf);
                move.add(x1);
                possibleMoves.add(move);
                if (board[x1][cf].getPiece() != null) {
                    break;
                }
                if (color.equals("black")) {
                    x1++;
                } else {
                    x1--;
                }
            } else {
                break;
            }
        }
        while (x2 >= 0 && x2 <= 7) {
            if (board[x2][cf].getPiece() == null || !board[x2][cf].getPiece().getColor().equals(color)) {
                List<Integer> move = new ArrayList<>();
                move.add(cf);
                move.add(x2);
                possibleMoves.add(move);
                if (board[x2][cf].getPiece() != null) {
                    break;
                }
                if (color.equals("black")) {
                    x2--;
                } else {
                    x2++;
                }
            } else {
                break;
            }
        }
        while (y1 <= 7 && y1 >= 0) {
            if (board[rf][y1].getPiece() == null || !board[rf][y1].getPiece().getColor().equals(color)) {
                List<Integer> move = new ArrayList<>();
                move.add(y1);
                move.add(rf);
                possibleMoves.add(move);
                if (board[rf][y1].getPiece() != null) {
                    break;
                }
                if (color.equals("black")) {
                    y1++;
                } else {
                    y1--;
                }
            } else {
                break;
            }
        }
        while (y2 >= 0 && y2 <= 7) {
            if (board[rf][y2].getPiece() == null || !board[rf][y2].getPiece().getColor().equals(color)) {
                List<Integer> move = new ArrayList<>();
                move.add(y2);
                move.add(rf);
                possibleMoves.add(move);
                if (board[rf][y2].getPiece() != null) {
                    break;
                }
                if (color.equals("black")) {
                    y2--;
                } else {
                    y2++;
                }
            } else {
                break;
            }
        }
        return possibleMoves;
    }
}
