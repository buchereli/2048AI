package com.ebucher;

import com.ebucher.game.Game;

import java.util.Scanner;

/**
 * Created by buche on 10/24/2017.
 */
public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        game.printBoard();
        Scanner sc = new Scanner(System.in);

        while (true) {
            game.makeMove(sc.nextLine());
            System.out.println(game.score());
            game.printBoard();
        }
    }

}
