package game;

import models.Snake;
import models.Ladder;
import java.util.HashMap;
import java.util.ArrayList;

public class Board {
    private static final int BOARD_SIZE = 100;
    private HashMap<Integer, Integer> snakes;
    private HashMap<Integer, Integer> ladders;
    private ArrayList<Integer> moves;
    
    public Board() {
        snakes = new HashMap<>();
        ladders = new HashMap<>();
        moves = new ArrayList<>();
        initializeBoard();
    }
    
    private void initializeBoard() {
        // Initialize 10+ snakes
        addSnake(99, 54);
        addSnake(95, 75);
        addSnake(87, 24);
        addSnake(64, 60);
        addSnake(62, 19);
        addSnake(56, 53);
        addSnake(49, 11);
        addSnake(48, 26);
        addSnake(36, 6);
        addSnake(32, 10);
        addSnake(17, 7);
        
        // Initialize 10+ ladders
        addLadder(2, 38);
        addLadder(4, 14);
        addLadder(8, 31);
        addLadder(21, 42);
        addLadder(28, 84);
        addLadder(51, 67);
        addLadder(71, 91);
        addLadder(80, 99);
        addLadder(50, 66);
        addLadder(3, 22);
        addLadder(15, 44);
        
        // Track all 100+ move transitions
        for (int i = 0; i <= BOARD_SIZE; i++) {
            moves.add(i);
        }
    }
    
    private void addSnake(int head, int tail) {
        snakes.put(head, tail);
    }
    
    private void addLadder(int start, int end) {
        ladders.put(start, end);
    }
    
    public int getBoardSize() {
        return BOARD_SIZE;
    }
    
    // Chain-resolution loop to handle cascading snake-ladder collisions
    public int getFinalPosition(int position) {
        int finalPos = position;
        
        // do-while loop to handle cascading collisions across all 100 positions
        do {
            position = finalPos;
            
            // Check for snake
            if (snakes.containsKey(finalPos)) {
                finalPos = snakes.get(finalPos);
                System.out.println("    🐍 Snake bite! Sliding down from " + position + " to " + finalPos);
            }
            // Check for ladder
            else if (ladders.containsKey(finalPos)) {
                finalPos = ladders.get(finalPos);
                System.out.println("    🪜 Ladder climb! Moving up from " + position + " to " + finalPos);
            }
            
        } while (finalPos != position); // Continue until no more transitions
        
        return finalPos;
    }
    
    public void displayBoard() {
        System.out.println("\n=== BOARD CONFIGURATION ===");
        System.out.println("Snakes: " + snakes.size());
        snakes.forEach((head, tail) -> 
            System.out.println("  🐍 " + head + " -> " + tail));
        
        System.out.println("\nLadders: " + ladders.size());
        ladders.forEach((start, end) -> 
            System.out.println("  🪜 " + start + " -> " + end));
        System.out.println("===========================\n");
    }
}
