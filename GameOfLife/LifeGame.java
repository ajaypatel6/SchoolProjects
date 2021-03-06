import java.io.*;
import java.util.Scanner;

/* COSC 1P03
 * ASSIGNMENT #1
 * Name: Ajay Patel
 * Student #: 5660055
 *
 * Program explanation should go here.
 */

public class LifeGame {
  
  private static Scanner userInput;
  private static Scanner inputfile;
  private int[][] Matrix; //
  private int r; // represents the rows for the matrix
  private int c; // represents the columns for the matrix
  private int Generations; // counts the generations
  private int CurrentGeneration; //keeps track of the current generation
  
  public LifeGame(){ // Constructor for lifegame
    this.CurrentGeneration = 0; // both set to 0 to begin
    this.Generations = 0;
  }
  
  /***** series of gettors and settors****************/  
// gettor and settor for the inputfile  
  public static Scanner getInputfile() { 
    return inputfile;
  }
  
  public static void setInputfile(Scanner inputfile) {
    LifeGame.inputfile = inputfile;
  }
  
// gettor and settor for the actual matrix 
  public int[][] getMatrix() {
    return Matrix;
  }
  
  public void setMatrix(int[][] grid) {
    Matrix = grid;
  }
  
//  gettor and settor for the rows 
  public int getr() {
    return r;
  }
  
  public void setr(int rows) {
    r = rows;
  }
  
//gettor and settors for the columns  
  public int getc() {
    return c;
  }
  
  public void setc(int cols) {
    c = cols;
  }
  
//gettor and settor for the generation that is inputted from the file 
  public int getGenerations() {
    return Generations;
  }
  
  public void setGenerations(int generations) {
    Generations = generations;
  }

//gettor and settor for the current generation that we are iterating  
  public int getCurrentGeneration() {
    return CurrentGeneration;
  }
  
  public void setCurrentGeneration(int Generation) {
    this.CurrentGeneration = Generation;
  }
  /***** series of gettors and settors ENDS****************/
  
// This method loads the matrix from the file, it returns true if it works, false if unsuccesful, uses booleans
  public boolean loadState(String fileName){
    boolean Success = false; // originally set to false
    
    File file = new File(fileName); //called fileName (sent into this method)
    
    try { // try catch sequence while reading and "traversing" the matrix
      inputfile = new Scanner(file);
      this.Matrix = readFile(); // invokes the readFile method down below
      this.showGrid(); //uses the showGrid method
    }
    catch (Exception exn) {
    }
    inputfile.close(); // alawys close your streams!!
    return Success; // returns the value of the boolean
  }//end loadState
  
  public  int[][] readFile()  {
    int[][] NewMatrix = null; // the matrix must be null before starting
    if(inputfile.hasNextInt()) // scanner looks at values in the file
    {
      this.r= inputfile.nextInt(); //the first value in the file represents the rows
      if(inputfile.hasNextInt()) {
        this.c= inputfile.nextInt(); // the second value in the txt.file shows the columns
        NewMatrix= new int[r][c]; // creates the new matrix with these two dimensions
        for(int row = 0; row < this.r; row ++){ //Nested for loop to look through the whole matrix
          for(int col = 0; col < this.c; col ++){ 
            if(inputfile.hasNextInt()){
              NewMatrix[row][col] = inputfile.nextInt();//populates the matrix
            }
          }
        }
      }
    }
    return NewMatrix; // returns the 2d array of the matrix
  } //end readfile
  
// the show grid method uses spaces and positions while using for loops, the first for loop looks at the ith position of the matrix
// uses the shorter notation for for loops the for each operator
// creates a matrix by however many dimensions
  public  void showGrid() {
    String cell = "";
    for(int[] i : this.Matrix){ 
      cell += " ";
      
      for(int val : i)
        cell += val + "  ";
      cell += " ";
      cell += "\n";
    }
    System.out.println("Final Game State");
    System.out.println(cell);
  }//end showGrid 
  
// This method is used with the rulesoflife method below and the countneighbours method
// a 2d array is made according to the dimensions of the matrix
// also creates the modifiable and dynamic version of the cell 
  public int[][] nextGeneration(){
    int[][] nextGenerationOfCells = new int[this.Matrix.length][this.Matrix[0].length];
    int newCellGenerated;
    for (int i = 0; i < this.Matrix.length; i++){ //nested for loop to look through the matrix
      for (int j = 0; j < this.Matrix[0].length; j++){
        newCellGenerated = countNeighbours(i, j);
        if (gameRules(newCellGenerated, this.Matrix[i][j]==1)){ //uses an if statement that looks at what was passed into the rules method
          nextGenerationOfCells[i][j] = 1; //will make the new cell 1 if the prior matrix had it at 1 at the position
        }
      }
    }
    return nextGenerationOfCells; //returns the new matrix
  } //end nextGeneration
  
