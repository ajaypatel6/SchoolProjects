import javax.swing.*;

import utility.Constants;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Chat client with a GUI and also supports text input from the console.
 * <p>
 * When using the GUI, use Ctrl+Enter to send the message. This allows multiline
 * chat messages.
 * <p>
 * When using the control the console client. Just enter works.
 */
public class ChatClient extends Chat {
  /**
   * The hostname to connect to changable with a command line argument.
   */
  static String hostName = Constants.HOST_NAME;
  /**
   * The port number.
   */
  static int portNumber = Constants.PORT_NUMBER;
  
  static boolean running = true;
  
    /**
     * Construct a chat client.
     */
  JTextArea area = new JTextArea();
  
  
  
  JButton btnSend = new JButton("Send");
  JButton btnDisconnect = new JButton("Disconnect");
  JButton btnConnect = new JButton("Connect");
    JButton changeHost = new JButton("Change Host");
    
    /**
     * The client thread. This interacts with the server. Freeing up the main
     * thread.
     */
    ClientThread thread;

    /**
     * The socket used to communicate with the server.
     */
    Socket sock;
    
    /**
     * Constructs a new chat client.
     */
    public ChatClient() {
      super("Chat Client");
    }
    
    private String getAreaText() {
      return area.getText(); 
    }
    
    
    public void connect(String hostName, int portNumber, boolean gui) {
      BufferedReader stdIn = null;
      try {
        sock = new Socket(hostName, portNumber);
        thread = new ClientThread(sock);
        thread.start();
        thread.setChat(this);
        
        stdIn = new BufferedReader(new InputStreamReader(System.in));
        if(gui)
          toggleConnect(false, true);
        
        while (sock.isConnected()) {
          try {
            String fromUser;
            fromUser = stdIn.readLine();
            if (fromUser != null) {
              log("Client: " + fromUser);
              thread.writeMessage(fromUser);
            }
          } catch (IOException e) {
            log("Disconnected");
          }
        }
        
      } catch (UnknownHostException e) {
        System.err.println("Don't know about host " + hostName);
      } catch (IOException e) {
        System.err.println("Couldn't get I/O for the connection to " + hostName);
      }
      
      log("Could not connect to " + hostName + " on " + portNumber);
      if(gui)
        toggleConnect(true, false);
    }
    
    
    private void setChangeHostEvent(JButton button) {
      button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          JTextField txtHost = new JTextField(Constants.HOST_NAME);
          JTextField txtPort = new JTextField(Constants.PORT_NUMBER);
          
          JPanel cmp = new JPanel();
          cmp.setMaximumSize(new Dimension(200, 200));
          cmp.setLayout(new GridLayout(2, 2));
          cmp.add(new JLabel("Hostname"));
          cmp.add(txtHost);
          cmp.add(new JLabel("Port"));
          cmp.add(txtPort);
          
          int result = JOptionPane.showConfirmDialog(frame, cmp, "Please enter connection settings",
                                                     JOptionPane.OK_CANCEL_OPTION);
          
          if (result == JOptionPane.OK_OPTION) {
            hostName = txtHost.getText().trim();
            try {
              portNumber = Integer.parseInt(txtPort.getText().trim());
              Thread tc = new Thread() {
                public void run() {
                  connect(hostName, portNumber, true);
                }
                
                ;
              };
              
              tc.start();
              
            } catch (NumberFormatException nex) {
              log("Invalid port number");
            }
          }
        }
      });
    }
    
    /**
     * Change the enabled disabled states of connect/disconnect buttons.
     */
    private void toggleConnect(boolean connect, boolean disconnect) {
      EventQueue.invokeLater(new Runnable() {
        /**
         * enable disable buttons on the EDT
         */
        @Override
        public void run() {
          btnConnect.setEnabled(connect);
          btnDisconnect.setEnabled(disconnect);
          btnSend.setEnabled(!connect);
        }
      });
      
    }
    
    /**
     * Build the graphical user interface.
     */
    private void buildGui() {
      doc = pane.getDocument();
        pane.setEditable(false);
        
        JScrollPane scroll1 = new JScrollPane(pane);
        
        JPanel jp = new JPanel();
        JPanel jpSub = new JPanel();
        jp.setLayout(new BorderLayout());
        JScrollPane scroll2 = new JScrollPane(area);
        jpSub.setLayout(new FlowLayout());
        
        jpSub.add(btnDisconnect);
        jpSub.add(btnConnect);
        jpSub.add(btnSend);
        setChangeHostEvent(changeHost);
        
        jp.add(scroll2, BorderLayout.CENTER);
        jp.add(jpSub, BorderLayout.SOUTH);

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              sendMessage(getAreaText(), true);
            }
        });
        
        
        btnDisconnect.setEnabled(false);
        

        btnDisconnect.addActionListener(new ActionListener() {
            /**
             * Handle disconnect button clicks.
             */
          @Override
            public void actionPerformed(ActionEvent e) {
                disconnect();
                toggleConnect(true, false);
            }
        });

        
        btnConnect.addActionListener(new ActionListener() {
          /**
             * Handle connect button clicks.
             *
             * Display a dialog providing th user with an opportunity to change the
             * port and host
             */
            @Override
            public void actionPerformed(ActionEvent e) {
              JTextField txtHost = new JTextField("localhost");
                JTextField txtPort = new JTextField("14001");
                
                JPanel cmp = new JPanel();
                cmp.setLayout(new GridLayout(2, 2));
                cmp.add(new JLabel("Hostname"));
                cmp.add(txtHost);
                cmp.add(new JLabel("Port"));
                cmp.add(txtPort);
                
                int result = JOptionPane.showConfirmDialog(frame, cmp, "Please enter connection settings",
                                                           JOptionPane.OK_CANCEL_OPTION);
                
                if (result == JOptionPane.OK_OPTION) {
                  hostName = txtHost.getText().trim();
                    try {
                      portNumber = Integer.parseInt(txtPort.getText().trim());
                        Thread tc = new Thread() {
                          public void run() {
                              connect(Constants.HOST_NAME, Constants.PORT_NUMBER, true);
                            };
                        };
                        
                        tc.start();
                        
                    } catch (NumberFormatException nex) {
                      log("Invalid port number");
                    }
                }
            }
        });
        
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(scroll1, BorderLayout.CENTER);
        frame.getContentPane().add(jp, BorderLayout.SOUTH);
        
        frame.setSize(450, 400);
        frame.setVisible(true);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
          @Override
            public void windowClosing(WindowEvent e) {
              disconnect();
                super.windowClosing(e);
            }

        });
        area.setMinimumSize(new Dimension(400, 40));
        area.setPreferredSize(new Dimension(400, 40));
        
        area.addKeyListener(new KeyListener() {
          /**
             * Not implemented
             */
            @Override
            public void keyTyped(KeyEvent e) {
              
            }
            
            /**
             * Not implmented
             */
            @Override
            public void keyReleased(KeyEvent e) {
            }

            /**
             * We allow the message to be sent on ctrl+enter
             */
            @Override
            public void keyPressed(KeyEvent e) {
              if (e.isControlDown()) {
                  if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                      sendMessage(getAreaText(), true);
                    }
                }
            }
        });
    }
    
    /**
     * Sends a message on button click or keyboard shortCut
     */
    private void sendMessage(String text, boolean gui) {
      if (thread != null) {
          thread.writeMessage(text);
            if(gui)
              area.setText("");
        }
    }
    
    /**
     * Disconnect before closing and on disconnect button click.
     */
    private void disconnect() {
      log("Disconnecting....");
        try {
          if (thread != null) {
              thread.closeConnection();
                thread.join();
            }
        } catch (InterruptedException | IOException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws IOException {
      
      if (args.length > 0) {
          if (args[0].equals("-cca")) {
              hostName = args[1];
            }
            if (args[2].equals("-cca")) {
              hostName = args[3];
            }
            if (args[0].equals("-ccp")) {
              portNumber = Integer.parseInt(args[1]);
            }
            if (args[2].equals("-ccp")) {
              portNumber = Integer.parseInt(args[3]);
            }
        }
        
        ChatClient client = new ChatClient();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Please Enter 1 for Gui and 2 for console chat");
        String input = scanner.next();
        if(input.equals("1") || input.equals("2")) {         
          if (input.equals("1")) {
            startUpApplication(client, true, scanner);
          }
          if (input.equals("2")) {
            startUpApplication(client, false, scanner);
          }
        }         
    }
    
    private static void startUpApplication(ChatClient client, boolean gui, Scanner scanner) {
      if (gui) {
           SwingUtilities.invokeLater(new Runnable() {
         public void run() {
               client.buildGui();
             }
       });
       client.connect(hostName, portNumber, true);
         } else {
           System.out.println("Enter Ctrl c to quit or Send a message to the user");
          client.connect(hostName, portNumber, false);
          while(running) {
             String input = scanner.nextLine();
            client.sendMessage(input, false);   
           }
          
         }
         
    }
}
