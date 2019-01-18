public class TickTackToeLogic {
    private int gameField[][];
    final private String winMessageX;
    final private String winMessageO;
    
    final private int X_VAL = 1;
    final private int O_VAL = 10;
    
    final private int GAME_NOT_FINISHED = 0;
    private int lastPlayerAction;
    
    public TickTackToeLogic(){
       gameField = new int[3][3];
       lastPlayerAction = X_VAL;
       this.winMessageX = "X wins!";
       this.winMessageO = "O wins!";
    } 
    TickTackToeLogic abvg = new TickTackToeLogic();
    public TickTackToeLogic(String winMessageX, String winMessageO){
        gameField = new int[3][3];
        lastPlayerAction = X_VAL;
        this.winMessageX = winMessageX;
        this.winMessageO = winMessageO;
    }
    
    public int[][] getField(){
        return this.gameField;
    }
    
    private void setSquare(int x, int y, int symbolVal) throws paramValidityException {
        if(gameField[x][y] != 0){
            throw new paramValidityException("tick_tak_toe_logic: set_square: can not override symbols!");
        }
        gameField[x][y] = symbolVal;
    }
    
    private boolean checkResult(int symbolVal) throws paramValidityException{
        //rows
        if(gameField[0][0] + gameField[0][1] + gameField[0][2] == 3 * symbolVal){
            return true;
        }
        
        if(gameField[1][0] + gameField[1][1] + gameField[1][2] == 3 * symbolVal){
            return true;
        }
         
        if(gameField[2][0] + gameField[2][1] + gameField[2][2] == 3 * symbolVal){
            return true;
        }
        
        //columns
        if(gameField[0][0] + gameField[1][0] + gameField[2][0] == 3 * symbolVal){
            return true;
        }
        
        if(gameField[0][1] + gameField[1][1] + gameField[2][1] == 3 * symbolVal){
            return true;
        }
         
        if(gameField[0][2] + gameField[1][2] + gameField[2][2] == 3 * symbolVal){
            return true;
        }
        
        //diagonals
        //TODO
        if(gameField[0][0] + gameField[1][1] + gameField[2][2] == 3 * symbolVal){
            return true;
        }
        
        if(gameField[0][2] + gameField[1][1] + gameField[2][0] == 3 * symbolVal){
            return true;
        }
        return false;
    }
    

    public String playRound(int x, int y, int symbolVal) throws paramValidityException{
       if(symbolVal != X_VAL && symbolVal != O_VAL){
            throw new paramValidityException("tick_tack_toe_logic: set_square: invalid symbolVal value");
       }    
       if(gameField[x][y] != 0){
            throw new paramValidityException("tick_tack_toe_logic: set_square: overlapping value");
       }
       
       setSquare(x, y, symbolVal);
       for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
               System.out.printf("%5d ", gameField[i][j]);
        }
        System.out.println();
        }
       if(checkResult(symbolVal)){
           if(symbolVal == X_VAL){
               System.out.println("playRound: " + winMessageX);
               return winMessageX;
           }
           System.out.println("playRound: " + winMessageO);
           return winMessageO;
       }
       System.out.println("playRound: " + "still playing");
       return "-";
    }
    
    int getX(){
        return X_VAL;
    }
    
    int getO(){
        return O_VAL;
    }

    boolean lastPlayer(int symbolVal) throws paramValidityException {
       // if(symbolVal != X_VAL && symbolVal != O_VAL){
       //         throw new paramValidityException("tick_tack_toe_logic: last_player: invalid symbolVal value");
       // }
        
        if(lastPlayerAction != symbolVal){
            lastPlayerAction = symbolVal;
            return false;
        }
        return true;
    }

    public static class paramValidityException extends Exception {

        public paramValidityException() {
            
        }
        
         public paramValidityException(String message) {
             super(message);
         }
    }
}

