package Chess;

public class ChessField {
    private Piece piece;
    private final int row;
    private final int column;
    ChessField(Piece piece, int row, int column){
        this.piece=piece;
        this.row=row;
        this.column=column;
    }
    public Piece getPiece(){
        return piece;
    }
    public int getRow(){
        return row;
    }
    public int getColumn(){
        return column;
    }
    public void setPiece(Piece piece){
        this.piece=piece;
    }
}
