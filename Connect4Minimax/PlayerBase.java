//What a player will consists off, just name and 1/2 for #

abstract class PlayerBase{
  
    String Name;
    int PlayerNumber;
    
    public PlayerBase(String name, int playerNumber){
        Name = name;
        PlayerNumber = playerNumber;
    }
    
    public String getName(){
        return Name;
    }
    
    public int getPlayerNumber(){
        return PlayerNumber;
    };
    //
    public abstract void  generateColumnMove(Board board, int moveNumber, int otherPlayerNumber);
    
} 