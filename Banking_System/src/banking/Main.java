package banking;


import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main {

static loginform lg ;
		   @SuppressWarnings("serial")
		   private static void createAndShowGui() {
		      final JLabel label = new JLabel();
		      int timerDelay = 500;
		      new Timer(timerDelay , new ActionListener() {
		         int timeLeft =1;

		         @Override
		         public void actionPerformed(ActionEvent e) {
		            if (timeLeft > 0) {
		               label.setText("Opening Application Please Wait");
		               timeLeft--;
		            } else {
		               ((Timer)e.getSource()).stop();
		               Window win = SwingUtilities.getWindowAncestor(label);
		               win.setVisible(false);
		 		lg = new loginform();
		            }
		         }
		      }){{setInitialDelay(0);}}.start();

		      JOptionPane.showMessageDialog(null, label);

		   }

		   public static void main(String[] args) {
		      SwingUtilities.invokeLater(new Runnable() {
		         public void run() {
		            createAndShowGui();
		         }
		      });

	}
} 
