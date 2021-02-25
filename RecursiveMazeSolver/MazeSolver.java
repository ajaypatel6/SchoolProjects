import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.io.File;

/*COSC 1P03
 *ASSIGNMENT #4
 *Username: ap14jl
 *Student #: 5660055
 *
 *This class coordinates the loading and parsing of a 'maze' datafile, the creation of
 *a Maze object  and the invocation of the Maze's solve() method  
 * 
 *It also handles basic IO and tells the Maze object to output the maze status and
 *solution
 *
 *Using Stacks and LinkedList
 *
 *Three sample mazes are included.
 *maze1.txt solves easily
 *maze2.txt is larger, but also solves
 *maze3.txt is unsolveable
 */

public class MazeSolver {
  
  //declaring a few variables to be used, including the actual maze, a boolean that checks if a spot has been visited, the 
  // directions in a stack and integers for the height and width of the maze
  private char[][] charmap, solution;
  private boolean[][] visited;
  private Stack<Character> directions;
  private int height, width;
  
  int startRow = -1;//Starting row
  int startCol = -1;//Starting column
  
  //constructing a stack 
  public MazeSolver(){
    directions = new Stack<Character>();
  }
  
  //loading the maze with scanner, must be in the same file folder
  public void loadMaze(){
    
    String filename = " ", newLine;    
    Scanner console = new Scanner(System.in); 
    Scanner file;
    
    System.out.print("Enter maze filename: ");
    filename = console.nextLine();
    
    //try catch that checks if the file works properly
    try {
      file = new Scanner(new File(System.getProperty("user.dir") + "/" + filename));
      int arr[] = new int[2];
      int k =0;
      
      // processing the maze
      while(file.hasNextLine()){
        newLine = file.nextLine();
        if(newLine.trim().length()>0){
          newLine = newLine.trim(); // gets rid of whitespaces at the end of a string
          // System.out.println(newLine);
          String vals[] = newLine.split("\\s");
          for(int i = 0; i < vals.length; i++){
            if(vals[i].length()>0){
              arr[k++] = Integer.parseInt(vals[i]);
            }
          }
          break;
        }
      }
      
      height = arr[0];
      width = arr[1];      
      // System.out.println(height + ":" + width);
      
      charmap = new char[height][width];
      visited = new boolean[height][width];
      
      int i = 0;
      while(file.hasNextLine()){
        newLine = file.nextLine();
        if(newLine.trim().length()>0){
          for(int j = 0; j < width; j++){
            char ch = newLine.charAt(j);
            charmap[i][j] = ch;
            visited[i][j] = false;
            // System.out.println(i + ":" + j + " - " + ch);
            if(ch == 'S'){
              startRow = i;
              startCol = j;
            }
          }
          i++;
        }
      }
      
      System.out.println("Start at " + startRow + "," + startCol + ".");
      System.out.println("File transcription complete!\n");
      System.out.println("Initial State:");
      printMap(charmap);
      System.out.println("");
      solve(); 
    }
    catch (Exception exn){
      System.out.println(exn.getMessage().toString());
      System.out.println("File transcription problem. Exiting now.\n");
      System.exit(0);
    }
  }
  
  // prints the actual maze of char's
  void printMap(char[][] charmap) {
    for (int i = 0; i < height; i++) {
      for(int j = 0; j < width; j++){
        System.out.print(charmap[i][j]);
      }
      System.out.println();
    }
  }
  
  // a checks if the spot has been travelled through/to
  boolean isSafe(char charmap[][], int x, int y) {
    if((x >= 0 && x < height) && (y >= 0 && y < width) && charmap[x][y] != 'X' && visited[x][y] == false){
      return true;
    }
    return false;
  }
  
  // recursive algorithm that accepts a variety of stuff that proccesses the actual navigation/traversal through the maze
  // is based on going south and east then west/north, since it makes the most sense because the navigation is mostly
  // going east and/or down, or the starting points typically want to go down first
  private boolean recursiveSolve(char charmap[][], int x, int y, char solution[][], Stack<Character> directions) {
    // if (x,y is goal) return true
    if(charmap[x][y]=='E'){
      solution[x][y] =  'O';
      return true;
    }
    
    // Check if maze[x][y] is possible to go to
    // places an asterisk in a spot if the proposed solution is going to lead to the correct one
    
    if(isSafe(charmap, x, y) == true){
      visited[x][y] = true;
      // System.out.println("Check " + x + ":" + y);      
      solution[x][y] = '*';
      
      // Check South, then East, then North, then West
      if (recursiveSolve(charmap,x+1, y, solution, directions) == true){
        directions.push('S');
        return true;
      }
      
      if (recursiveSolve(charmap,x, y+1, solution, directions) == true){
        directions.push('E');
        return true;
      }
      
      if (recursiveSolve(charmap, x-1, y, solution, directions) == true){
        directions.push('N');
        return true;
      }
      
      if (recursiveSolve(charmap, x, y-1, solution, directions) == true){
        directions.push('W');
        return true;
      }
      
      if(charmap[x][y]=='S'){
        solution[x][y] = 'S';
      }
      else solution[x][y] = ' ';
      
      //when there are no more directions to go, start to pop the stack
      if(!directions.isEmpty()){
        directions.pop();
      } 
    }   
    return false; //compiling placeholder
  }
  
  // solve that invokes recursive solve, and it can work
  // 
  private void solve(){
    solution = new char[height][width];
    
    for (int i = 0; i < height; i++) {
      for(int j = 0; j < width; j++){
        solution[i][j] = charmap[i][j];
      }
    }
  
    // if the recursive algorithm cant work, the user is identified it doesnt work
    if(recursiveSolve(charmap, startRow, startCol, solution, directions) == false){
      System.out.println("Oops! No solution found!");
      return;
    }
   
    //places a '#' at the starting spot
    solution[startRow][startCol] = '#';
    System.out.println("Final Maze:");
    printMap(solution);
    
    // the directions as an arraylist 
    List<Character> list = new ArrayList<Character>(directions);
    // reversing the list because it is pushed into as a stack (FILO)
    Collections.reverse(list);
    
    // printing the solution path in reverse order because its a stack
    System.out.print("Solution Path: ");
    
    for (int i = 0; i < list.size(); i++){
      if(i!=(list.size()-1)){
        System.out.print(list.get(i) + ", ");
      }
      else System.out.println(list.get(i));
    }    
  }
  
  public static void main(String args[]) {new MazeSolver().loadMaze();}
}