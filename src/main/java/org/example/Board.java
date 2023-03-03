package org.example;

import java.util.ArrayList;

public class Board {
    char[][] board;
    private Object slotChoiceList;
    private boolean playStatus;
private char symbolChoice1;
    private char symbolChoice2;
    public Board() {
        board = new char[][]{
                {'-', '*', '-', '*', '-'},
                {' ', '|', ' ', '|', ' '},
                {' ', '|', ' ', '|', ' '},
                {' ', '|', ' ', '|', ' '},
                {'-', '*', '-', '*', '-'}
        };
    }

    public void printBoard() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    public boolean isSlotEmpty(int slot) {
        switch (slot) {
            case 1:
                return board[1][0] == ' ';
            case 2:
                return board[1][2] == ' ';
            case 3:
                return board[1][4] == ' ';
            case 4:
                return board[2][0] == ' ';
            case 5:
                return board[2][2] == ' ';
            case 6:
                return board[2][4] == ' ';
            case 7:
                return board[3][0] == ' ';
            case 8:
                return board[3][2] == ' ';
            case 9:
                return board[3][4] == ' ';
            default:
                return false;
        }
    }

    public void markSlot(int slot, char symbol) {
        switch (slot) {
            case 1:
                board[1][0] = symbol;
                break;
            case 2:
                board[1][2] = symbol;
                break;
            case 3:
                board[1][4] = symbol;
                break;
            case 4:
                board[2][0] = symbol;
                break;
            case 5:
                board[2][2] = symbol;
                break;
            case 6:
                board[2][4] = symbol;
                break;
            case 7:
                board[3][0] = symbol;
                break;
            case 8:
                board[3][2] = symbol;
                break;
            case 9:
                board[3][4] = symbol;
                break;
            default:
                break;
        }
    }

    public boolean checkForWin(char symbol) {
        return (board[1][0] == symbol && board[1][2] == symbol && board[1][4] == symbol ||
                board[2][0] == symbol && board[2][2] == symbol && board[2][4] == symbol ||
                board[3][0] == symbol && board[3][2] == symbol && board[3][4] == symbol ||
                board[1][0] == symbol && board[2][0] == symbol && board[3][0] == symbol ||
                board[1][2] == symbol && board[2][2] == symbol && board[3][2] == symbol ||
                board[1][4] == symbol && board[2][4] == symbol && board[3][4] == symbol ||
                board[1][0] == symbol && board[2][2] == symbol && board[3][4] == symbol ||
                board[1][4] == symbol && board[2][2] == symbol && board[3][0] == symbol);

    }
    public static boolean drawGame(char [][] board, boolean playStatus) {

        boolean draw = true;  // Flag to indicate if the game is a draw
        for (int row = 1; row < 4; row++)
        {
            for (int column = 0; column < 5; column += 2)
            {
                if (board[row][column] == ' ')
                {  // If an empty slot is found
                    draw = false;  // Set the flag to false
                }
            }
        }
        if (draw)
        {  // If the game is a draw

            System.out.println("It's a draw! Game over.");
        }
        return draw;
    }
    public void loadGameState(GameState gameState) {
        this.board = gameState.getBoard();
        this.slotChoiceList = gameState.getSlotChoiceArrayList();
        this.playStatus = gameState.isPlayStatus();
        this.symbolChoice1 = gameState.getSymbolChoice1();
        this.symbolChoice2 = gameState.getSymbolChoice2();
    }
    public void clearSlot(int slot) {
        int row = (slot - 1) / 3;
        int col = (slot - 1) % 3;
        board[row][col] = ' ';
    }


}