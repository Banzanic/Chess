package Chess;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    Queen(String colour, char name) {
        super(colour, name);
    }

    @Override
    public List<List<Integer>> getPossibleMoves(ChessField[][] board, int rf, int cf, Piece pieceLastMoved, int lastRow, int lastCol, boolean is2) {
        List<List<Integer>> possibleMoves = new ArrayList<>();
        String color = board[rf][cf].getPiece().getColor();
        int x1 = rf + 1, y1 = cf + 1;
        int x2 = rf + 1, y2 = cf - 1;
        int x3 = rf - 1, y3 = cf + 1;
        int x4 = rf - 1, y4 = cf - 1;
        if (color.equals("white")) {
            x1 = rf - 1;
            x2 = rf - 1;
            x3 = rf + 1;
            x4 = rf + 1;
            y1 = cf - 1;
            y2 = cf + 1;
            y3 = cf - 1;
            y4 = cf + 1;
        }
        while (x1 >= 0 && x1 <= 7 && y1 <= 7 && y1 >= 0) {
            if (board[x1][y1].getPiece() == null || !board[x1][y1].getPiece().getColor().equals(color)) {
                List<Integer> move = new ArrayList<>();
                move.add(y1);
                move.add(x1);
                possibleMoves.add(move);
                if (board[x1][y1].getPiece() != null) {
                    break;
                }
                if (color.equals("black")) {
                    x1++;
                    y1++;
                } else {
                    x1--;
                    y1--;
                }
            } else {
                break;
            }
        }
        while (x2 >= 0 && x2 <= 7 && y2 <= 7 && y2 >= 0) {
            if (board[x2][y2].getPiece() == null || !board[x2][y2].getPiece().getColor().equals(color)) {
                List<Integer> move = new ArrayList<>();
                move.add(y2);
                move.add(x2);
                possibleMoves.add(move);
                if (board[x2][y2].getPiece() != null) {
                    break;
                }
                if (color.equals("black")) {
                    x2++;
                    y2--;
                } else {
                    x2--;
                    y2++;
                }
            } else {
                break;
            }
        }
        while (x3 >= 0 && x3 <= 7 && y3 <= 7 && y3 >= 0) {
            if (board[x3][y3].getPiece() == null || !board[x3][y3].getPiece().getColor().equals(color)) {
                List<Integer> move = new ArrayList<>();
                move.add(y3);
                move.add(x3);
                possibleMoves.add(move);
                if (board[x3][y3].getPiece() != null) {
                    break;
                }
                if (color.equals("black")) {
                    x3--;
                    y3++;
                } else {
                    x3++;
                    y3--;
                }
            } else {
                break;
            }
        }
        while (x4 >= 0 && x4 <= 7 && y4 <= 7 && y4 >= 0) {
            if (board[x4][y4].getPiece() == null || !board[x4][y4].getPiece().getColor().equals(color)) {
                List<Integer> move = new ArrayList<>();
                move.add(y4);
                move.add(x4);
                possibleMoves.add(move);
                if (board[x4][y4].getPiece() != null) {
                    break;
                }
                if (color.equals("black")) {
                    x4--;
                    y4--;
                } else {
                    x4++;
                    y4++;
                }
            } else {
                break;
            }
        }
        x1 = rf + 1;
        x2 = rf - 1;
        y1 = cf + 1;
        y2 = cf - 1;
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
