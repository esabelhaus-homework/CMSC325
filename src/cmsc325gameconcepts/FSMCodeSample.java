/*
AUTHOR: Associate Professor (Adjunct) Mark A. Wireman
COURSE: CMSC325, Intro to Game Development, UMUC
*/
package cmsc325gameconcepts;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FSMCodeSample extends JPanel 
                     implements ActionListener {
     JButton buttonGather;
     JButton buttonFlee;
     JButton buttonFlight;

     public FSMCodeSample() {
         super(new BorderLayout());
         buttonGather = new JButton("Gather Treasure");
         buttonGather.setPreferredSize(new Dimension(180, 30));
         add(buttonGather, BorderLayout.LINE_START);
         buttonGather.addActionListener(this);

         buttonFlee = new JButton("Flee");
         buttonFlee.setPreferredSize(new Dimension(180, 30));
         add(buttonFlee, BorderLayout.LINE_END);
         buttonFlee.addActionListener(this);

         buttonFlight = new JButton("Fight");
         buttonFlight.setPreferredSize(new Dimension(50, 30));
         add(buttonFlight, BorderLayout.PAGE_END);
         buttonFlight.addActionListener(this);
     }

     public static void infoBox(String infoMessage, String location)
    {
        JOptionPane.showMessageDialog(null, infoMessage, location, JOptionPane.INFORMATION_MESSAGE);
    }

     public void actionPerformed(ActionEvent e) {
         Toolkit.getDefaultToolkit().beep();
	switch (e.getActionCommand()) {
		case "Fight" :
			FSMCodeSample.infoBox("Monster Dead, go Gather the Treasure!", e.getActionCommand());
		   break;
		case "Flee" :
			FSMCodeSample.infoBox("2 Events Based on Game Theory Concepts (Probability) - No Monster which means Gather the Treasure! OR Cornered, get ready to Fight!", e.getActionCommand());
		   break;
		case "Gather Treasure":
			FSMCodeSample.infoBox("Monster in Sight, so time to Flee!", e.getActionCommand());
		   break;
	}
     }

     /**
      * Create the GUI and show it.  For thread safety,
      * this method should be invoked from the
      * event-dispatching thread.
      */
     public static void createAndShowGUI() {
         //Create and set up the window.
         JFrame frame = new JFrame("FSM Code Sample");
         frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

         //Create and set up the content pane.
         JComponent newContentPane = new FSMCodeSample();
         newContentPane.setOpaque(true); //content panes must be opaque
         frame.setContentPane(newContentPane);

         //Display the window.
         frame.pack();
         frame.setVisible(true);
     }

 }
