/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadwood;

import java.awt.Dimension;
import javax.swing.*;

/**
 *
 * @author Curveball
 */
public class BoardPane extends JLayeredPane {

    private static BoardPane instance = new BoardPane();
    private ImageIcon boardIcon;
    private JLabel boardLabel;
    private JLabel[] playerLabels;

    private BoardPane() {

    }

    public static BoardPane getInstance() {
        return instance;
    }

    public void init(int height, int width, ImageIcon boardIcon) {
        instance.boardIcon = boardIcon;
        instance.playerLabels = new JLabel[2];
        instance.setLayout(null);
        instance.setPreferredSize(new Dimension(900, 1200));
        instance.boardLabel = new JLabel();
        instance.boardLabel.setIcon(boardIcon);
        instance.boardLabel.setBounds(0, 0, boardIcon.getIconWidth(), boardIcon.getIconHeight());
        instance.add(boardLabel, new Integer(0));
        Demo();
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

            add(backlabel, new Integer(2));

            /// DEALING CARD FACES DEMO
            JLabel cardlabel = new JLabel();
            SceneCard card = deck.getCards().pop();
            String cardImage = card.getIconID();
            ImageIcon cIcon = new ImageIcon(getClass().getResource("/resources/" + cardImage));
            cardlabel.setIcon(cIcon);
            cardlabel.setBounds(board.getScene(i).getXCoordinates(), board.getScene(i).getYCoordinates(), cIcon.getIconWidth(), cIcon.getIconHeight());
            cardlabel.setOpaque(false);

            add(cardlabel, new Integer(1));

            /// SETTING SHOT COUNTERS
            for (int j = 0; j < board.getScene(i).getShotCountersXCoordinates().size(); j++) {
                JLabel shotCounterLabel = new JLabel();
                ImageIcon shotCounterIcon = new ImageIcon(getClass().getResource("/resources/shot.png"));
                shotCounterLabel.setIcon(shotCounterIcon);
                shotCounterLabel.setBounds(board.getScene(i).getShotCountersXCoordinates().get(j), board.getScene(i).getShotCountersYCoordinates().get(j), shotCounterIcon.getIconWidth(), shotCounterIcon.getIconHeight());
                shotCounterLabel.setOpaque(false);
                add(shotCounterLabel, new Integer(2));
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

        for (int i = 0; i < 2; i++) {

            JLabel playerLabel = new JLabel();
            if (i == 0) {
                ImageIcon playerIcon = new ImageIcon(getClass().getResource("/resources/r1.png"));
                playerLabel.setIcon(playerIcon);
                playerLabel.setBounds(board.getSpace(10).getXCoordinates(), board.getSpace(10).getYCoordinates(), playerIcon.getIconWidth(), playerIcon.getIconHeight());

            } else {
                ImageIcon playerIcon = new ImageIcon(getClass().getResource("/resources/b1.png"));
                playerLabel.setIcon(playerIcon);
                playerLabel.setBounds(board.getSpace(10).getXCoordinates() + 50, board.getSpace(10).getYCoordinates(), playerIcon.getIconWidth(), playerIcon.getIconHeight());
            }
            playerLabel.setOpaque(true);
            playerLabels[i] = playerLabel;

            add(playerLabels[i], new Integer(2));
        }
    }

}
