package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import static org.example.Manager.loadGame;
import static org.example.Manager.saveGame;

public class Main {
    private static final String FILE_NAME = "game.json";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        File file = new File(FILE_NAME);

        if (file.exists()) {
            System.out.println("There's a saved game. Do you want to resume it? (Y/N)");
            String choice = scanner.next();
            if (choice.equalsIgnoreCase("Y")) {
                GameState gameState = Manager.loadGame(FILE_NAME, board);
                if (gameState != null) {
                    board.loadGameState(gameState);
                    playGame(board, scanner);
                } else {
                    System.out.println("Error loading saved game. Starting new game.");
                    playGame(board, scanner);
                }
            } else {
                System.out.println("Welcome to Tic Tac Toe!");
                playGame(board, scanner);
            }
        } else {
            System.out.println("Welcome to Tic Tac Toe!");
            playGame(board, scanner);
        }
    }

    public static void playGame(Board board, Scanner scanner) {
        System.out.println("Do you want to play against a bot or against player 2? (B/P)");
        char choice = scanner.next().charAt(0);
        scanner.nextLine(); // consume the newline character

        Player player1 = new Player('X');
        Player player2;

        if (choice == 'P' || choice == 'p') {
            System.out.println("You have chosen to play against player 2.");
            System.out.println("Player 1, choose your symbol (X or O):");
            char symbolChoice1 = scanner.next().charAt(0);
            player1 = new Player(symbolChoice1);
            System.out.println("Player 2, your symbol is " + (symbolChoice1 == 'X' ? 'O' : 'X'));
            player2 = new Player(symbolChoice1 == 'X' ? 'O' : 'X');
            playWithPerson(board, player1, scanner, player2);
        } else if (choice == 'B' || choice == 'b') {
            System.out.println("You have chosen to play against a bot.");
            System.out.println("Do you want to be X or O? (X/O)");
            char symbolChoice1 = scanner.next().charAt(0);
            player1 = new Player(symbolChoice1);
            System.out.println("The bot will be " + (symbolChoice1 == 'X' ? 'O' : 'X'));
            player2 = new Bot(player1.getSymbol() == 'X' ? 'O' : 'X');
            playWithBot(board, player1, scanner, player2);
        } else {
            System.out.println("Invalid choice. Starting new game.");
            playGame(board, scanner);
            return;
        }

    }

    public static void playWithBot(Board board, Player player1, Scanner scanner, Player player2) {
        ArrayList<Integer> slotChoiceList = new ArrayList<>();
        boolean playStatus = true;

        while (playStatus) {
            board.printBoard();

            int slotChoice;
            boolean validInput = false;
            do {
                System.out.println("Player " + player1.getSymbol() + ", choose a slot (1-9):");
                slotChoice = scanner.nextInt();
                scanner.nextLine(); // consume the newline character
                if (slotChoice < 1 || slotChoice > 9) {
                    System.out.println("Invalid input. Please choose a slot between 1 and 9.");
                } else if (!board.isSlotEmpty(slotChoice)) {
                    System.out.println("That slot is already taken. Please choose another slot.");
                } else {
                    validInput = true;
                }
            } while (!validInput);
            board.markSlot(slotChoice, player1.getSymbol());
            slotChoiceList.add(slotChoice);

            if (board.checkForWin(player1.getSymbol())) {
                board.printBoard();
                System.out.println("Player " + player1.getSymbol() + " wins! Game over.");
                playStatus = false;
                continue;
            }
            if (Board.drawGame(board.board, playStatus)) {
                playStatus = false;
                continue;
            }
            ((Bot) player2).makeMove(board);
            if (board.checkForWin(player2.getSymbol())) {
                board.printBoard();
                System.out.println("Bot win! Game over.");
                playStatus = false;
                continue;
            }
            if (Board.drawGame(board.board, playStatus)) {
                playStatus = false;
                continue;
            }

            // Prompt user to save game
            System.out.println("Do you want to save the game and continue later ? (Y/N)");
            char saveChoice = scanner.nextLine().charAt(0);
            if (saveChoice == 'Y' || saveChoice == 'y') {
                char symbolChoice1 = player1.getSymbol();
                saveGame(board.board, slotChoiceList, FILE_NAME, playStatus, symbolChoice1, player2.getSymbol());
                System.out.println("Game saved successfully!");
                System.exit(0);

            }
        }
    }


    public static void playWithPerson(Board board, Player player1, Scanner scanner, Player player2) {
        ArrayList<Integer> slotChoiceList = new ArrayList<>();
        boolean playStatus = true;

        while (playStatus) {
          //  board.printBoard();

            int slotChoice;
            boolean validInput = false;
            int currentPlayer = 1;

                do {

                        System.out.println("Player " + player1.getSymbol() + ", choose a slot (1-9):");
                        slotChoice = scanner.nextInt();
                        scanner.nextLine(); // consume the newline character
                        currentPlayer = 2;
                        if (slotChoice < 1 || slotChoice > 9) {
                            System.out.println("Invalid input. Please choose a slot between 1 and 9.");
                        } else if (!board.isSlotEmpty(slotChoice)) {
                            System.out.println("That slot is already taken. Please choose another slot.");
                        } else {
                            validInput = true;
                        }

                } while (!validInput);

                board.markSlot(slotChoice, player1.getSymbol());
                slotChoiceList.add(slotChoice);
                 board.printBoard();
            if (board.checkForWin(player1.getSymbol())) {
                board.printBoard();
                System.out.println("Player " + player1.getSymbol() + " wins! Game over.");
                playStatus = false;
                continue;
            }
            if (Board.drawGame(board.board, playStatus)) {
                playStatus = false;
                continue;
            }

                if (currentPlayer == 2) {
                    do {
                        System.out.println("Player " + player2.getSymbol() + ", choose a slot (1-9):");
                        slotChoice = scanner.nextInt();
                        scanner.nextLine(); // consume the newline character
                        currentPlayer = 2;
                        if (slotChoice < 1 || slotChoice > 9) {
                            System.out.println("Invalid input. Please choose a slot between 1 and 9.");
                        } else if (!board.isSlotEmpty(slotChoice)) {
                            System.out.println("That slot is already taken. Please choose another slot.");
                        } else {
                            validInput = true;
                        }

                    } while (!validInput);

                }
                board.markSlot(slotChoice, player2.getSymbol());
                slotChoiceList.add(slotChoice);
                board.printBoard();

                if (board.checkForWin(player2.getSymbol())) {
                    System.out.println("Player " + player2.getSymbol() + " wins! Game over.");
                    playStatus = false;
                    continue;
                }
                if (Board.drawGame(board.board, playStatus)) {
                    playStatus = false;
                    continue;
                }
                // Prompt user to save game
                System.out.println("Do you want to save the game and continue later ? (Y/N)");
                char saveChoice = scanner.nextLine().charAt(0);
                if (saveChoice == 'Y' || saveChoice == 'y') {
                    char symbolChoice1 = player1.getSymbol();
                    saveGame(board.board, slotChoiceList, FILE_NAME, playStatus, symbolChoice1, player2.getSymbol());
                    System.out.println("Game saved successfully!");
                    System.exit(0);

                }

            }
        }
    }
