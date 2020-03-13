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
    }

    private ImageIcon getIcon(int playerID, int rank) {
        char color;
        switch (playerID) {
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
                break;
            case 6:
                color = 'v';
                break;
            default:
                color = 'w';
        }
        return new ImageIcon(getClass().getResource("/resources/" + color + rank + ".png"));
    }

    public void movePlayerLabel(int playerID, int x, int y) {

        playerLabels[playerID].setBounds(x, y, playerIconWidth, playerIconHeight);
        add(playerLabels[playerID], new Integer(3));

    }

    public void movePlayerToStarringRole(int playerID, int labelID, int x, int y) {
        playerLabels[playerID].setBounds(x, y, playerIconWidth, playerIconHeight);
        cardLabels[labelID].add(playerLabels[playerID]);
        add(cardLabels[labelID], new Integer(3));
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

    public void dealCard(int labelID, String iconID, int x, int y) {
        
        removeCard(labelID);
        removeCardBack(labelID);
        
        cardLabels[labelID] = new JLabel();
        ImageIcon cIcon = new ImageIcon(getClass().getResource("/resources/" + iconID));
        cardLabels[labelID].setIcon(cIcon);
        cardLabels[labelID].setBounds(x, y, cIcon.getIconWidth(), cIcon.getIconHeight());
        cardLabels[labelID].setOpaque(false);
        add(cardLabels[labelID], new Integer(2));
        cardLabels[labelID].setVisible(false);
        setCardBackInView(labelID, x, y);
    }
    
    public void setCardFaceUpInView(int labelID) {
        removeCardBack(labelID);
        cardLabels[labelID].setVisible(true);
    }
    
    
    public void removeCard(int labelID) {
        if (cardLabels[labelID] != null) {
            cardLabels[labelID].setVisible(false);
            remove(cardLabels[labelID]);
            cardLabels[labelID] = null;
        }
    }
    
    public void removeCardBack(int sceneID) {
        if (cardBackLabels[sceneID] != null) {
            cardBackLabels[sceneID].setVisible(false);
            remove(cardBackLabels[sceneID]);
            cardBackLabels[sceneID] = null;
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
            final int rank = i + 2;
            Border border = BorderFactory.createLineBorder(Color.GREEN, 1);
            System.out.println(i);
            if(rankIndex <= i && i <= maxDollarRank) {
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
                        
                        Controller.getInstance().upgrade(rank , CurrencyType.DOLLARS);
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
            
            if(rankIndex <= i && i <= maxCreditRank) {
                creditLabels[i] = new JLabel();
                creditLabels[i].setBorder(border);
                creditLabels[i].setBounds(CastingOffice.getInstance().getCreditCoordinates().get(i * 2), CastingOffice.getInstance().getCreditCoordinates().get(creditIndex), 19, 19);
                creditLabels[i].setOpaque(false);
                creditLabels[i].addMouseListener(new MouseAdapter() {
                    public void mouseReleased(MouseEvent e) {
                        Controller.getInstance().upgrade(rank , CurrencyType.CREDITS);
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
        JOptionPane.showMessageDialog(this, "Select the desired rank");
    }

    public void deleteUpgradeLabels() {
        System.out.println("Deleting them labels");
        for (int i = 0; i < dollarLabels.length; i++) {
            if(dollarLabels[i] != null) {
                dollarLabels[i].setVisible(false);
                remove(dollarLabels[i]);
            }
            if(creditLabels[i] != null) {
                creditLabels[i].setVisible(false);
                remove(creditLabels[i]);
            }
            
        }
    }
    
    public JLabel getPlayerLabel(int index){
        return playerLabels[index];
    }
    
    public void setPlayerIcon(int playerID, int rank){
        playerLabels[playerID].setIcon(getIcon(playerID, rank));
        
    }

}
