package com.ebucher.game;

/**
 * Created by buche on 10/24/2017.
 */
public class Game {

    private Board board;

    public Game() {
        board = new Board();
    }

    public void makeMove(String direction) {
        board.makeMove(direction);
    }

    public void printBoard() {
        System.out.println(board);
    }

    public int score(){
        return board.score();
    }

}
