
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * The thread for the client.
 */
public class ClientThread extends ChatThread {
    /**
     * Create a new client thread with the given socket.
     *
     * @param socket
     */
    public ClientThread(Socket socket) {
        super();
        this.socket = socket;
    }

    /**
     * The business end of the code.
     * Read and write from the socket using a thread.
     */
    @Override
    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String fromServer;

            while (socket.isConnected()) {
                if (in.ready()) {
                    if ((fromServer = in.readLine()) != null) {
                        if (!fromServer.equals(Chat.HEART_BEAT_CODE)) {
                            chat.log(fromServer);
                        }
                        heartBeat = 0;
                    } else {
                        break;
                    }
                } else {
                    try {
                        sleep(1000);
                        heartBeat++;
                        if (heartBeat == TICKS) {
                            out.println(Chat.HEART_BEAT_CODE);
                        }
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            chat.log("Disconnected from server");
        } catch (IOException e) {
            chat.log("Disconnected");
        }
    }

    public void closeConnection() throws IOException {
        in.close();
        out.close();
        socket.close();

    }

    public void writeMessage(String s) {
        out.println(s);
    }
}
