package main;

import game.GameEngine;
import java.util.Scanner;

public class SnakeAndLadderGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║   SNAKE & LADDER GAME - JAVA OOP      ║");
        System.out.println("╚═══════════════════════════════════════╝");
        
        // Get number of players (2-6)
        int numPlayers = 0;
        while (numPlayers < 2 || numPlayers > 6) {
            System.out.print("\nEnter number of players (2-6): ");
            try {
                numPlayers = Integer.parseInt(scanner.nextLine());
                if (numPlayers < 2 || numPlayers > 6) {
                    System.out.println("❌ Please enter a number between 2 and 6!");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input! Please enter a number.");
            }
        }
        
        // Get number of dice
        int numDice = 0;
        while (numDice < 1 || numDice > 3) {
            System.out.print("Enter number of dice (1-3): ");
            try {
                numDice = Integer.parseInt(scanner.nextLine());
                if (numDice < 1 || numDice > 3) {
                    System.out.println("❌ Please enter a number between 1 and 3!");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input! Please enter a number.");
            }
        }
        
        // Start the game
        try {
            GameEngine game = new GameEngine(numPlayers, numDice);
            game.startGame();
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        
        scanner.close();
    }
}
