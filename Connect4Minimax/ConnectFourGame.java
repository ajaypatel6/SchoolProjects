/**
 * All elements of the game of connect 4
 */
public class ConnectFourGame {
  //Create the 6x7 board and the two players
    Board board = new Board(6, 7); 
    PlayerBase firstPlayer;
    PlayerBase secondPlayer;
    PlayerBase winner = null;
    
  //The two players playing
    public ConnectFourGame(PlayerBase firstPlayer, PlayerBase secondPlayer){
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }
    
  //Game Logic, Shows messages, prints boards
  //Keeps running until a winner/illegal board occurs
  //
  //Each players takes turns selecting columns, viewing the board and then playing until
  //the game is over.
    
  //Whoever goes first depends on what you want, this is changeable in the main, currently we go first.
    public void RunAGame(){
        int move = 1;
        System.out.println("Connect4");
        System.out.println("********");
        this.board.printCurrentBoard();
        
        //Any win breaks the loop
        while(true){
            System.out.println(firstPlayer.Name + "s move"); 
            System.out.println("********");
            firstPlayer.generateColumnMove(board, move, this.secondPlayer.getPlayerNumber());
            move += 1;
            this.board.printCurrentBoard();
            if(board.HasWon(firstPlayer.PlayerNumber)){
                winner = firstPlayer;
                break;
            }
            if(!this.board.isLegal()){
                System.out.println("Tie");
                return;
            }
            
            System.out.println(secondPlayer.Name + "s move");
            System.out.println("********");
            secondPlayer.generateColumnMove(board, move, this.firstPlayer.getPlayerNumber());
            move += 1;
            this.board.printCurrentBoard();
            if(board.HasWon(secondPlayer.PlayerNumber)){
                winner = secondPlayer;
                break;
            }
            if(!this.board.isLegal()){
                System.out.println("Tie");
                return;
            }
        }
        
        System.out.println(this.winner.Name + " has won");
    }
    
}
