import java.io.*;
import java.net.*;
import java.util.Arrays;

public class TickTackToeServer 
{
    private static final int ECHO_PORT_NUMB = 1234;
    
    public static void main(String[] args) throws IOException, TickTackToeLogic.paramValidityException 
    {
        TickTackToeLogic t3l = new TickTackToeLogic();
        String gameState = "-";
        
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        
        try
        {
            serverSocket = new ServerSocket(ECHO_PORT_NUMB);
        }
        catch (IOException excpt)
        {
            System.err.println("Cannot listen on port: " + ECHO_PORT_NUMB);
            System.err.println("Exception: " + excpt.getMessage());
            System.exit(1);
        }
        while(gameState.equals("-")){
          
            
            //int[] intArray = getPlayerInput(t3l);
            try
            {
                clientSocket = serverSocket.accept();
            }
            catch (IOException excpt)
            {
                System.err.println("Accept failed.");
                System.err.println("Exception: " + excpt.getMessage());
                System.exit(1);
            }
            
            System.out.println("Accepted connection from client.");
                
            // open I/O streams
            int[] intArray = null;
            out = new PrintWriter(clientSocket.getOutputStream(), true); 
            String inp ="";
            try
            {
                in = new BufferedReader(
                        new InputStreamReader(
                            clientSocket.getInputStream()
                        )//InputStreamReader
                    );//BufferedReader
                                             
                //read exactly one row.             

                    //out.println("Jello!");
                inp = in.readLine();
                if(inp == null){
                    continue;
                }
                String[] strArray = inp.split(",");
                intArray = new int[strArray.length];
                for(int i = 0; i < strArray.length; i++) {
                    intArray[i] = Integer.parseInt(strArray[i]);
                }
            }
            catch (IOException excpt)
            {
                    System.err.println("Exception: " + excpt.getMessage());
                    System.out.println("Closing connection with client.");
                
                    // free resources
                    out.close();
                    in.close();
                    System.exit(1);
            }
          
            System.out.println("Closing connection with client.");
                     
            System.out.println(Arrays.toString(intArray));
            
            try {
                System.out.println(intArray[intArray.length -1]);
                if(t3l.lastPlayer(intArray[intArray.length -1])){
                    System.out.println("Duplicate player turn!!");
                    out.println(Arrays.deepToString(t3l.getField()));
                    out.println(gameState);
                    continue;
                }
                gameState = t3l.playRound(intArray[0], intArray[1], intArray[intArray.length -1]);
            } catch (TickTackToeLogic.paramValidityException ex) {
               
            }                       

            out.println(Arrays.deepToString(t3l.getField()));
            out.println(gameState);
            System.out.println("Game state: " + gameState);
            out.close();
            in.close();   
        }
        System.out.println(gameState);
    }//main


//Used when there was no network
       private static int[] getPlayerInput(TickTackToeLogic t3l) throws IOException, TickTackToeLogic.paramValidityException {
            BufferedReader stdIn =      // standard input
                                    new BufferedReader(
                                        new InputStreamReader(System.in)
                                    );
            String userInput = stdIn.readLine();
            String[] strArray = userInput.split(",");
            int[] intArray = new int[strArray.length];
            if(strArray.length < 3){
                throw new TickTackToeLogic.paramValidityException("tick_tack_toe_logic: get_player_input: not enough arguments in call");
            }           
            
            for(int i = 0; i < strArray.length; i++) {
                intArray[i] = Integer.parseInt(strArray[i]);
            }
            
            if(intArray[0] > 2 || intArray[0] < 0){
                throw new TickTackToeLogic.paramValidityException("tick_tack_toe_logic: get_player_input: invalid X value");
            }
            
            if(intArray[1] > 2 || intArray[1] < 0){
                throw new TickTackToeLogic.paramValidityException("tick_tack_toe_logic: get_player_input: invalid Y value");
            }
            
            if(intArray[2] !=  t3l.getX() && intArray[2] != t3l.getO()){
                throw new TickTackToeLogic.paramValidityException("tick_tack_toe_logic: get_player_input: invalid symbolVal11 value");
            }
            
            return intArray;
    }
} 