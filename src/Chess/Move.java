package Chess;

public class Move {
    private Piece piece;
    private ChessField from;
    private ChessField to;
    Move(ChessField from, ChessField to){
        this.piece=from.getPiece();
        this.from=from;
        this.to=to;
    }
    public ChessField getFrom(){
        return from;
    }
    public ChessField getTo(){
        return to;
    }
    public void setPiece(Piece piece){
        this.piece=piece;
    }
    public void setTo(ChessField to){
        this.to=to;
    }
}