  // 1 neighbour = death
  // 2 neighbour = stasis
  // 3 neighbour = birth
  // 4 neighbour = death
  // the gameRules method takes the number of neighbours and if a cell is alive as a boolean
  // series of if / else statements to determine the state of the cell if alive or dead
  public  boolean gameRules(int numberOfNeighbors, boolean alive){
    if( alive && (numberOfNeighbors == 2 || numberOfNeighbors == 3)) 
      return true;
    else if (!alive && numberOfNeighbors == 3)
      return true;
    else
      return false;
  } //end gamerules
  
  //method of getCell to accept the row and the column and returns it (acts as a gettor)
  public int getCell(int row, int col){
    return this.Matrix[row][col];
  } //end getCell
  
//counts the neighbours of a cell for every cell
//includes the details on how to proceed with the wrapping (not 100% this works)
  public int countNeighbours(  int row, int column) {   
    int CurrentColumn = column;
    int CurrentRow = row;
    int AliveCount = 0;
    int LeftColumn = column - 1;
    
    //wrapping detail 
    if(LeftColumn  < 0){
      LeftColumn  += this.Matrix[0].length;
    }
    
    int RightColumn = (column + 1) % this.Matrix[0].length;
    int TopRow = row - 1;
    if(TopRow < 0){
      TopRow += this.Matrix.length;
    }
    
    int BottomRow = (row + 1 ) % this.Matrix.length;
    
//the following are the procedures that each cell goes through, uses the get cell method if the cell is alive and 
// counts the number of cells alive in alivecount variable
    
// Checks top left
    if(this.getCell(TopRow, LeftColumn) == 1){
      AliveCount ++;
    }
// Checks top
    if(this.getCell(TopRow, CurrentColumn) == 1){
      AliveCount ++;
    }
// Checks top right
    if(this.getCell(TopRow, RightColumn) == 1){
      AliveCount ++;
    }
// Checks right
    if(this.getCell(CurrentRow, RightColumn) == 1){
      AliveCount ++;
    }
// Checks bottom right
    if(this.getCell(BottomRow, RightColumn) == 1){
      AliveCount ++;
    }
// Checks bottom
    if(this.getCell(BottomRow, CurrentColumn) == 1){
      AliveCount ++;
    } 
// Checks bottom left
    if(this.getCell(BottomRow, LeftColumn) == 1){
      AliveCount ++;
    }
// Checks left
    if(this.getCell(CurrentRow, LeftColumn) == 1){
      AliveCount ++;
    }   
    return AliveCount;
  }//end getNeighbours
  
//simple iterate method as a settor
//the matrix were using once iterated on is counted towards the current generation
  public void iterate(){
    this.Matrix = nextGeneration();
    this.CurrentGeneration ++;
  }//end iterate
  
// start of main method
  public static void main(String[] args) {
    userInput = new Scanner(System.in);
    System.out.println("Enter game filename:");
    String fileName = userInput.next();
    
    System.out.println("How many iterations would you like to perform on this matrix?");
    int Iterations = userInput.nextInt();
    
    LifeGame game = new LifeGame(); // making a new lifegame object and calling it game
    
    game.loadState(fileName); //loading games state
    
    String continueOption = "no";
    
    do{ //Here is a do while loop to set the generations and to work with the iterate method
      game.setCurrentGeneration(0);
      game.setGenerations(Iterations);
      while(game.getCurrentGeneration() < game.getGenerations()){
        game.iterate();
      }
      System.out.println("# of iterations (<1 to stop):" + game.getCurrentGeneration());
      game.showGrid(); 
      System.out.println("Want to perform more iterations?");
      continueOption = userInput.next();
    }
    while(continueOption == "yes");
  }//end main
  
}//end class Lifegame