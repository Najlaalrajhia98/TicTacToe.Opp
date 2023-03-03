package org.example;

import java.util.ArrayList;

public class GameState {
    private char[][] board;
    private ArrayList<Integer> slotChoiceArrayList;
    private boolean playStatus;
    private char symbolChoice1;
    private char symbolChoice2;

    public GameState(char[][] board, ArrayList<Integer> slotChoiceArrayList, boolean playStatus, char symbolChoice1, char symbolChoice2) {
        this.board = board;
        this.slotChoiceArrayList = slotChoiceArrayList;
        this.playStatus = playStatus;
        this.symbolChoice1 = symbolChoice1;
        this.symbolChoice2 = symbolChoice2;
    }

    public char[][] getBoard() {
        return board;
    }

    public ArrayList<Integer> getSlotChoiceArrayList() {
        return slotChoiceArrayList;
    }

    public boolean isPlayStatus() {
        return playStatus;
    }

    public char getSymbolChoice1() {
        return symbolChoice1;
    }

    public char getSymbolChoice2() {
        return symbolChoice2;
    }
}
