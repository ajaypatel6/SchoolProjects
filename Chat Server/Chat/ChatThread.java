import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatThread extends Thread {
    /**
     * Will be used to write message to the server.
     */
    protected PrintWriter out;
    /**
     * Read messages from the server
     */
    protected BufferedReader in;
    /**
     * The socket
     */
    protected Socket socket;
    /**
     * The chat instance used mostly for logging.
     */
    protected Chat chat;

    /**
     * A heartbeat will be used to check the connection from time to time
     */
    protected int heartBeat = 0;

    /**
     * Heartbeat delay.
     * Approximately 1 minute.
     */
    protected final int TICKS = 10;

    /**
     * @return the chat
     */
    public Chat getChat() {
        return chat;
    }

    /**
     * @param chat the chat to set
     */
    public void setChat(Chat chat) {
        this.chat = chat;
    }


}
