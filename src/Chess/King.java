package Chess;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    King(String colour, char name) {
        super(colour, name);
    }

    @Override
    public List<List<Integer>> getPossibleMoves(ChessField[][] board, int rf, int cf, Piece pieceLastMoved, int lastRow, int lastCol, boolean is2) {
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
        if (this.getMoveCounter() == 0) {
            boolean checkField = true;
            if (color.equals("white")) {
                if (board[0][7].getPiece() != null && board[0][7].getPiece().getName() == 'R' && board[0][7].getPiece().getMoveCounter() == 0) {
                    for (int i = 1; i < board[rf][7].getRow(); i++) {
                        if (board[i][0].getPiece() != null) {
                            checkField = false;
                            break;
                        }
                    }
                    if (checkField) {
                        List<Integer> move = new ArrayList<>();
                        move.add(7);
                        move.add(2);
                        possibleMoves.add(move);
                    }
                }
                checkField = true;
                if (board[7][7].getPiece() != null && board[7][7].getPiece().getName() == 'R' && board[7][7].getPiece().getMoveCounter() == 0) {
                    for (int i = 6; i > board[rf][7].getColumn(); i--) {
                        if (board[i][0].getPiece() != null) {
                            checkField = false;
                            break;
                        }
                    }
                    if (checkField) {
                        List<Integer> move = new ArrayList<>();
                        move.add(7);
                        move.add(6);
                        possibleMoves.add(move);
                    }
                }
            } else {
                if (board[0][0].getPiece() != null && board[0][0].getPiece().getName() == 'r' && board[0][0].getPiece().getMoveCounter() == 0) {
                    for (int i = 1; i < board[rf][0].getRow(); i++) {
                        if (board[i][0].getPiece() != null) {
                            checkField = false;
                            break;
                        }
                    }
                    if (checkField) {
                        List<Integer> move = new ArrayList<>();
                        move.add(0);
                        move.add(2);
                        possibleMoves.add(move);
                    }
                }
                checkField = true;
                if (board[7][0].getPiece() != null && board[7][0].getPiece().getName() == 'r' && board[7][0].getPiece().getMoveCounter() == 0) {
                    for (int i = 6; i > board[rf][0].getRow(); i--) {
                        if (board[i][0].getPiece() != null) {
                            checkField = false;
                            break;
                        }
                    }
                    if (checkField) {
                        List<Integer> move = new ArrayList<>();
                        move.add(0);
                        move.add(6);
                        possibleMoves.add(move);
                    }
                }
            }
        }
        return possibleMoves;
    }
}