import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


//What a board for this game will look like
//an array of integers will be used to represent the board
//
//Includes basic gettors for the stuff in a board, 
//Also helping classes for the board
class Board {
  
  int Board [][]; //
  int Row, Column = 0;
  
  //Every board requires a row/column and the board sequence to keep track of winner
  private Board(int[][] board, int row, int column){
      this.Board = board;
      this.Row = row;
      this.Column = column;
      SequencesCounter.CountSequences(board, column, column);
  }
  
  // 
  public Board (int Row, int Column){
    this.Row = Row;
    this.Column = Column;
    this.Board = new int [Row][Column];    
  }
  
  public int[][] getBoard(){
      return this.Board;
  }
  
  public int getRow(){
    return Row;
  }
  
  public int getColumn(){
    return Column;
  }
  
  //Fill board with 0's
  void emptyBoard(){
    for (int i = 0; i < this.Board.length; i++){
      for (int j = 0; j < this.Board[i].length; j++){
        this.Board[i][j] = 0;
      }
    }
  }
  
  //Print the board/ tostring?
  public void printCurrentBoard(){
  for(int i=0; i<this.Row; i++){
    for(int j=0; j<this.Column; j++){
      System.out.print(this.Board[i][j]);
   }
    System.out.println();
  } 
 }
 
  //Drop the piece into the column, for the given player
  public boolean insertPiece(int Column, int playerNumber){
    for(int row = Row - 1; row >= 0; row--){
        if(Board[row][Column - 1] == 0){
            Board[row][Column - 1] = playerNumber;
            return true;
        }
    }
    return false;
  }
  
  //once 4 in a row is made, the specific player wins
  public boolean HasWon(int playerNumber){
      return SequencesCounter.CountSequences(this.Board, playerNumber, 4) > 0;
  }
  
  //Checks if the move is legal
  public boolean isLegal(){
      for(int i = 0; i < this.Board.length; ++i){
          for(int j = 0; i < this.Board.length; ++j){
              if(this.Board[i][j] == 0){
                  return true;
              }
          }
      }
      return false;
  }
  
  //copies board
  public Board makeACopy(){
      int[][] board = new int[this.Board.length][];
      for(int i = 0; i < this.Board.length; ++i){
          board[i] = Arrays.copyOf(this.Board[i], this.Board[i].length);
      }
      return new Board(board, this.Row, this.Column);
  }
}
