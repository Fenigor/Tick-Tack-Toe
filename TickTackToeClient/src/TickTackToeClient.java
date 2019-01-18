
import java.io.*;
import java.net.*;

public class TickTackToeClient 
{
    private static final String HOST_NAME = "127.0.0.1";
    private static final int ECHO_PORT_NUMB = 1234;
    
    public static void main(String[] args) throws IOException 
    {
        while(true){
        String gameState = "";
        Socket echoSocket = null;   // socket to establis the connection
        PrintWriter out = null;     // output to the socket
        BufferedReader in = null;   // input from the socket
        
        try
        {
            echoSocket = new Socket(HOST_NAME, ECHO_PORT_NUMB);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(
                        echoSocket.getInputStream()
                    )//InputStreamReader
                );//BufferedReader
        }
        catch (UnknownHostException excpt)
        {
            System.err.println("Unknown host: " + HOST_NAME);
            System.err.println("Exception: " + excpt.getMessage());
            System.exit(1);
        }
        catch (IOException excpt)
        {
            System.err.println("Cannot get I/O for " + HOST_NAME);
            System.err.println("Exception: " + excpt.getMessage());
            System.exit(1);
        }
        
        BufferedReader stdIn =      // standard input
                new BufferedReader(
                    new InputStreamReader(System.in)
                );

        String userInput = "";
        while(userInput == null || userInput.equals("")){
            userInput = stdIn.readLine();  
        }

        System.out.println("sending: " + userInput);
        out.println(userInput);
        in = new BufferedReader(
                        new InputStreamReader(
                            echoSocket.getInputStream()
                        )//InputStreamReader
                    );//BufferedReader
        String inp = in.readLine();
        if(inp != null){
        System.out.println(inp);
        }
        inp = in.readLine();
        if(inp != null){
            System.out.println(inp);
        }

        if(!inp.equals("-")){
            break;
        }
        // free resources
        out.close();
        in.close();
        }
    }//main
}//class EchoClient