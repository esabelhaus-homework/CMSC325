/*
@AUTHOR: Associate Professor (Adjunct) Mark A. Wireman
@COURSE: CMSC325, Intro to Game Development, UMUC
@CREDITTO: Michael C. Semeniuk
*/
package cmsc325gameconcepts;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;


/**
 *
 * @author Mark
 */
public class CMSC325GameConcepts extends JPanel 
                     implements ActionListener {

    JButton buttonFSM;
     JTextArea txtInstructions;
     JTextArea txtSeedMoves;
     JRadioButton rdoRandom;
     JRadioButton rdoProbability;
     JRadioButton rdoEach;
     
    public CMSC325GameConcepts() {
        super(new BorderLayout());
          txtInstructions = new JTextArea("Instructions");
          txtInstructions.setPreferredSize(new Dimension(180, 100));
          txtInstructions.setText("This is a working example of the\n" +
                  "Finite Statement Machine (FSM) and\n " +
                  "the Prisoner's Dilemma using either\n " +
                  "a Random or Probability method\n "+ 
                  "to determine the Prisoner's moves.");
          add(txtInstructions,BorderLayout.PAGE_START);
         buttonFSM = new JButton("FSM");
         buttonFSM.setPreferredSize(new Dimension(180, 30));
         add(buttonFSM, BorderLayout.PAGE_END);
         buttonFSM.addActionListener(this);

         rdoRandom = new JRadioButton("Random");
         add(rdoRandom,BorderLayout.LINE_START);
         rdoRandom.addActionListener(this);
         
         rdoProbability = new JRadioButton("Probability");
         add(rdoProbability,BorderLayout.CENTER);
         rdoProbability.addActionListener(this);
         
         rdoEach = new JRadioButton("Both");
         add(rdoEach,BorderLayout.LINE_END);
         rdoEach.addActionListener(this);
    }
    
       /**
      * Create the GUI and show it.  For thread safety,
      * this method should be invoked from the
      * event-dispatching thread.
      */
     public static void createAndShowGUI() {
         //Create and set up the window.
         JFrame frame = new JFrame("Finite State Machine and Prisoner's Dilemma Examples");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
         //Create and set up the content pane.
         JComponent newContentPane = new CMSC325GameConcepts();
         newContentPane.setOpaque(true); //content panes must be opaque
         frame.setContentPane(newContentPane);

         //Display the window.
         frame.pack();
         frame.setVisible(true);
     }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
             javax.swing.SwingUtilities.invokeLater(new Runnable() {
                 public void run() {
                     CMSC325GameConcepts.createAndShowGUI();
                 }
             });
    }
    
    public void actionPerformed(ActionEvent e) {
         Toolkit.getDefaultToolkit().beep();
         javax.swing.SwingUtilities.invokeLater(new Runnable() {
                 public void run() {
                        switch (e.getActionCommand()) {
                                case "FSM" :
                                        FSMCodeSample.createAndShowGUI();
                                   break;
                                case "Random" :
                                        StrategyRandom r1 = new StrategyRandom();
                                    r1.name = "RandomPrisoner1";
                                     StrategyRandom r2 = new StrategyRandom();
                                     r2.name = "RandomPrisoner2";
                                     PrisonersDilemma.createAndShowGUI(r1, r2);
                                   break;
                                case "Probability":
                                    StrategyProbabilistic p1 = new StrategyProbabilistic();
                                    p1.setMoves("0011001111010101100111101101101001110101110000010101110");
                                     p1.name = "ProbPrisoner1";
                                     //StrategyRandom p2 = new StrategyRandom();
                                     StrategyProbabilistic p2 = new StrategyProbabilistic();
                                     p2.setMoves("0011101111010101110111101101111001110101110000010101110");
                                     p2.name = "ProbPrisoner2";
                                     PrisonersDilemma.createAndShowGUI(p1, p2);
                                   break;
                                case "Both":
                                    StrategyProbabilistic b1 = new StrategyProbabilistic();
                                    b1.setMoves("0011001111010101100111101101101001110101110000010101110");
                                     b1.name = "ProbPrisoner1";
                                     StrategyRandom b2 = new StrategyRandom();
                                     b2.name = "RandomPrisoner2";
                                     PrisonersDilemma.createAndShowGUI(b1, b2);
                                    break;
                        }
                 }
             });
    }
}