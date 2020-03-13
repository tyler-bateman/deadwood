/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author Curveball
 */
public class GameView extends JFrame /*implements ActionListener*/ {

    private JPanel jpanel1;
    private ActionsPanel actionsPanel = ActionsPanel.getInstance();
    private BoardPane boardPane = BoardPane.getInstance();
    private InfoPanel infoPanel = InfoPanel.getInstance();
    private ImageIcon boardIcon;
    private int playerNumber;

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
    
    public void importantMessage(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.PLAIN_MESSAGE);
    }
    
    public void gameOver(String message) {
        importantMessage(message, "Game Over!");
        this.dispose();
        System.exit(0);
    }
    
    public ActionsPanel getActionsPanel() {
        return actionsPanel;
    }
    
    public BoardPane getBoardPane() {
        return boardPane;
    }
    
    public InfoPanel getInfoPanel() {
        return infoPanel;
    }




}
