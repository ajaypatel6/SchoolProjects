import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;

public class Chat {
  
  protected JFrame frame;
  
  protected JEditorPane pane = new JEditorPane();
  
  protected Document doc;
  
  public static final String HEART_BEAT_CODE = "___HEART__BEAT___";
  
  public Chat(String title) {
    frame = new JFrame(title);
  }

  
  
  protected void log(String message) {

    if (doc != null) {
      /*
       * The communications between the server and the clients happen in various threads.
       * these different threads are not allowed to update the GUI. So we use the
       * invokeLater here to make sure that the update happens in the Event Dispath Thread
       * (EDT)
             */
      EventQueue.invokeLater(new Runnable() {
        @Override
        public void run() {
          try {
            doc.insertString(doc.getLength(), message, null);
                        doc.insertString(doc.getLength(), "\n", null);
          } catch (BadLocationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
        
      });

    }
    System.out.println(message);
  }
}
