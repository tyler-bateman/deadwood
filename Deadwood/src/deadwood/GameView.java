
package deadwood;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;

/**
 * GameView has the JFrame that holds all of the game components user for user
 * interactions and information and label display
 *
 * @author Curveball
 */
public class GameView extends JFrame {

    private JPanel jpanel1;
    private ActionsPanel actionsPanel = ActionsPanel.getInstance();
    private BoardPane boardPane = BoardPane.getInstance();
    private InfoPanel infoPanel = InfoPanel.getInstance();
    private ImageIcon boardIcon;
    private int playerNumber;

    /**
     * Constructor for GameView
     *
     * @param numOfPlayers number of players in this game of Deadwood
     */
    public GameView(int numOfPlayers) {
        super("Deadwood");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        playerNumber = numOfPlayers;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        boardIcon = new ImageIcon(getClass().getResource("/resources/board.jpg"));

        jpanel1 = new JPanel();
        jpanel1.setLayout(new BorderLayout());
        add(jpanel1);

        actionsPanel.init(height, width, boardIcon);
        infoPanel.init(height, width, boardIcon);
        boardPane.init(height, width, boardIcon, playerNumber);

        jpanel1.add(actionsPanel, BorderLayout.WEST);
        jpanel1.add(boardPane, BorderLayout.CENTER);
        jpanel1.add(infoPanel, BorderLayout.EAST);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(jpanel1);
        this.setMaximumSize(screenSize);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/DeadwoodIcon.PNG"));
        setIconImage(image);
        this.setVisible(true);

    }

    /**
     * sends a dialog box to the user
     *
     * @param message message to be displayed
     * @param title title of the frame
     */
    public void importantMessage(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * sends a dialog box to the user once the game is over and terminates the
     * program on OK button click
     *
     * @param message message to be displayed in the dialog box
     */
    public void gameOver(String message) {
        importantMessage(message, "Game Over!");
        this.dispose();
        System.exit(0);
    }

    /**
     * gets the ActionsPanel
     *
     * @return actionsPanel
     */
    public ActionsPanel getActionsPanel() {
        return actionsPanel;
    }

    /**
     * gets the BoardPane
     *
     * @return boardPane
     */
    public BoardPane getBoardPane() {
        return boardPane;
    }

    /**
     * gets the infoPanel
     *
     * @return infoPanel
     */
    public InfoPanel getInfoPanel() {
        return infoPanel;
    }

}
