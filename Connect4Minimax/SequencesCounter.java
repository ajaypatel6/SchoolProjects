//Checks the four different ways to win

//Horizontally XXXX
//Vertically
//Positive Diagonal
//Negative Diagonal

//Each check reviews the board for the player and compares to the amount of pieces for specific directions
//Sequence size's goal is to hit 4 to win

public class SequencesCounter {
    
    private static int countHorizontally(int[][] matrix, int playerNumber, int sequenceSize){
      int result = 0;
      for(int i = 0; i < matrix.length; ++i){
          int count = 0;
          for(int j = 0; j < matrix[i].length; ++j){
              if(matrix[i][j] == playerNumber){
                  count += 1;
                  if(count == sequenceSize){
                    result += 1;
                  }
              } else {
                  if(count > sequenceSize){
                      result -= 1;
                  }
                  count = 0;
              }
          }
      }
      return result;
  }
  
    private static int countVertically(int[][] matrix, int playerNumber, int sequenceSize){
      int result = 0;
      for(int i = 0; i < matrix[0].length; ++i){
          int count = 0;
          for(int j = 0; j < matrix.length; ++j){
              if(matrix[j][i] == playerNumber){
                  count += 1;
                  if(count == sequenceSize){
                    result += 1;
                  }
              } else {
                  if(count > sequenceSize){
                      result -= 1;
                  }
                  count = 0;
              }
          }
      }
      return result;
    }
    
    private static int countPositiveDiagonal(int[][] matrix, int playerNumber, int sequenceSize){
        int result = 0;
        for( int k = 0 ; k <= matrix.length + matrix[0].length -2 ; k++ ) {
            int count = 0;
            for(int j = 0 ; j <= k ; j++ ) {
                int i = k - j;
                if( i < matrix.length && j < matrix[0].length){
                    if(matrix[i][j] == playerNumber){
                        count += 1;
                        if(count == sequenceSize){
                            result += 1;
                        }
                    } else {
                        if(count > sequenceSize){
                            result -= 1;
                        }
                        count = 0;
                    }
                }
            }
        }
        return result;
    }
    
    private static int countNegativeDiagonal(int[][] matrix, int playerNumber, int sequenceSize){
        int result = 0;
        for( int k = matrix.length + matrix[0].length -2 ; k >= 0 ; k-- ) {
            int count = 0;
            for(int j = 0 ; j <= k ; j++ ) {
                int i = k + j;
                if( i < matrix.length && j < matrix[0].length){
                    if(matrix[i][j] == playerNumber){
                        count += 1;
                        if(count == sequenceSize){
                            result += 1;
                        }
                    } else {
                        if(count > sequenceSize){
                            result -= 1;
                        }
                        count = 0;
                    }
                }
            }
        }
        return result;
    }
    
    //
    public static int CountSequences(int[][] matrix, int identifier, int sizeOfSequence){
        return countVertically(matrix, identifier, sizeOfSequence) 
                + countHorizontally(matrix, identifier, sizeOfSequence)
                + countPositiveDiagonal(matrix, identifier, sizeOfSequence)
                + countNegativeDiagonal(matrix, identifier, sizeOfSequence);
    }
}
