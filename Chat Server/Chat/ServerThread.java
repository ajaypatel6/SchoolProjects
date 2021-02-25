
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ServerThread extends ChatThread {
	private static final Logger LOGGER = Logger.getLogger(
		    Thread.currentThread().getStackTrace()[0].getClassName() );
	FileHandler fh;  
	private static final String FILENAME = System.getProperty("user.home") 
			+ System.getProperty("file.separator") + "Downloads"+ System.getProperty("file.separator") + "log.txt" ;

    /**
     * Static list of all connected threads.
     * <p>
     * We need this to send broadcasts and also when the server shuts down. Note that
     * this is a synchronized list so that modifications and retrievals can be done
     * in a thread safe manner.
     */
    private static final List<ServerThread> threads = Collections.synchronizedList(new ArrayList<>());
    /**
     * The ide of the cclient
     */
    private int clientId = 0;

    /**
     * Initialize a new ServerThread with the given socket.
     *
     * @param socket the server socket.
     */
    public ServerThread(Socket socket) {
        super();
        this.socket = socket;
        threads.add(this);
    }

    /**
     * Interact with the client in this thread.
     */
    public void run() {

        try {
            // get the reader and writer
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));

            String inputLine;
            out.println("Welcome to your favourite chat server");


            while (socket.isConnected()) {
                if (in.ready()) {
                    if ((inputLine = in.readLine()) == null) {
                        // Most likely the client has disconnected.
                        socket.close();
                        break;
                    } else {
                        if (!inputLine.equals(Chat.HEART_BEAT_CODE)) {
                            broadCast("Client " + clientId + " : " + inputLine);
                        }
                        heartBeat = 0;
                    }
                } else {
                    try {
                        sleep(1000);

                        heartBeat++;
                        if (heartBeat == TICKS) {
                            out.println(Chat.HEART_BEAT_CODE);
                            out.flush();
//							chat.log("Sending heart beat to " + clientId);
                            heartBeat = 0;
                            if ((inputLine = in.readLine()) == null) {
                                // Most likely the client has disconnected.
                                socket.close();
                                break;
                            } else {
                                if (!inputLine.equals(Chat.HEART_BEAT_CODE)) {
                                    broadCast("Client " + clientId + " : " + inputLine);
                                }
                                heartBeat = 0;
                            }
                        }
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }

        } catch (IOException e) {
            chat.log("IO Exception for client " + clientId);
            // e.printStackTrace();
        }
        chat.log("Client " + clientId + " disconnected");
    }

    
    private boolean testRegex(String word) {
    		String patternRegex = "(client:)(\\d+)";	
		Pattern pattern = Pattern.compile(patternRegex);
		Matcher matcher = pattern.matcher(word);
		if(matcher.find()) {
			return true;
		} else {
			
			return false;
		}
    }
    
    private void broadCast(String message) {
    	try {
    		fh = new FileHandler(FILENAME); 
    		LOGGER.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
       	   LOGGER.info("Broadcasting " + message); 
    	} catch (Exception e) {
    		
    	}
    	 LOGGER.info("Broadcasting " + message);
        System.out.println("Broadcasting " + message);
        for(ServerThread serverThread : threads) {
        	    if(testRegex(message)) {
        	    		String client = String.valueOf(serverThread.clientId);
        	    		serverThread.out.println("client is "+ client);
        	    		if(testRegex(client.trim())) {
        	    			serverThread.out.println("I found a client here " + client);
        	    			serverThread.out.println(message);
        	    		}
        	    } else {
        		serverThread.out.println(message);
        	    }
        }
       /* for (ServerThread serverThread : threads) {
        		String pattern = "(client:)(\\d+)";
        		if (message.contains(pattern)) {
        			String clientId = message.split(pattern)[0].split(":")[1];
        			if(serverThread.clientId == Integer.parseInt(clientId)) {
        				System.out.println("We have a client Id");
        				serverThread.out.print(message.replaceFirst(pattern, "From"));
        			}
        		} else {
            serverThread.out.println(message);
        		}
        } */

    }

    /**
     * Set the client id.
     *
     * @param i
     */
    public void setClientId(int i) {
        clientId = i;
    }

    /**
     * The server is shutting down. Disconnect all sockets.
     */
    public static void shutDown() {
        for (ServerThread serverThread : threads) {
            try {
                serverThread.out.println("Server is shutting down");
                serverThread.in.close();
                serverThread.out.close();
                serverThread.socket.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}