import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import utility.Constants;

/**
 * A multi threaded chat server implementation.
 * <p>
 * Each new connection gets a thread of it's own.
 */
public class ChatServer extends Chat {
 private static final String FILENAME = System.getProperty("user.home") 
   + System.getProperty("file.separator") + "Downloads"+ System.getProperty("file.separator") + "logs.txt" ;
   
    // The default port number
    //static int portNumber = 14001;
    /**
     * A number which will be assigned to each connecting client.
     * It's incremented after allocation.
     */
    static int clientId = 1;

    ServerSocket serverSocket;

    /**
     * Construct a new Server
     */
    public ChatServer() {
        super("Chat Server");
        buildGui();
    }
     
    /*
     * @param portNumber Integer
     * The portNumber a Server socket listens actively for connection
     * @return void
     */
    public void listenForConnectionOnNewThred(int portNumber) {
   Thread run = new Thread() {
            public void run() {
                listen(portNumber);
            }
        };
        run.start();
 }
    
    /*
     * 
     */
    private void setChangePortEventListener(JButton button, int portNumber, JTextField textField, String title) {
        button.addActionListener(new ActionListener() {
           
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(frame, 
                  textField, title, 
                  JOptionPane.OK_CANCEL_OPTION);
                
                if (result == JOptionPane.OK_OPTION) {
                    System.out.println("The port number was changed to " + textField.getText());
                    int changedPortNumber = Integer.parseInt(textField.getText());
                    if(button.getText().equals("Start")) {
                      toogleButtonEnable(button, false);
                    }
                    listenForConnectionOnNewThred(changedPortNumber);
                }
            }
        });
    }
    
    /*
     * 
     */
    private JTextField createTextField(String text) {
      return new JTextField(text);
    }
    
    /*
     * 
     */
    private void addPanelLayout(JPanel jpanel, JButton ...button) {
      for(JButton jbutton: button) {
       jpanel.add(jbutton);
      }
    }
    
    /*
     * 
     */
    private String convertIntToString(int number) {
      return String.valueOf(number);
    }
    
    /*
     * 
     */
    private void toogleButtonEnable(JButton button, boolean toogle) {
     button.setEnabled(toogle);
    }
    
    /*
     * 
     */
    private void toggleButtonVisible(JButton button, boolean toogle) {
      button.setVisible(toogle);
    }
    
    /*
     * 
     */
    private void addConentToWindowFrame(JScrollPane scrollPane, JPanel jPanel) {
      frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(jPanel, BorderLayout.SOUTH);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /*
     * 
     */
    private void registerWindowListener() {
      frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                shutDown();
                super.windowClosing(e);
            }
        });
    }
    
    /*
     * 
     */
    private void registerStopButtonListener(JButton eventButton, JButton otherButton ) {
      eventButton.addActionListener(new ActionListener() {       
                @Override
                public void actionPerformed(ActionEvent e) {
                    Thread run = new Thread() {
                        public void run() {
                          toggleButtonVisible(otherButton, true);
                            shutDown();
                        }
                    };
                    run.start();
                    toogleButtonEnable(otherButton, true);
                    toogleButtonEnable(eventButton, true);
                }
       });
    }

    /**
     * Build the GUI
     */
    private void buildGui() {
        JButton btnStop = new JButton("Stop");
        JButton btnStart = new JButton("Start");
        JButton changePort = new JButton("Change Port");
        JPanel jp = new JPanel( new FlowLayout());
        addPanelLayout(jp, btnStop, btnStart, changePort);
        int portNumber = Constants.PORT_NUMBER;
        JTextField portNumberChangeField = createTextField(convertIntToString(portNumber));
        String popUpTitle = "Select new port number";
        setChangePortEventListener(changePort, 
          portNumber, portNumberChangeField, popUpTitle);
        toogleButtonEnable(btnStart, false);   
        doc = pane.getDocument();
        JScrollPane scroll = new JScrollPane(pane);
        addConentToWindowFrame(scroll, jp);
        registerWindowListener();
        registerStopButtonListener(btnStop, btnStart);
        setChangePortEventListener(btnStart, portNumber, portNumberChangeField, popUpTitle);
        frame.setVisible(true);
    }
 
    /**
     * Listen for incoming connections.
     * <p>
     * Each new connection will result in a new thread being created and IO
     * tasks will be assigned to that thread.
     */
    private void listen(int portNumber) {
        boolean listening = true;
        log("Starting a new instance of the chat server listing on port " + portNumber);

        /*
         * We create a separate thread to listen to console input.
         *
         * It would be overkill to put this on a class of it's own. Since the run method
         * performs only a tirival task.
         *
         * So we use an annonmous inner class.
         */
        Thread consoleListener = new Thread() {

            @Override
            public void run() {

                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
                String s;

                try {
                    while ((s = stdIn.readLine()) != null) {
                        if (s.trim().toLowerCase().equals("exit")) {
                            shutDown();
                            System.exit(0);
                        }
                    }
                } catch (IOException e) {
                    log("Interruped");
                }
            }
        };

        consoleListener.start();

        /*
         * This is where we start listening on the server socket.
         */

        try {
            serverSocket = new ServerSocket(portNumber);
            while (listening) {
                synchronized (this) {
                    /*
                     * A synchrnoized block because we want the assignment of client
                     * numbers to be atomic.
                     */
                    ServerThread thrd = new ServerThread(serverSocket.accept());
                    log("New client " + clientId + " has connected");
                    thrd.setClientId(clientId++);
                    thrd.setChat(this);
                    thrd.start();
                }
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
        }
    }

    /**
     * Shuts down the server.
     */
    private void shutDown() {
        /*
         * First disconnects all the clients. Then closes the socket.
         */
        log("Shutting down the server");
        ServerThread.shutDown();
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws IOException {
        if (args.length == 2) {
            if (args[1].equals("-csp")) {
                try {
                    Constants.PORT_NUMBER = Integer.parseInt(args[2]);
                } catch (NumberFormatException nex) {
                    System.out.println("Incorrect arguments. Starting server on port " + Constants.PORT_NUMBER);
                }
            } else {
                System.out.println("Unrecognized argument.");
            }
        }
        ChatServer server = new ChatServer();
        server.listen(Constants.PORT_NUMBER);
    }
}