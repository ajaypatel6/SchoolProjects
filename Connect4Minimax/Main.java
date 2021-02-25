/**
 * CONNECT 4 - First to four connecting pieces wins
 * with Minimax AI
 * 
 * Cosc 3p71 Assignment 1
 * October 2019
 * Ajay Patel
 * 
 * HIT RUN and play
 * 
 * Type 1-7, representing the columns, to drop a piece in the column**
 * 
 * (Player vs Player)
 * Each player takes turns to drop a piece in connect 4 until winner is found,
 * 
 * (Player vs AI) 
 * Player faces the AI and tries to beat it while the AI is using the Minimax algorithm
 */ 

public class Main {
  
    public static void main(String[] args) {
      
        PlayerBase first = new AIAgent("AI", 2); // THE AI's piece is '2'
        PlayerBase second = new HumanPlayer("Player", 1); // The players piece is '1'
        ConnectFourGame game = new ConnectFourGame(second, first); //you go first
        game.RunAGame();
    }
    
}