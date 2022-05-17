package Chess;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class App extends JPanel implements MouseListener, MouseMotionListener {
    private Board temp;
    private ChessField[][] board;
    private BufferedImage[] images = new BufferedImage[12];
    private int x;
    private int y;
    private int toX;
    private int toY;
    private boolean is2=false;
    private int movesCounter=0;
    private Piece pieceLastMoved=null;
    private int lastRow=-1;
    private int lastCol=-1;
    public void init() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        temp = new Board();
        board = temp.getBoard();
        load();
        this.repaint();
    }
    public void load() {
        try {
            File directory = new File("src/Images");
            if (!directory.exists()) {
                if (directory.mkdir()) {
                    throw new Exception();
                }
            }
            images[0] = ImageIO.read(new File("src/Images/WPawn.PNG"));
            images[1] = ImageIO.read(new File("src/Images/WRook.PNG"));
            images[2] = ImageIO.read(new File("src/Images/WKnight.PNG"));
            images[3] = ImageIO.read(new File("src/Images/WBishop.PNG"));
            images[4] = ImageIO.read(new File("src/Images/WQueen.PNG"));
            images[5] = ImageIO.read(new File("src/Images/WKing.PNG"));
            images[6] = ImageIO.read(new File("src/Images/BPawn.PNG"));
            images[7] = ImageIO.read(new File("src/Images/BRook.PNG"));
            images[8] = ImageIO.read(new File("src/Images/BKnight.PNG"));
            images[9] = ImageIO.read(new File("src/Images/BBishop.PNG"));
            images[10] = ImageIO.read(new File("src/Images/BQueen.PNG"));
            images[11] = ImageIO.read(new File("src/Images/BKing.PNG"));
        } catch (Exception e) {
        }
    }
    public void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        int w = width / 8;
        int h = height / 8;
        drawBoard(g, w, h);
        drawPieces(g, w, h);
    }
    public void drawBoard(Graphics g, int w, int h) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0)) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.GRAY);
                }
                g.fillRect(j * w, i * h, w, h);
            }
        }
    }
    public void drawPieces(Graphics g, int w, int h) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getPiece() != null) {
                    if (board[i][j].getPiece().getName() == 'P') {
                        g.drawImage(images[0], board[i][j].getRow() * w, board[i][j].getColumn() * h, w, h, null);
                    } else if (board[i][j].getPiece().getName() == 'R') {
                        g.drawImage(images[1], board[i][j].getRow() * w, board[i][j].getColumn() * h, w, h, null);
                    } else if (board[i][j].getPiece().getName() == 'S') {
                        g.drawImage(images[2], board[i][j].getRow() * w, board[i][j].getColumn() * h, w, h, null);
                    } else if (board[i][j].getPiece().getName() == 'B') {
                        g.drawImage(images[3], board[i][j].getRow() * w, board[i][j].getColumn() * h, w, h, null);
                    } else if (board[i][j].getPiece().getName() == 'Q') {
                        g.drawImage(images[4], board[i][j].getRow() * w, board[i][j].getColumn() * h, w, h, null);
                    } else if (board[i][j].getPiece().getName() == 'K') {
                        g.drawImage(images[5], board[i][j].getRow() * w, board[i][j].getColumn() * h, w, h, null);
                    } else if (board[i][j].getPiece().getName() == 'p') {
                        g.drawImage(images[6], board[i][j].getRow() * w, board[i][j].getColumn() * h, w, h, null);
                    } else if (board[i][j].getPiece().getName() == 'r') {
                        g.drawImage(images[7], board[i][j].getRow() * w, board[i][j].getColumn() * h, w, h, null);
                    } else if (board[i][j].getPiece().getName() == 's') {
                        g.drawImage(images[8], board[i][j].getRow() * w, board[i][j].getColumn() * h, w, h, null);
                    } else if (board[i][j].getPiece().getName() == 'b') {
                        g.drawImage(images[9], board[i][j].getRow() * w, board[i][j].getColumn() * h, w, h, null);
                    } else if (board[i][j].getPiece().getName() == 'q') {
                        g.drawImage(images[10], board[i][j].getRow() * w, board[i][j].getColumn() * h, w, h, null);
                    } else if (board[i][j].getPiece().getName() == 'k') {
                        g.drawImage(images[11], board[i][j].getRow() * w, board[i][j].getColumn() * h, w, h, null);
                    }
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x=e.getX();
        y=e.getY();
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        toX=e.getX();
        toY=e.getY();
        int rt=toX/64;
        int ct=toY/64;
        int rf=x/64;
        int cf=y/64;
        if(board[rf][cf].getPiece()!=null) {
            if (board[rf][cf].getPiece() != null && ((board[rf][cf].getPiece().getColor() == "white" && movesCounter % 2 == 0) || (board[rf][cf].getPiece().getColor() == "black" && movesCounter % 2 != 0))) {
                if (canMove(rf, cf, rt, ct, pieceLastMoved, lastRow, lastCol, is2)) {
                    if(board[rt][ct].getPiece()==null && board[rf][cf].getPiece().getName()=='p' && rf!=rt){
                        board[rt][ct-1]=new ChessField(null,rt,ct-1);
                    }
                    else if(board[rt][ct].getPiece()==null && board[rf][cf].getPiece().getName()=='P' && rf!=rt){
                        board[rt][ct+1]=new ChessField(null,rt,ct+1);
                    }
                    else if((board[rf][cf].getPiece().getName()=='k' || board[rf][cf].getPiece().getName()=='K') && rf-rt==2){
                        System.out.println(rf+" "+rt);
                        board[rf-1][cf] = new ChessField(board[0][cf].getPiece(), rf-1, cf);
                        board[0][cf] = new ChessField(null, 0, cf);
                    }
                    else if((board[rf][cf].getPiece().getName()=='k' || board[rf][cf].getPiece().getName()=='K') && rf-rt==-2){
                        System.out.println(rf+" "+cf);
                        board[rf+1][cf] = new ChessField(board[7][cf].getPiece(), rf+1, cf);
                        board[7][cf] = new ChessField(null, 7, cf);
                    }
                    board[rt][ct] = new ChessField(board[rf][cf].getPiece(), rt, ct);
                    board[rf][cf] = new ChessField(null, rf, cf);
                    if((board[rt][ct].getPiece().getName()=='p' || board[rt][ct].getPiece().getName()=='P') && ct==0 || ct==7){
                        if(board[rt][ct].getPiece().getName()=='p'){
                            board[rt][ct] = new ChessField(new Queen("black",'q'),rt,ct);
                        }
                        else{
                            board[rt][ct] = new ChessField(new Queen("white",'Q'),rt,ct);
                        }
                    }
                    movesCounter++;
                    pieceLastMoved=board[rt][ct].getPiece();
                    lastRow=rt;
                    lastCol=ct;
                    if(Math.abs(ct-cf)==2){
                        is2=true;
                    }
                    else{
                        is2=false;
                    }
                    board[rt][ct].getPiece().setMoveCounter();
                    repaint();
                    if(isGameOver((board[rt][ct].getPiece().getColor()))){
                        if(!isChecked(board,"white") && !isChecked(board,"black")){
                            JOptionPane.showMessageDialog(this, "Draw!", "", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            JOptionPane.showMessageDialog(this, "Victory!", "", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }

            }
        }
        repaint();
    }
    public boolean isGameOver(String color){
        if(color.equals("white")){
            color="black";
        }
        else{
            color="white";
        }
        boolean check=false;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(board[i][j].getPiece()!=null && board[i][j].getPiece().getColor()==color) {
                    List<List<Integer>> possibleMoves=board[i][j].getPiece().getPossibleMoves(board,i,j,pieceLastMoved,lastRow,lastCol,is2);
                    for(List k:possibleMoves){
                        if(canMove(i,j,(int)k.get(1),(int)k.get(0),pieceLastMoved,lastRow,lastCol,is2)){
                            check=true;
                        }
                    }
                }
            }
        }
        if(check){
            return false;
        }
        return true;
    }
    public List<List<List<Integer>>> possibleMovesForEnemy(ChessField[][] board, String color) {
        List<List<List<Integer>>> possibleMoves = new ArrayList<>();
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                if(board[i][j].getPiece()!=null && board[i][j].getPiece().getColor().equals(color)) {
                    possibleMoves.add(board[i][j].getPiece().getPossibleMoves(board,i,j,pieceLastMoved,lastRow,lastCol,is2));
                }
            }
        }
        return possibleMoves;
    }
    public boolean isChecked(ChessField[][] board, String color){
        if(color=="white"){
            List<List<List<Integer>>> possibleMoves;
            possibleMoves=possibleMovesForEnemy(board, "black");
            for(int i=0;i<8;i++){
                for(int j=0;j<8;j++){
                    if(board[i][j].getPiece()!=null && board[i][j].getPiece().getName()=='K'){
                        List<Integer> move=new ArrayList<>();
                        move.add(j);
                        move.add(i);
                        for(List k:possibleMoves){
                            for(int l=0;l<k.size();l++){
                                if(k.get(l).equals(move)){
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        else{
            List<List<List<Integer>>> possibleMoves;
            possibleMoves=possibleMovesForEnemy(board, "white");
            for(int i=0;i<8;i++){
                for(int j=0;j<8;j++){
                    if(board[i][j].getPiece()!=null && board[i][j].getPiece().getName()=='k'){
                        List<Integer> move=new ArrayList<>();
                        move.add(j);
                        move.add(i);
                        for(List k:possibleMoves){
                            for(int l=0;l<k.size();l++){
                                if(k.get(l).equals(move)){
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    public boolean canMove(int rf, int cf, int rt, int ct, Piece pieceLastMoved, int lastRow, int lastCol, boolean is2){
        List<List<Integer>> possibleMoves=board[rf][cf].getPiece().getPossibleMoves(board,rf,cf,pieceLastMoved,lastRow,lastCol,is2);
        List<Integer> move=new ArrayList<>();
        move.add(ct);
        move.add(rt);
        ChessField[][] copy=new ChessField[8][8];
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                copy[i][j]=board[i][j];
            }
        }
        copy[rt][ct] = new ChessField(copy[rf][cf].getPiece(), rt, ct);
        copy[rf][cf] = new ChessField(null, rf, cf);
        for(List i:possibleMoves){
            if(i.equals(move) && !isChecked(copy, copy[rt][ct].getPiece().getColor())){
                return true;
            }
        }
        return false;
    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}