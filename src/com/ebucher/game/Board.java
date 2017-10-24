package com.ebucher.game;

/**
 * Created by buche on 10/24/2017.
 */
public class Board {

    final private int[][] board = new int[4][4];
    private int score;

    // Creates a new board with two spawns
    Board() {
        score = 0;

        for (int x = 0; x < 4; x++)
            for (int y = 0; y < 4; y++)
                board[x][y] = 0;

        spawn();
        spawn();
    }

    // Adds a new value to the board
    private void spawn() {
        int x = random(4);
        int y = random(4);
        while (board[x][y] != 0) {
            x = random(4);
            y = random(4);
        }

        board[x][y] = random(10) == 0 ? 2 : 1;
    }

    // Generates a random number between 0-r
    private int random(int r) {
        return (int) (Math.random() * r);
    }

    // Creates an inverse version of the board for simpler AI
    public int[][] inverseBoard() {
        final int[][] inverseBoard = new int[4][4];
        final int max = max();

        for (int x = 0; x < 4; x++)
            for (int y = 0; y < 4; y++)
                inverseBoard[x][y] = -(board[x][y] - max);

        return inverseBoard;
    }

    // Finds the max value on the board
    private int max() {
        int max = Integer.MIN_VALUE;
        for (int x = 0; x < 4; x++)
            for (int y = 0; y < 4; y++)
                if (max < board[x][y])
                    max = board[x][y];

        return max;
    }

    // Makes intended move and returns whether move is legal
    boolean makeMove(String direction) {
        boolean legal = false;

        switch (direction) {
            case "up":
                for (int y = 1; y < 4; y++)
                    for (int x = 0; x < 4; x++)
                        if (push(x, y, direction))
                            legal = true;
                break;
            case "right":
                for (int x = 2; x >= 0; x--)
                    for (int y = 0; y < 4; y++)
                        if (push(x, y, direction))
                            legal = true;
                break;
            case "down":
                for (int y = 2; y >= 0; y--)
                    for (int x = 0; x < 4; x++)
                        if (push(x, y, direction))
                            legal = true;
                break;
            case "left":
                for (int x = 1; x < 4; x++)
                    for (int y = 0; y < 4; y++)
                        if (push(x, y, direction))
                            legal = true;
                break;
        }

        if (legal)
            spawn();

        return legal;
    }

    // Moves the piece at [x][y] in given direction
    private boolean push(int x, int y, String direction) {
        if (board[x][y] == 0)
            return false;

        switch (direction) {
            case "up":
                for (int i = y - 1; i >= 0; i--) {
                    int val = board[x][i];
                    if (val != 0) {
                        if (val == board[x][y]) {
                            board[x][i]++;
                            board[x][y] = 0;
                            score += Math.pow(2, val) * 2;
                            return true;
                        }
                        if (y != i + 1) {
                            board[x][i + 1] = board[x][y];
                            board[x][y] = 0;
                            return true;
                        }
                        return false;
                    } else if (i == 0) {
                        board[x][i] = board[x][y];
                        board[x][y] = 0;
                        return true;
                    }
                }
                break;
            case "right":
                for (int i = x + 1; i < 4; i++) {
                    int val = board[i][y];
                    if (val != 0) {
                        if (val == board[x][y]) {
                            board[i][y]++;
                            board[x][y] = 0;
                            score += Math.pow(2, val) * 2;
                            return true;
                        }
                        if (x != i - 1) {
                            board[i - 1][y] = board[x][y];
                            board[x][y] = 0;
                            return true;
                        }
                        return false;
                    } else if (i == 3) {
                        board[i][y] = board[x][y];
                        board[x][y] = 0;
                        return true;
                    }
                }
                break;
            case "down":
                for (int i = y + 1; i < 4; i++) {
                    int val = board[x][i];
                    if (val != 0) {
                        if (val == board[x][y]) {
                            board[x][i]++;
                            board[x][y] = 0;
                            score += Math.pow(2, val) * 2;
                            return true;
                        }
                        if (y != i - 1) {
                            board[x][i - 1] = board[x][y];
                            board[x][y] = 0;
                            return true;
                        }
                        return false;
                    } else if (i == 3) {
                        board[x][i] = board[x][y];
                        board[x][y] = 0;
                        return true;
                    }
                }
                break;
            case "left":
                for (int i = x - 1; i >= 0; i--) {
                    int val = board[i][y];
                    if (val != 0) {
                        if (val == board[x][y]) {
                            board[i][y]++;
                            board[x][y] = 0;
                            score += Math.pow(2, val) * 2;
                            return true;
                        }
                        if (x != i + 1) {
                            board[i + 1][y] = board[x][y];
                            board[x][y] = 0;
                            return true;
                        }
                        return false;
                    } else if (i == 0) {
                        board[i][y] = board[x][y];
                        board[x][y] = 0;
                        return true;
                    }
                }
                break;
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder stringRep = new StringBuilder();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++)
                stringRep.append("[").append(board[x][y]).append("]");

            stringRep.append(System.lineSeparator());
        }

        return stringRep.toString();
    }

    int score(){
        return score;
    }

}
