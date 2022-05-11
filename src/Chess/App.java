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
    int movesCounter=0;
    public boolean isChecked=false;
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
                if (canMove(rf, cf, rt, ct)) {
                    board[rt][ct] = new ChessField(board[rf][cf].getPiece(), rt, ct);
                    board[rf][cf] = new ChessField(null, rf, cf);
                    movesCounter++;
                    if(isChecked(board, board[rt][ct].getPiece().getColor())) {
                        board[rf][cf] = new ChessField(board[rt][ct].getPiece(), rf, cf);
                        board[rt][ct] = new ChessField(null, rt, ct);
                        movesCounter--;
                    }
                }
            }
        }
        repaint();
    }
    public List<List<List<Integer>>> possibleMovesForEnemy(ChessField[][] board, String color) {
        List<List<List<Integer>>> possibleMoves = new ArrayList<>();
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                if(board[i][j].getPiece()!=null && board[i][j].getPiece().getColor().equals(color)) {
                    possibleMoves.add(board[i][j].getPiece().getPossibleMoves(board,i,j));
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
    public boolean canMove(int rf, int cf, int rt, int ct){
        List<List<Integer>> possibleMoves=board[rf][cf].getPiece().getPossibleMoves(board,rf,cf);
        List<Integer> move=new ArrayList<>();
        move.add(ct);
        move.add(rt);
        for(List i:possibleMoves){
            if(i.equals(move)){
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