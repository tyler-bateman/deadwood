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
    private ActionsPanel actionsPanel;
    private BoardPane boardPane;
    private InfoPanel infoPanel;   
    private JLabel[] playerLabels;
    private int playerNumber;

    public GameView(int numOfPlayers) {
        super("Deadwood");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        playerNumber = numOfPlayers;
        
        playerLabels = new JLabel[numOfPlayers];
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        ImageIcon boardIcon = new ImageIcon(getClass().getResource("/resources/board.jpg"));
        
        jpanel1 = new JPanel();
        jpanel1.setLayout(new BorderLayout());
        add(jpanel1);

        actionsPanel = new ActionsPanel(height,width,boardIcon);
        infoPanel = new InfoPanel(height,width,boardIcon);
        boardPane = new BoardPane(height,width,boardIcon);

        jpanel1.add(actionsPanel, BorderLayout.WEST);
        jpanel1.add(boardPane, BorderLayout.CENTER);
        jpanel1.add(infoPanel, BorderLayout.EAST);

        Demo();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(jpanel1);
        this.setMaximumSize(screenSize);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/DeadwoodIcon.PNG"));
        setIconImage(image);
        this.setVisible(true);
        //this.pack();

    }

    private void Demo() {
        Board board = Board.getInstance();
        Deck deck = Deck.getInstance();

        ////DEALING CARDBACKS DEMO
        for (int i = 0; i < board.getScenes().length; i++) {
            JLabel backlabel = new JLabel();
            ImageIcon backIcon = new ImageIcon(getClass().getResource("/resources/CardBack.jpg"));
            backlabel.setIcon(backIcon);
            backlabel.setBounds(board.getScene(i).getXCoordinates(), board.getScene(i).getYCoordinates(), backIcon.getIconWidth(), backIcon.getIconHeight());
            backlabel.setOpaque(false);

            boardPane.add(backlabel, new Integer(2));

            /// DEALING CARD FACES DEMO
            JLabel cardlabel = new JLabel();
            SceneCard card = deck.getCards().pop();
            String cardImage = card.getIconID();
            ImageIcon cIcon = new ImageIcon(getClass().getResource("/resources/" + cardImage));
            cardlabel.setIcon(cIcon);
            cardlabel.setBounds(board.getScene(i).getXCoordinates(), board.getScene(i).getYCoordinates(), cIcon.getIconWidth(), cIcon.getIconHeight());
            cardlabel.setOpaque(false);

            boardPane.add(cardlabel, new Integer(1));

            /// SETTING SHOT COUNTERS
            for (int j = 0; j < board.getScene(i).getShotCountersXCoordinates().size(); j++) {
                JLabel shotCounterLabel = new JLabel();
                ImageIcon shotCounterIcon = new ImageIcon(getClass().getResource("/resources/shot.png"));
                shotCounterLabel.setIcon(shotCounterIcon);
                shotCounterLabel.setBounds(board.getScene(i).getShotCountersXCoordinates().get(j), board.getScene(i).getShotCountersYCoordinates().get(j), shotCounterIcon.getIconWidth(), shotCounterIcon.getIconHeight());
                shotCounterLabel.setOpaque(false);
                boardPane.add(shotCounterLabel, new Integer(2));
            }

            /// MOVING PLAYER ICON TO ON CARD ROLES
            /*for (int m = 0; m < card.getRoles().size(); m++) {
                JLabel playerLabel1 = new JLabel();
                ImageIcon playerIcon1 = new ImageIcon(getClass().getResource("/resources/r1.png"));
                playerLabel1.setIcon(playerIcon1);
                playerLabel1.setBounds(card.getRoles().get(m).getXCoordinates(), card.getRoles().get(m).getYCoordinates(), playerIcon1.getIconWidth(), playerIcon1.getIconHeight());
                playerLabel1.setOpaque(true);
                cardlabel.add(playerLabel1);

            }*/
            /// MOVING PLAYER TO OFF CARD ROLE
            /*for (int j = 0; j < board.getScene(i).getOffCardRoles().size(); j++) {
                JLabel playerLabel = new JLabel();
                ImageIcon playerIcon = new ImageIcon(getClass().getResource("/resources/b1.png"));
                playerLabel.setIcon(playerIcon);
                playerLabel.setBounds(board.getScene(i).getOffCardRoles().get(j).getXCoordinates(), board.getScene(i).getOffCardRoles().get(j).getYCoordinates(), playerIcon.getIconWidth(), playerIcon.getIconHeight());
                playerLabel.setOpaque(false);
                boardPane.add(playerLabel, new Integer(2));
            }*/
        }

        for (int i = 0; i < playerNumber; i++) {

            JLabel playerLabel = new JLabel();
            ImageIcon playerIcon = new ImageIcon(getClass().getResource("/resources/r1.png"));
            playerLabel.setIcon(playerIcon);
            if (i == 0) {
                playerLabel.setBounds(board.getSpace(10).getXCoordinates(), board.getSpace(10).getYCoordinates(), playerIcon.getIconWidth(), playerIcon.getIconHeight());

            } else {
                playerLabel.setBounds(board.getSpace(10).getXCoordinates() + 50, board.getSpace(10).getYCoordinates(), playerIcon.getIconWidth(), playerIcon.getIconHeight());
            }
            playerLabels[i] = playerLabel;
            playerLabel.setOpaque(true);
            boardPane.add(playerLabel, new Integer(2));
        }
    }

}
