package cmsc325gameconcepts;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Mark
 */
public class PrisonersDilemma extends JPanel
        implements ActionListener {

    JButton buttonP1;
    JButton buttonP2;
    JTextArea howto;
    /**
     * Prisoner's Dilemma program.
     */
    Strategy player1;
    Strategy player2;
    int T = 7;
    int R = 5;
    int P = 3;
    int S = 1;
    int player1Payoff;
    int player2Payoff;
    int player1Move;
    int player2Move;

    public PrisonersDilemma(Strategy p1, Strategy p2) {

        super(new BorderLayout());
        howto = new JTextArea("Instructions");
        howto.setText("");
        add(howto, BorderLayout.CENTER);
        buttonP1 = new JButton(p1.name);
        buttonP1.setPreferredSize(new Dimension(180, 30));
        add(buttonP1, BorderLayout.LINE_START);
        buttonP1.addActionListener(this);

        buttonP2 = new JButton(p2.name);
        buttonP2.setPreferredSize(new Dimension(180, 30));
        add(buttonP2, BorderLayout.LINE_END);
        buttonP2.addActionListener(this);

        this.player1 = p1;
        this.player2 = p2;

    }  /* PrisonersDilemma */


    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event-dispatching thread.
     */
    public static void createAndShowGUI(Strategy p1, Strategy p2) {
        //Create and set up the window.
        JFrame frame = new JFrame("Prisoner's Dilemma Code Sample");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new PrisonersDilemma(p1, p2);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        playPD();

        PrisonersDilemma.infoBox("Player1 Payoff=" + player1Payoff + " : Player2 Payoff=" + player2Payoff, e.getActionCommand());
    }

    public static void infoBox(String infoMessage, String location) {
        JOptionPane.showMessageDialog(null, infoMessage, location, JOptionPane.INFORMATION_MESSAGE);
    }

    public int playPD() {
        // 0 = defect, 1 = cooperate
        player1Move = player1.nextMove();
        player2Move = player2.nextMove();

        player1.saveMyMove(player1Move);
        player2.saveMyMove(player2Move);
        player1.saveOpponentMove(player2Move);
        player2.saveOpponentMove(player1Move);

        if (player1Move == 0 && player2Move == 0) {
            player1Payoff = P;
            player2Payoff = P;
        } else if (player1Move == 0 && player2Move == 1) {
            player1Payoff = T;
            player2Payoff = S;
        } else if (player1Move == 1 && player2Move == 0) {
            player1Payoff = S;
            player2Payoff = T;
        } else if (player1Move == 1 && player2Move == 1) {
            player1Payoff = R;
            player2Payoff = R;
        }
        return 0;
    }  /* playPD */


    public int getPlayer1Move() {
        return player1Move;
    }

    public int getPlayer2Move() {
        return player2Move;
    }

    public int getPlayer1Payoff() {
        return player1Payoff;
    }

    public int getPlayer2Payoff() {
        return player2Payoff;
    }
}  /* class PrisonersDilemmd */
