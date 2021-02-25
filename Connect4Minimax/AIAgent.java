import java.util.Map;
import java.util.Map.Entry;
import javafx.util.Pair;

//Minimax agent with depth of 4
//This is the AI
public class AIAgent extends PlayerBase{
    int MAX_DEPTH = 4;
    
    public AIAgent(String name, int playerNumber){
        super(name, playerNumber);
    }
    
    @Override
    //requires another way to keep track of the game for the AI
    //USES minimax, to decide what move to do next
    public void generateColumnMove(Board board, int numberOfMove, int otherPlayerNumber) {
       int move;
       if(numberOfMove % 2 == 1){
            move = this.getMaxValue(0, board, PlayerNumber, otherPlayerNumber).getKey();
       } else {
           move = this.getMinValue(0, board, PlayerNumber, otherPlayerNumber).getKey();
       }
       if(move == -1){
           System.out.println("No move can't be done");
       }
       board.insertPiece(move, PlayerNumber);
    }
    
    //Uses the min value, so the AI wants to pick to see the worst placement for the user
    private Pair<Integer, Integer> getMinValue(int depth, Board board, int player, int opponent){ 
        int currentMove = -1;
        int currentEval = Integer.MAX_VALUE;
        
        for(int i = 0; i < board.Column; ++i){
            Board newBoard = board.makeACopy();
            boolean wasValid = newBoard.insertPiece(i + 1, player);
            if(!wasValid){
                continue;
            }
            int evaluationComparison = 0;
            if(depth == this.MAX_DEPTH){
                evaluationComparison = this.evaluateBoard(newBoard.getBoard(), opponent);
            } else {
                Pair<Integer, Integer> opponentMove = getMaxValue(depth + 1, newBoard, opponent, player);
                evaluationComparison = opponentMove.getValue();
            }
            if(evaluationComparison < currentEval){
                currentMove = i + 1;
                currentEval = evaluationComparison;
            }
        }
        return new Pair<Integer, Integer>(currentMove, currentEval);
    }
    
    //Uses max value, so the AI sees the max/best value for the user
    private Pair<Integer, Integer> getMaxValue(int depth, Board board, int player, int opponent){ 
        int currentMove = -1;
        int currentEval = Integer.MIN_VALUE;
        
        for(int i = 0; i < board.Column; ++i){
            Board newBoard = board.makeACopy();
            boolean wasValid = newBoard.insertPiece(i + 1, player);
            if(!wasValid){
                continue;
            }
            int evaluationComparison = 0;
            if(depth == this.MAX_DEPTH){ evaluationComparison = this.evaluateBoard(newBoard.getBoard(), player);
            } else {
                Pair<Integer, Integer> opponentMove = getMinValue(depth + 1, newBoard, opponent, player);
                evaluationComparison = opponentMove.getValue();
            }
            if(evaluationComparison > currentEval){
                currentMove = i + 1;
                currentEval = evaluationComparison;
            }
        }
        return new Pair<Integer, Integer>(currentMove, currentEval);
    }
    
    //Checks the board to see what the next suitable move is to win and create the winning sequences
    private int evaluateBoard(int[][] board, int playerNumber){
        int winSeq = SequencesCounter.CountSequences(board, playerNumber, 3);
        if(winSeq > 0){
            return Integer.MAX_VALUE - 1;
        }
        int twos = SequencesCounter.CountSequences(board, playerNumber, 2);
        return twos * 40;
    }
    
}

