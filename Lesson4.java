/*
1. Полностью разобраться с кодом, попробовать переписать с нуля, стараясь не подглядывать в методичку.
2. Переделать проверку победы, чтобы она не была реализована просто набором условий, например, с использованием циклов.
3. * Попробовать переписать логику проверки победы, чтобы она работала для поля 5х5 и количества фишек 4. Очень желательно не делать это просто набором условий для каждой из возможных ситуаций;
4. *** Доработать искусственный интеллект, чтобы он мог блокировать ходы игрока.
 */


import java.util.Random;
import java.util.Scanner;

public class Lesson4 {
    private static final Random RANDOM = new Random();
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = '0';
    private static final char DOT_EMPTY = '_';
    private static char[][] field;
    private static int fieldSizeX;
    private static int fieldSizeY;



    public static void main(String[] args) {
        fieldSizeY = 3;
        fieldSizeX = 3;
        while (true) {
            initField();
            printField();
            while (true) {
                humanTurn();
                printField();
                if(gameCheck(DOT_HUMAN)) break;
                aiTurn();
                printField();
                if (gameCheck(DOT_AI)) break;
            }
            break;
        }
    }

    private static void aiTurn() {
        int x, y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isCellEmpty(x, y));

        field[y][x] = DOT_AI;
    }

    private static boolean gameCheck(char dot) {
        if (checkWin(dot)) {
            if (dot == DOT_HUMAN) {
                System.out.println("Человек победил");
            } else {
                System.out.println("AI победил");
            }
            return true;
        }
        if (checkDraw()) {
            System.out.println("Ничья");
            return true;
        }
        return false;

    }

    private static boolean checkWin(char dot) {
        for (int y = 0; y < 3; y++)
            if ((field[y][0] == dot && field[y][1] == dot && field[y][2] == dot) || (field[0][y] == dot && field[1][y] == dot && field[2][y] == dot))
                return true;
        if ((field[0][0] == dot && field[1][1] == dot && field[2][2] == dot) || (field[2][0] == dot && field[1][1] == dot && field[0][2] == dot))
            return true;
        return false;

    }

    private static boolean checkDraw() {
        for (int y = 0; y < field.length; y++)
            for (int x = 0; x < field[y].length; x++)
                if (field[y][x] == DOT_EMPTY)
                    return false;
        return true;
    }

    private static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты х и у через пробел:");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!isCellValid(x, y) || !isCellEmpty(x, y));

        field[y][x] = DOT_HUMAN; 
    }

    private static boolean isCellEmpty(int x, int y) {
        return field[y][x] == DOT_EMPTY;
    }

    private static boolean isCellValid(int x, int y) {
        return x >= 0 && y >= 0 && x < fieldSizeX && y < fieldSizeY;
    }

    private static void printField() {
        System.out.println("   " + 1 + " " + 2 + " " + 3);
        for (int y = 0; y < field.length; y++) {
            System.out.print((y+1) + " " + "|");
            for (int x = 0; x < field[y].length; x++) {
                System.out.print(field[y][x] + "|");
            }
            System.out.println();
        }
    }

    private static void initField() {
        field = new char[fieldSizeY][fieldSizeX];
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                field[y][x]=DOT_EMPTY;
            }
        }
    }

}
