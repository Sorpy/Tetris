package config;

public class GameBoard {

    private static final int MAX_ROW = 23;
    private static final int MAX_COL = 11;
    public int[][] board;

    public GameBoard() {
        this.board = new int[MAX_ROW][MAX_COL];
    }

}
