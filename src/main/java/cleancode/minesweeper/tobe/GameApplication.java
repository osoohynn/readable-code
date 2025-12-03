package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.gamelevel.Advanced;
import cleancode.minesweeper.tobe.gamelevel.Beginner;
import cleancode.minesweeper.tobe.gamelevel.Middle;
import cleancode.minesweeper.tobe.gamelevel.VeryBeginner;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GameApplication {

    public static void main(String[] args) {
        MineSweeper mineSweeper = new MineSweeper(new VeryBeginner());
        mineSweeper.run();
    }
}
