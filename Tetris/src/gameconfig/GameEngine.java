package gameconfig;

import piece.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameEngine extends JFrame implements KeyListener,Runnable {

    public final static int BLOCK_SIZE = 25;

    private boolean gameOver = false;
    private TetrisPiece currentPiece;
    private Thread gameThread;
    private List<Block> allBlocksList = new ArrayList<>();


    public GameEngine() {

        super();

        gameThread = new Thread();

        gameThread.start();

        this.createBoard();
    }

    private void createBoard() {
        int gameWidth = BLOCK_SIZE*12 + 10;

        int gameHeight = BLOCK_SIZE*24 + 10;

        this.setSize(gameWidth, gameHeight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addKeyListener(this);
    }

    public void run(){
        while (!gameOver) {
            frameRate();
            updateGame();

        }

    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Block block : allBlocksList) {

            g.fillRect(block.getBlockPoint().x * BLOCK_SIZE,
                    block.getBlockPoint().y *BLOCK_SIZE,
                    BLOCK_SIZE,
                    BLOCK_SIZE);
        }
    }

    private void updateGame() {

        //checkForFullRow();

        if (currentPiece == null) {
            currentPiece = generatePiece();
        } else {
            if (!canMoveDown(currentPiece)) {
                currentPiece = null;
            } else {
                currentPiece.moveDown();
                repaint();
            }
        }
    }

    private void checkForFullRow() {
        int blockRowCounter;
        //getMaxY();
        for (int i = 0; i < 24; i++) {
            blockRowCounter =0;
            for (Block block :
                    allBlocksList) {
                if (block.getBlockPoint().y == i){
                    blockRowCounter++;
                }
            }
            if (blockRowCounter==12){
                for (Block block: allBlocksList) {
                    if (block.getBlockPoint().y == i) {
                        allBlocksList.remove(block);
                    }
                }
                for (Block block:allBlocksList){
                    if (block.getBlockPoint().y>i){
                        block.setBlockPoint(new Point (block.getBlockPoint().x,block.getBlockPoint().y-1));
                    }
                }
            }
        }
    }

    private TetrisPiece generatePiece() {
        Random r = new Random();
        int pieceChooser = r.nextInt(5);

        switch (pieceChooser) {
            case 0:
                return new IPiece(new Point(6,0),generateBlocks());
            case 1:
                return new JPiece(new Point(6,0),generateBlocks());
            case 2:
                return new OPiece(new Point(6,0),generateBlocks());
            case 3:
                return new TPiece(new Point(6,0),generateBlocks());
            case 4:
                return new ZPiece(new Point(6,0),generateBlocks());
        }
        return null;
    }

    private List<Block> generateBlocks(){
        List<Block> blockList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Block newBlock = new Block();
            blockList.add(newBlock);
            allBlocksList.add(newBlock);
        }
        return blockList;
    }

    private void frameRate() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean canMoveDown(TetrisPiece currentPiece) {
        for (Block block : currentPiece.getBlockList()) {
            if (block.getBlockPoint().y == 23) {
                return false;
            }

            Block checkBlock = checkSingleBlockMatch(new Point(block.getBlockPoint().x, block.getBlockPoint().y + 1));
            if (checkBlock!= null && !currentPiece.getBlockList().contains(checkBlock)) {
                return false;
            }
        }
        return true;
    }

    private boolean canMoveLeft(TetrisPiece currentPiece) {
        for (Block block : currentPiece.getBlockList()) {
            if (block.getBlockPoint().x == 0) {
                return false;
            }

            Block checkBlock = checkSingleBlockMatch(new Point(block.getBlockPoint().x-1, block.getBlockPoint().y));
            if (checkBlock != null && currentPiece.getBlockList().contains(checkBlock)) {
                return false;
            }
        }
        return true;
    }

    private boolean canMoveRight(TetrisPiece currentPiece) {
        for (Block block : currentPiece.getBlockList()) {
            if (block.getBlockPoint().x == 11) {
                return false;
            }
            Block checkBlock = checkSingleBlockMatch(new Point(block.getBlockPoint().x+1, block.getBlockPoint().y));
            if (checkBlock != null && currentPiece.getBlockList().contains(checkBlock)) {
                return false;
            }
        }
        return true;
    }

    private void canRotate(TetrisPiece currentPiece){
        boolean isValid = false;
        currentPiece.rotate();
        for (Block block : currentPiece.getBlockList()) {
            if (block.getBlockPoint().x>0 && block.getBlockPoint().x<=11){
                isValid =true;
            }
            Block checkBlock = checkSingleBlockMatch(block.getBlockPoint());
            if (checkBlock != null && currentPiece.getBlockList().contains(checkBlock)){
                isValid = true;
            }
        }
        if (!isValid) {
            for (int i = 0; i < 2; i++) {
                currentPiece.rotate();
            }
        }
    }


    private Block checkSingleBlockMatch(Point currentBlockPoint){
        Block returnBlock = null;
        for (Block block : allBlocksList) {
            if ((block.getBlockPoint().x == currentBlockPoint.x && block.getBlockPoint().y == currentBlockPoint.y)
            && currentPieceBlock().getBlockPoint().x != currentBlockPoint.x && currentPieceBlock().getBlockPoint().y != currentBlockPoint.y){
                        returnBlock =block;
            }
        }
        return returnBlock;
    }

    private Block currentPieceBlock(){
        Block bl = null;
        for (Block block : currentPiece.getBlockList()) {
            bl = block;
        }
        return bl;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_RIGHT :
                if (canMoveRight(currentPiece)){
                    currentPiece.move(true);
                }
                break;
            case KeyEvent.VK_LEFT :
                if (canMoveLeft(currentPiece)){
                    currentPiece.move(false);
                }
                break;
            case KeyEvent.VK_UP :
                canRotate(currentPiece);
                break;
            case KeyEvent.VK_DOWN :
                if (canMoveDown(currentPiece)){
                    currentPiece.moveDown();
                }
                break;


        }
    }
}
