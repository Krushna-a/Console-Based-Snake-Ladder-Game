package game;

import models.Player;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GameEngine {
    private Board board;
    private Queue<Player> players;
    private Dice dice;
    private boolean gameWon;
    private Scanner scanner;
    
    public GameEngine(int numPlayers, int numDice) {
        if (numPlayers < 2 || numPlayers > 6) {
            throw new IllegalArgumentException("Number of players must be between 2 and 6");
        }
        
        this.board = new Board();
        this.players = new LinkedList<>();
        this.dice = new Dice(numDice);
        this.gameWon = false;
        this.scanner = new Scanner(System.in);
        
        initializePlayers(numPlayers);
    }
    
    private void initializePlayers(int numPlayers) {
        String[] playerNames = {"Red", "Blue", "Green", "Yellow", "Orange", "Purple"};
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player(playerNames[i]));
        }
    }
    
    public void startGame() {
        System.out.println("\n🎲 WELCOME TO SNAKE & LADDER GAME 🎲\n");
        System.out.println("Players: " + players.size());
        System.out.println("Dice: " + dice.getNumberOfDice());
        board.displayBoard();
        
        System.out.println("Press Enter to start the game...");
        scanner.nextLine();
        
        while (!gameWon) {
            playTurn();
        }
        
        scanner.close();
    }
    
    private void playTurn() {
        Player currentPlayer = players.poll();
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("🎮 " + currentPlayer.getName() + "'s turn");
        System.out.println("Current Position: " + currentPlayer.getPosition());
        System.out.print("Press Enter to roll the dice...");
        scanner.nextLine();
        
        boolean keepRolling = true;
        
        while (keepRolling) {
            int diceRoll = dice.roll();
            System.out.println("🎲 Rolled: " + diceRoll);
            
            int newPosition = currentPlayer.getPosition() + diceRoll;
            
            // Exact-landing rule: reject overshoots beyond cell 100
            if (newPosition > board.getBoardSize()) {
                System.out.println("❌ Overshoot! Need exactly " + 
                    (board.getBoardSize() - currentPlayer.getPosition()) + 
                    " to win. Stay at " + currentPlayer.getPosition());
                keepRolling = false;
            } else {
                System.out.println("  Moving from " + currentPlayer.getPosition() + 
                    " to " + newPosition);
                
                // Apply chain-resolution loop for cascading collisions
                int finalPosition = board.getFinalPosition(newPosition);
                currentPlayer.setPosition(finalPosition);
                
                System.out.println("✓ Final Position: " + currentPlayer.getPosition());
                
                // Check for win
                if (currentPlayer.getPosition() == board.getBoardSize()) {
                    System.out.println("\n" + "🎉".repeat(20));
                    System.out.println("🏆 " + currentPlayer.getName() + " WINS! 🏆");
                    System.out.println("🎉".repeat(20) + "\n");
                    gameWon = true;
                    keepRolling = false;
                }
                // Bonus rule: rolling a 6 gives another turn
                else if (diceRoll == 6 || (dice.getNumberOfDice() > 1 && diceRoll == 6 * dice.getNumberOfDice())) {
                    System.out.println("🎁 Bonus! You rolled a 6! Roll again!");
                    System.out.print("Press Enter to roll again...");
                    scanner.nextLine();
                } else {
                    keepRolling = false;
                }
            }
        }
        
        // Add player back to queue if game not won
        if (!gameWon) {
            players.add(currentPlayer);
        }
    }
    
    public void displayLeaderboard() {
        System.out.println("\n📊 Current Standings:");
        int rank = 1;
        for (Player player : players) {
            System.out.println(rank++ + ". " + player);
        }
    }
}
