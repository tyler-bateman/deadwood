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
    private final int playerIconWidth = 40;
    private final int playerIconHeight = 40;

    private BoardPane() {

    }

    public static BoardPane getInstance() {
        return instance;
    }

    public void init(int height, int width, ImageIcon boardIcon, int playerNumber) {
        instance.boardIcon = boardIcon;
        instance.playerLabels = new JLabel[playerNumber];
        instance.setLayout(null);
        instance.setPreferredSize(new Dimension(900, 1200));
        instance.boardLabel = new JLabel();
        instance.boardLabel.setIcon(boardIcon);
        instance.boardLabel.setBounds(0, 0, boardIcon.getIconWidth(), boardIcon.getIconHeight());
        instance.add(boardLabel, new Integer(0));
        initializeNewDay();
    }

    private void initializeNewDay() {
        Board board = Board.getInstance();

        ////DEALING CARDBACKS DEMO
        for (int i = 0; i < board.getScenes().length; i++) {
            JLabel backlabel = new JLabel();
            ImageIcon backIcon = new ImageIcon(getClass().getResource("/resources/CardBack.jpg"));
            backlabel.setIcon(backIcon);
            backlabel.setBounds(board.getScene(i).getXCoordinates(), board.getScene(i).getYCoordinates(), backIcon.getIconWidth(), backIcon.getIconHeight());
            backlabel.setOpaque(false);

            add(backlabel, new Integer(1));

            /// DEALING CARD FACES DEMO
            JLabel cardlabel = new JLabel();
            SceneCard card = board.getScene(i).getCard();
            String cardImage = card.getIconID();
            ImageIcon cIcon = new ImageIcon(getClass().getResource("/resources/" + cardImage));
            cardlabel.setIcon(cIcon);
            cardlabel.setBounds(board.getScene(i).getXCoordinates(), board.getScene(i).getYCoordinates(), cIcon.getIconWidth(), cIcon.getIconHeight());
            cardlabel.setOpaque(false);

            add(cardlabel, new Integer(2));

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

        for (int i = 0; i < playerLabels.length; i++) {

            playerLabels[i] = new JLabel();
            playerLabels[i].setIcon(getIcon(i));
            playerLabels[i].setOpaque(true);
            if (i < (playerLabels.length / 2)) {
                playerLabels[i].setBounds(board.getSpace(10).getXCoordinates() + (i * 50), board.getSpace(10).getYCoordinates(), getIcon(i).getIconWidth(), getIcon(i).getIconHeight());

            } else {
                playerLabels[i].setBounds(board.getSpace(10).getXCoordinates() + ((i - (playerLabels.length / 2)) * 50), board.getSpace(10).getYCoordinates() + 50, getIcon(i).getIconWidth(), getIcon(i).getIconHeight());
            }

            add(playerLabels[i], new Integer(2));
        }
    }

    private ImageIcon getIcon(int index) {

        ImageIcon[] icons = new ImageIcon[8];
        icons[0] = new ImageIcon(getClass().getResource("/resources/b1.png"));
        icons[1] = new ImageIcon(getClass().getResource("/resources/r1.png"));
        icons[2] = new ImageIcon(getClass().getResource("/resources/g1.png"));
        icons[3] = new ImageIcon(getClass().getResource("/resources/p1.png"));
        icons[4] = new ImageIcon(getClass().getResource("/resources/c1.png"));
        icons[5] = new ImageIcon(getClass().getResource("/resources/o1.png"));
        icons[6] = new ImageIcon(getClass().getResource("/resources/v1.png"));
        icons[7] = new ImageIcon(getClass().getResource("/resources/w1.png"));

        return icons[index];
    }
    
    public void movePlayerLabelToScene(int adjacentSpaceID){
        Player active = TurnManager.getInstance().getActivePlayer();
        playerLabels[active.getID()].setBounds(Board.getInstance().getSpace(active.getLocation()).getAdjacentSpaces().get(adjacentSpaceID).getXCoordinates(), Board.getInstance().getSpace(active.getLocation()).getAdjacentSpaces().get(adjacentSpaceID).getYCoordinates(), playerIconWidth, playerIconHeight);
        add(playerLabels[active.getID()], new Integer(3));       
        Controller.getInstance().move(adjacentSpaceID);
        InfoPanel.getInstance().setPlayerInfoData(active);       
    }
    
    public void movePlayerLabelToExtraRole(int roleID){
        
    }

}
