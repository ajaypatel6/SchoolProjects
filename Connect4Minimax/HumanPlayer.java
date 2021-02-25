import java.util.Scanner;

//the users class, allows to keep track of what the user has done

public class HumanPlayer extends PlayerBase{
    
    public HumanPlayer(String name, int playerNumber){
        super(name, playerNumber);
    }
    
    @Override
    public void generateColumnMove(Board board, int moveNumber, int otherPlayerNumber) {
       boolean isMoveValid = false;
       while(!isMoveValid){
           System.out.println(this.Name + ", type what column to drop your piece (1-7)");
           Scanner s = new Scanner(System.in);
           String input = s.nextLine();
           int column = Integer.parseInt(input);
           isMoveValid = board.insertPiece(column, this.PlayerNumber);
       }
    }
}
