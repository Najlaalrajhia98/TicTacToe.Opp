package org.example;

import java.util.Random;

public class Bot extends Player {

    public Bot(char symbol) {
        super(symbol);
    }

    public void makeMove(Board board) {
        Random random = new Random();
        int slotChoice = random.nextInt(9) + 1;

        while (!board.isSlotEmpty(slotChoice)) {
            slotChoice = random.nextInt(9) + 1;
        }

        board.markSlot(slotChoice, getSymbol());
        System.out.println("Bot chose slot " + slotChoice);
    }
}
