package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.util.ArrayList;

public class Manager {

    public static void saveGame(char[][] board, ArrayList<Integer> slotchoiceArrayList, String DATA_FILE_PATH, boolean playStatus, char symbolChoice1, char symbolChoice2) {
        try {
            playStatus = true;
            // Create a Gson object with pretty printing enabled
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            // Create a GameState object to hold the game state
            GameState gameState = new GameState(board, slotchoiceArrayList, playStatus, symbolChoice1, symbolChoice2);

            // Convert the GameState object to JSON
            String json = gson.toJson(gameState);

            // Write the JSON to the file
            FileWriter writer = new FileWriter(DATA_FILE_PATH);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error while saving the game: " + e.getMessage());
        }
    }

    public static GameState loadGame(String DATA_FILE_PATH,Board board) {
        try {

            // Read the JSON from the file
            BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE_PATH));
            Gson gson = new Gson();
            GameState gameState = gson.fromJson(reader, GameState.class);
            System.out.println("Updated board:");
            for (int i = 0; i < gameState.getBoard().length; i++) {
                for (int j = 0; j < gameState.getBoard()[i].length; j++) {
                    System.out.print(gameState.getBoard()[i][j] + " ");
                }
                System.out.println();
            }
            reader.close();
            if (gameState.isPlayStatus()) {
                // clear slots that have already been played
                for (int slot : gameState.getSlotChoiceArrayList()) {
                    board.clearSlot(slot);
                }
            }

            return gameState;
        } catch (FileNotFoundException e) {
            System.out.println("Save file not found: " + DATA_FILE_PATH);
        } catch (IOException e) {
            System.out.println("Error while loading the game: " + e.getMessage());
        }

        return null;
    }
}