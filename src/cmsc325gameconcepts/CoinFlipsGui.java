/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cmsc325gameconcepts;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author emsabel
 */
public class CoinFlipsGui extends JPanel implements ActionListener {

    JButton heads;
    JButton tails;
    JTextArea howto;
    Strategy flipper;
    String flips = "";
    String probs = "";

    CoinFlipsGui(Strategy s1) {
        // How to text
        howto = new JTextArea("Instructions");
        howto.setText("Pick between HEADS or TAILS to guess what the coin flip will be");
        add(howto, BorderLayout.CENTER);


        heads = new JButton("HEADS");
        heads.setPreferredSize(new Dimension(180, 30));
        add(heads, BorderLayout.LINE_START);

        tails = new JButton("TAILS");
        tails.setPreferredSize(new Dimension(180, 30));
        add(tails, BorderLayout.LINE_END);

        this.flipper = s1;

    }

    CoinFlipsGui(Strategy s1, String probabilityData) {
        this(s1);
        this.probs = probabilityData;
    }

    public static void createAndShowGUI(Strategy s1) {
        //Create and set up the window.
        JFrame frame = new JFrame("Flipping Coins");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new CoinFlipsGui(s1);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void createAndShowGUI(Strategy s1, String data) {
        //Create and set up the window.
        JFrame frame = new JFrame("Flipping Coins");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new CoinFlipsGui(s1, data);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
