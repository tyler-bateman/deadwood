/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadwood;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.Border;

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
    private JLabel[] dollarLabels;
    private JLabel[] creditLabels;
    private JLabel[] cardBackLabels;
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
        instance.cardBackLabels = new JLabel[10];
        instance.shotCounterLabels = new JLabel[22];
        instance.dollarLabels = new JLabel[5];
        instance.creditLabels = new JLabel[5];
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

    private ImageIcon getIcon(int playerID, int rank) {
        char color;
        switch(playerID) {
            case 0:
                color = 'b';
                break;
            case 1:
                color = 'r';
                break;
            case 2:
                color = 'g';
                break;
            case 3:
                color = 'p';
                break;
            case 4:
                color = 'c';
                break;
            case 5:
                color = 'o';
            case 6:
                color = 'v';
            default:
                color = 'w';
        }
        return new ImageIcon(getClass().getResource("/resources/" + color + rank +".png"));
    }

    public void movePlayerLabel(int playerID, int x, int y) {

        playerLabels[playerID].setBounds(x, y, playerIconWidth, playerIconHeight);
        add(playerLabels[playerID], new Integer(3));

//        InfoPanel.getInstance().setPlayerInfoData(active);
//        InfoPanel.getInstance().setUpdateTextArea("Your have moved !\n");
    }

    /*public void movePlayerLabelToExtraRole(Role role) {
        Player active = TurnManager.getInstance().getActivePlayer();
        playerLabels[active.getID()].setBounds(role.getXCoordinates() + 3, role.getYCoordinates() + 3, playerIconWidth, playerIconHeight);
        add(playerLabels[active.getID()], new Integer(3));
        //Controller.getInstance().takeRole(role);
        InfoPanel.getInstance().setPlayerInfoData(active);
        InfoPanel.getInstance().setUpdateTextArea("You took an extra role !\n");
    }*/
    public void movePlayerToStarringRole(int playerID, int labelID, int x, int y) {
        //Player active = TurnManager.getInstance().getActivePlayer();
        playerLabels[playerID].setBounds(x, y, playerIconWidth, playerIconHeight);
        //cardLabels[active.getLocation()].add(playerLabels[active.getID()]);
        cardLabels[labelID].add(playerLabels[playerID]);
        add(cardLabels[labelID], new Integer(3));
        //Controller.getInstance().takeRole(role);
        //InfoPanel.getInstance().setPlayerInfoData(active);
        //InfoPanel.getInstance().setUpdateTextArea("You took a starring role !\n");
    }

    public void removeShotCounter(int index) {
        shotCounterLabels[index].setVisible(false);
    }

    public void setCardBackInView(int labelID, int x, int y) {
        JLabel backlabel = new JLabel();
        ImageIcon backIcon = new ImageIcon(getClass().getResource("/resources/CardBack.jpg"));
        backlabel.setIcon(backIcon);
        backlabel.setBounds(x, y, backIcon.getIconWidth(), backIcon.getIconHeight());
        backlabel.setOpaque(false);
        add(backlabel, new Integer(1));
        cardBackLabels[labelID] = backlabel;
    }

    public void setCardFaceUpInView(int labelID, String iconID, int x, int y) {
        remove(cardBackLabels[labelID]);
        cardBackLabels[labelID].setVisible(false);
        cardLabels[labelID] = new JLabel();
        //SceneCard card = scene.getCard();
        //String cardImage = card.getIconID();
        ImageIcon cIcon = new ImageIcon(getClass().getResource("/resources/" + iconID));
        cardLabels[labelID].setIcon(cIcon);
        cardLabels[labelID].setBounds(x, y, cIcon.getIconWidth(), cIcon.getIconHeight());
        cardLabels[labelID].setOpaque(false);
        add(cardLabels[labelID], new Integer(2));
    }
    
    public void removeCard(int labelID) {
        if(cardLabels[labelID] != null) {
            System.out.println("Remove card");
            cardLabels[labelID].setVisible(false);
            remove(cardLabels[labelID]);
        }
        
        
    }

    public JLabel shotCounter(int x, int y) {
        JLabel shotCounterLabel = new JLabel();
        ImageIcon shotCounterIcon = new ImageIcon(getClass().getResource("/resources/shot.png"));
        shotCounterLabel.setIcon(shotCounterIcon);
        shotCounterLabel.setBounds(x, y, shotCounterIcon.getIconWidth(), shotCounterIcon.getIconHeight());
        shotCounterLabel.setOpaque(false);
        add(shotCounterLabel, new Integer(2));
        return shotCounterLabel;
    }

    public void initializeAllShots(int[] xCoords, int[] yCoords) {
        System.out.println("Initializing shots...");
        for (int i = 0; i < xCoords.length; i++) {
            shotCounterLabels[i] = shotCounter(xCoords[i], yCoords[i]);
        }
    }

    public void redrawShots(int startingIndex, int num, int total) {
        System.out.println("Redrawing shots: " + num);
        for (int i = 0; i < total; i++) {
            shotCounterLabels[i + startingIndex].setVisible(false);
        }
        //shotCounterLabels[startingIndex].setVisible(true);
        for (int i = 0; i < num; i++) {
            shotCounterLabels[i + startingIndex].setVisible(true);
        }
    }

    public void makePlayerLabels() {
        for (int i = 0; i < playerLabels.length; i++) {
            playerLabels[i] = new JLabel();
            playerLabels[i].setIcon(getIcon(i, 1));
            playerLabels[i].setOpaque(false);
            add(playerLabels[i], new Integer(2));
        }

    }

    public void displayUpgradeLabels(int currRank, int maxDollarRank, int maxCreditRank) {
        System.out.println("displayUpgradeLabels " + currRank + " " + maxDollarRank + " " + maxCreditRank);
        int rankIndex = currRank - 1;
        int dollarIndex = 1;
        int creditIndex = 1;
        for (int i = 0; i < dollarLabels.length; i++) {
            Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
            System.out.println(i);
            if(rankIndex >= i && i <= maxDollarRank) {
                System.out.println("Drawing upgrade label...");
                dollarLabels[i] = new JLabel();
                
                dollarLabels[i].setBorder(border);
                dollarLabels[i].setBounds(CastingOffice.getInstance().getDollarCoordinates().get(i * 2), CastingOffice.getInstance().getDollarCoordinates().get(dollarIndex), 19, 19);
                dollarLabels[i].setOpaque(false);
                dollarLabels[i].addMouseListener(new MouseAdapter() {
                    public void mouseEntered(MouseEvent e){
                        JLabel lab = (JLabel) e.getSource();
                        Border border = BorderFactory.createLineBorder(Color.RED, 2);
                        lab.setBorder(border);
                    }
                    public void mouseReleased(MouseEvent e) {
                        //TODO
                    }
                    public void mouseExited(MouseEvent e){
                        JLabel lab = (JLabel) e.getSource();
                        Border border = BorderFactory.createLineBorder(Color.GREEN, 1);
                        lab.setBorder(border);
                    }

                });
                add(dollarLabels[i], new Integer(2));
            }
            dollarIndex = dollarIndex + 2;
            
            if(rankIndex >= i && i <= maxCreditRank) {
                creditLabels[i] = new JLabel();
                creditLabels[i].setBorder(border);
                creditLabels[i].setBounds(CastingOffice.getInstance().getCreditCoordinates().get(i * 2), CastingOffice.getInstance().getCreditCoordinates().get(creditIndex), 19, 19);
                creditLabels[i].setOpaque(false);
                creditLabels[i].addMouseListener(new MouseAdapter() {
                    public void mouseReleased(MouseEvent e) {
                        //TODO
                    }
                    public void mouseEntered(MouseEvent e){
                        JLabel lab = (JLabel) e.getSource();
                        Border border = BorderFactory.createLineBorder(Color.RED, 2);
                        lab.setBorder(border);
                    }
                    public void mouseExited(MouseEvent e){
                        JLabel lab = (JLabel) e.getSource();
                        Border border = BorderFactory.createLineBorder(Color.GREEN, 1);
                        lab.setBorder(border);
                    }
                });
                add(creditLabels[i], new Integer(2));
            }
            creditIndex = creditIndex + 2;

            
            

        }
    }
    
    public void deleteUpgradeLabels() {
        for(int i=0; i<dollarLabels.length; i++){
            dollarLabels[i].setVisible(false);
            remove(dollarLabels[i]);
            creditLabels[i].setVisible(false);
            remove(creditLabels[i]);
        }
    }

}
