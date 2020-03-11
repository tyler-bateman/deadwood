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
    private JLabel[] cardLabels;
    private JLabel[] playerLabels;
    private JLabel[] shotCounterLabels;
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
        instance.cardLabels = new JLabel[10];
        instance.shotCounterLabels = new JLabel[1000];
        instance.setLayout(null);
        instance.setPreferredSize(new Dimension(900, 1200));
        instance.boardLabel = new JLabel();
        instance.boardLabel.setIcon(boardIcon);
        instance.boardLabel.setBounds(0, 0, boardIcon.getIconWidth(), boardIcon.getIconHeight());
        instance.add(boardLabel, new Integer(0));
        //initializeNewDay();
    }

    private void initializeNewDay() {
        Board board = Board.getInstance();

        ////DEALING CARDBACKS DEMO
        for (int i = 0; i < board.getScenes().length; i++) {
            /*JLabel backlabel = new JLabel();
            ImageIcon backIcon = new ImageIcon(getClass().getResource("/resources/CardBack.jpg"));
            backlabel.setIcon(backIcon);
            backlabel.setBounds(board.getScene(i).getXCoordinates(), board.getScene(i).getYCoordinates(), backIcon.getIconWidth(), backIcon.getIconHeight());
            backlabel.setOpaque(false);
            add(backlabel, new Integer(1));*/

            /// DEALING CARD FACES DEMO
            /*cardLabels[i] = new JLabel();
            SceneCard card = board.getScene(i).getCard();
            String cardImage = card.getIconID();
            ImageIcon cIcon = new ImageIcon(getClass().getResource("/resources/" + cardImage));
            cardLabels[i].setIcon(cIcon);
            cardLabels[i].setBounds(board.getScene(i).getXCoordinates(), board.getScene(i).getYCoordinates(), cIcon.getIconWidth(), cIcon.getIconHeight());
            cardLabels[i].setOpaque(false);

            add(cardLabels[i], new Integer(2));*/
            /// SETTING SHOT COUNTERS
            /*for (int j = 0; j < board.getScene(i).getShotCountersXCoordinates().size(); j++) {
                shotCounterLabels[(i + 1) * (j + 1)] = new JLabel();
                ImageIcon shotCounterIcon = new ImageIcon(getClass().getResource("/resources/shot.png"));
                shotCounterLabels[(i + 1) * (j + 1)].setIcon(shotCounterIcon);
                shotCounterLabels[(i + 1) * (j + 1)].setBounds(board.getScene(i).getShotCountersXCoordinates().get(j), board.getScene(i).getShotCountersYCoordinates().get(j), shotCounterIcon.getIconWidth(), shotCounterIcon.getIconHeight());
                shotCounterLabels[(i + 1) * (j + 1)].setOpaque(false);
                add(shotCounterLabels[(i + 1) * (j + 1)], new Integer(2));
            }*/
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

        /*for (int i = 0; i < playerLabels.length; i++) {

            playerLabels[i] = new JLabel();
            playerLabels[i].setIcon(getIcon(i));
            playerLabels[i].setOpaque(false);
            if (i < (playerLabels.length / 2)) {
                playerLabels[i].setBounds(board.getSpace(10).getXCoordinates() + (i * 50), board.getSpace(10).getYCoordinates(), getIcon(i).getIconWidth(), getIcon(i).getIconHeight());

            } else {
                playerLabels[i].setBounds(board.getSpace(10).getXCoordinates() + ((i - (playerLabels.length / 2)) * 50), board.getSpace(10).getYCoordinates() + 50, getIcon(i).getIconWidth(), getIcon(i).getIconHeight());
            }

            add(playerLabels[i], new Integer(4));
        }*/
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

    public void movePlayerLabel(int playerID, int x, int y) {
        playerLabels[playerID].setBounds(x, y, playerIconWidth, playerIconHeight);
        System.out.println(x);
        System.out.println(y);
        add(playerLabels[playerID], new Integer(3));
        
        
//        InfoPanel.getInstance().setPlayerInfoData(active);
//        InfoPanel.getInstance().setUpdateTextArea("Your have moved !\n");
    }
/*
    public void movePlayerLabelToExtraRole(Role role) {
        Player active = TurnManager.getInstance().getActivePlayer();
        playerLabels[active.getID()].setBounds(role.getXCoordinates() + 3, role.getYCoordinates() + 3, playerIconWidth, playerIconHeight);
        add(playerLabels[active.getID()], new Integer(3));
        //Controller.getInstance().takeRole(role);
        InfoPanel.getInstance().setPlayerInfoData(active);
        InfoPanel.getInstance().setUpdateTextArea("You took an extra role !\n");
    }

    public void movePlayerToStarringRole(Role role) {
        Player active = TurnManager.getInstance().getActivePlayer();
        playerLabels[active.getID()].setBounds(role.getXCoordinates(), role.getYCoordinates(), playerIconWidth, playerIconHeight);
        cardLabels[active.getLocation()].add(playerLabels[active.getID()]);
        add(cardLabels[active.getLocation()], new Integer(3));
        //Controller.getInstance().takeRole(role);
        InfoPanel.getInstance().setPlayerInfoData(active);
        InfoPanel.getInstance().setUpdateTextArea("You took a starring role !\n");
    }
    */
    public void removeShotCounter(int index) {
        shotCounterLabels[index].setVisible(false);
    }

    public void setCardBackInView(int x, int y) {
        JLabel backlabel = new JLabel();
        ImageIcon backIcon = new ImageIcon(getClass().getResource("/resources/CardBack.jpg"));
        backlabel.setIcon(backIcon);
        backlabel.setBounds(x, y, backIcon.getIconWidth(), backIcon.getIconHeight());
        backlabel.setOpaque(false);
        add(backlabel, new Integer(1));
    }

    public void setCardFaceUpInView(int labelID, String iconID, int x, int y) {
        cardLabels[labelID] = new JLabel();
        //SceneCard card = scene.getCard();
        //String cardImage = card.getIconID();
        ImageIcon cIcon = new ImageIcon(getClass().getResource("/resources/" + iconID));
        cardLabels[labelID].setIcon(cIcon);
        cardLabels[labelID].setBounds(x, y, cIcon.getIconWidth(), cIcon.getIconHeight());
        cardLabels[labelID].setOpaque(false);

        add(cardLabels[labelID], new Integer(2));
    }

    public void setShotCountersInView(int x, int y) {
        JLabel shotCounterLabel = new JLabel();
        ImageIcon shotCounterIcon = new ImageIcon(getClass().getResource("/resources/shot.png"));
        shotCounterLabel.setIcon(shotCounterIcon);
        shotCounterLabel.setBounds(x, y, shotCounterIcon.getIconWidth(), shotCounterIcon.getIconHeight());
        shotCounterLabel.setOpaque(false);
        add(shotCounterLabel, new Integer(2));
    }

    public void positionPlayersInTrailer(int x, int y) {
        for (int i = 0; i < playerLabels.length; i++) {
            playerLabels[i] = new JLabel();
            playerLabels[i].setIcon(getIcon(i));
            playerLabels[i].setOpaque(false);
            if (i < (playerLabels.length / 2)) {
                playerLabels[i].setBounds(x + (i * 50), y, getIcon(i).getIconWidth(), getIcon(i).getIconHeight());

            } else {
                playerLabels[i].setBounds(x + ((i - (playerLabels.length / 2)) * 50), y + 50, getIcon(i).getIconWidth(), getIcon(i).getIconHeight());
            }

            add(playerLabels[i], new Integer(2));
        }
    }

}
