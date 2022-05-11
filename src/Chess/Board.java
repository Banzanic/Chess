package Chess;
public class Board {
    ChessField[][] board=new ChessField[8][8];
    Board(){
        this.createBoard();
    }
    private void createBoard(){
        board[0][0] = new ChessField(new Rook("black",'r'),0,0);
        board[1][0] = new ChessField(new Knight("black",'s'),1,0);
        board[2][0] = new ChessField(new Bishop("black",'b'),2,0);
        board[3][0] = new ChessField(new Queen("black",'q'),3,0);
        board[4][0] = new ChessField(new King("black",'k'),4,0);
        board[5][0] = new ChessField(new Bishop("black",'b'),5,0);
        board[6][0] = new ChessField(new Knight("black",'s'),6,0);
        board[7][0] = new ChessField(new Rook("black",'r'),7,0);

        board[0][7] = new ChessField(new Rook("white",'R'),0,7);
        board[1][7] = new ChessField(new Knight("white",'S'),1,7);
        board[2][7] = new ChessField(new Bishop("white",'B'),2,7);
        board[3][7] = new ChessField(new Queen("white",'Q'),3,7);
        board[4][7] = new ChessField(new King("white",'K'),4,7);
        board[5][7] = new ChessField(new Bishop("white",'B'),5,7);
        board[6][7] = new ChessField(new Knight("white",'S'),6,7);
        board[7][7] = new ChessField(new Rook("white",'R'),7,7);

        for(int i=0;i<8;i++){
            board[i][1] = new ChessField(new Pawn("black",'p'),i,1);
            board[i][6] = new ChessField(new Pawn("white",'P'),i,6);
        }

        for(int i=0;i<8;i++){
            for(int j=2;j<6;j++){
                board[i][j] = new ChessField(null,i,j);
            }
        }
    }
    public ChessField[][] getBoard(){
        return board;
    }
    public Piece getPiece(int row, int column){
        return board[row][column].getPiece();
    }
    public void setPiece(Piece piece, int row, int column){
        board[row][column].setPiece(piece);
    }
}
