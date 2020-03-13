
package deadwood;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * BoardPane holds the visual components of Deadwood and the methods to
 * manipulate them. It displays the board icon, the card backs, card faces and
 * player labels
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

    /**
     * Private constructor as required by Singleton design pattern.
     */
    private BoardPane() {

    }

    /**
     * getInstance() method as required by Singleton design pattern
     *
     * @return the singular instance of BoardPane
     */
    public static BoardPane getInstance() {
        return instance;
    }

    /**
     * initialises the components contained within BordPane
     *
     * @param height height of the user's screen
     * @param width width of the user's screen
     * @param boardIcon icon of the game board
     * @param playerNumber number of players in this game of Deadwood
     */
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

    /**
     * gets the appropriate player icon depending on the colour and the rank
     *
     * @param playerID ID of the player
     * @param rank rank of the player label
     * @return
     */
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

    /**
     * moves the player's label to the given coordinates
     *
     * @param playerID ID of player label to be moved
     * @param x X coordinate to be moved to
     * @param y Y coordinate to be moved to
     */
    public void movePlayerLabel(int playerID, int x, int y) {

        playerLabels[playerID].setBounds(x, y, playerIconWidth, playerIconHeight);
        add(playerLabels[playerID], new Integer(3));

    }

    /**
     * moves the player label to the given on card role
     *
     * @param playerID ID of player to be moved
     * @param labelID ID of the card label that has the on card role
     * @param x X coordinate to be moved to
     * @param y Y coordinate to be moved to
     */
    public void movePlayerToStarringRole(int playerID, int labelID, int x, int y) {
        playerLabels[playerID].setBounds(x, y, playerIconWidth, playerIconHeight);
        cardLabels[labelID].add(playerLabels[playerID]);
        add(cardLabels[labelID], new Integer(3));
    }

    /**
     * removes a shot counter from a scene
     *
     * @param index index of the scene's shot counter
     */
    public void removeShotCounter(int index) {
        shotCounterLabels[index].setVisible(false);
    }

    /**
     * sets the card back on BoardPane at the given location
     *
     * @param labelID position of the card back in the cardBackLabels array
     * @param x X coordinate to be moved to
     * @param y Y coordinate to be moved to
     */
    public void setCardBackInView(int labelID, int x, int y) {
        JLabel backlabel = new JLabel();
        ImageIcon backIcon = new ImageIcon(getClass().getResource("/resources/CardBack.jpg"));
        backlabel.setIcon(backIcon);
        backlabel.setBounds(x, y, backIcon.getIconWidth(), backIcon.getIconHeight());
        backlabel.setOpaque(false);
        add(backlabel, new Integer(1));
        cardBackLabels[labelID] = backlabel;
    }

    /**
     * removes the previous card back and card and places new ones
     *
     * @param labelID ID of the card labels
     * @param iconID icon of the scene card
     * @param x X coordinate to be moved to
     * @param y Y coordinate to be moved to
     */
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

    /**
     * "flips" the card from card back to card face
     *
     * @param labelID ID of the card label
     */
    public void setCardFaceUpInView(int labelID) {
        removeCardBack(labelID);
        cardLabels[labelID].setVisible(true);
    }

    /**
     * removes card from the view
     *
     * @param labelID ID of the card label
     */
    public void removeCard(int labelID) {
        if (cardLabels[labelID] != null) {
            cardLabels[labelID].setVisible(false);
            remove(cardLabels[labelID]);
            cardLabels[labelID] = null;
        }
    }

    /**
     * removes card back from the view
     *
     * @param sceneID ID of the card back label
     */
    public void removeCardBack(int sceneID) {
        if (cardBackLabels[sceneID] != null) {
            cardBackLabels[sceneID].setVisible(false);
            remove(cardBackLabels[sceneID]);
            cardBackLabels[sceneID] = null;
        }
    }

    /**
     * places a shot counter at the given location
     *
     * @param x X coordinate in which to place label
     * @param y Y coordinate in which to place label
     * @return returns the shot counter label
     */
    public JLabel shotCounter(int x, int y) {
        JLabel shotCounterLabel = new JLabel();
        ImageIcon shotCounterIcon = new ImageIcon(getClass().getResource("/resources/shot.png"));
        shotCounterLabel.setIcon(shotCounterIcon);
        shotCounterLabel.setBounds(x, y, shotCounterIcon.getIconWidth(), shotCounterIcon.getIconHeight());
        shotCounterLabel.setOpaque(false);
        add(shotCounterLabel, new Integer(2));
        return shotCounterLabel;
    }

    /**
     * places shot counters on all the shot positions in the board
     *
     * @param xCoords X coordinates of the shot positions in the board
     * @param yCoords Y coordinates of the shot positions in the board
     */
    public void initializeAllShots(int[] xCoords, int[] yCoords) {
        for (int i = 0; i < xCoords.length; i++) {
            shotCounterLabels[i] = shotCounter(xCoords[i], yCoords[i]);
        }
    }

    /**
     * redraws the shots after a successful act attempt
     *
     * @param startingIndex index in the shotCounterLabels array where the shot
     * counters for the scene starts
     * @param num number of shots remaining in the scene
     * @param total total number of shots in the scene
     */
    public void redrawShots(int startingIndex, int num, int total) {
        for (int i = 0; i < total; i++) {
            shotCounterLabels[i + startingIndex].setVisible(false);
        }
        //shotCounterLabels[startingIndex].setVisible(true);
        for (int i = 0; i < num; i++) {
            shotCounterLabels[i + startingIndex].setVisible(true);
        }
    }

    /**
     * sets the player labels depending on the number of players in this round
     * of Deadwood
     */
    public void makePlayerLabels() {
        for (int i = 0; i < playerLabels.length; i++) {
            playerLabels[i] = new JLabel();
            playerLabels[i].setIcon(getIcon(i, 1));
            playerLabels[i].setOpaque(false);
            add(playerLabels[i], new Integer(2));
        }

    }

    /**
     * creates and displays the possible upgrade possibilities on the board once
     * the player gets to the Casting Office and clicks on Upgrade
     *
     * @param currRank rank of the active player
     * @param maxDollarRank maximum rank that the active player can afford with
     * dollars
     * @param maxCreditRank maximum rank that the active player can afford with
     * credits
     */
    public void displayUpgradeLabels(int currRank, int maxDollarRank, int maxCreditRank) {
        int rankIndex = currRank - 1;
        int dollarIndex = 1;
        int creditIndex = 1;
        for (int i = 0; i < dollarLabels.length; i++) {
            final int rank = i + 2;
            Border border = BorderFactory.createLineBorder(Color.GREEN, 1);
            if (rankIndex <= i && i <= maxDollarRank) {
                dollarLabels[i] = new JLabel();

                dollarLabels[i].setBorder(border);
                dollarLabels[i].setBounds(CastingOffice.getInstance().getDollarCoordinates().get(i * 2), CastingOffice.getInstance().getDollarCoordinates().get(dollarIndex), 19, 19);
                dollarLabels[i].setOpaque(false);
                dollarLabels[i].addMouseListener(new MouseAdapter() {
                    public void mouseEntered(MouseEvent e) {
                        JLabel lab = (JLabel) e.getSource();
                        Border border = BorderFactory.createLineBorder(Color.RED, 2);
                        lab.setBorder(border);
                    }

                    public void mouseReleased(MouseEvent e) {

                        Controller.getInstance().upgrade(rank, CurrencyType.DOLLARS);
                    }

                    public void mouseExited(MouseEvent e) {
                        JLabel lab = (JLabel) e.getSource();
                        Border border = BorderFactory.createLineBorder(Color.GREEN, 1);
                        lab.setBorder(border);
                    }

                });
                add(dollarLabels[i], new Integer(2));
            }
            dollarIndex = dollarIndex + 2;

            if (rankIndex <= i && i <= maxCreditRank) {
                creditLabels[i] = new JLabel();
                creditLabels[i].setBorder(border);
                creditLabels[i].setBounds(CastingOffice.getInstance().getCreditCoordinates().get(i * 2), CastingOffice.getInstance().getCreditCoordinates().get(creditIndex), 19, 19);
                creditLabels[i].setOpaque(false);
                creditLabels[i].addMouseListener(new MouseAdapter() {
                    public void mouseReleased(MouseEvent e) {
                        Controller.getInstance().upgrade(rank, CurrencyType.CREDITS);
                    }

                    public void mouseEntered(MouseEvent e) {
                        JLabel lab = (JLabel) e.getSource();
                        Border border = BorderFactory.createLineBorder(Color.RED, 2);
                        lab.setBorder(border);
                    }

                    public void mouseExited(MouseEvent e) {
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

    /**
     * removes the upgradeLabels from the board once the player has finished
     * upgrading
     */
    public void deleteUpgradeLabels() {
        for (int i = 0; i < dollarLabels.length; i++) {
            if (dollarLabels[i] != null) {
                dollarLabels[i].setVisible(false);
                remove(dollarLabels[i]);
            }
            if (creditLabels[i] != null) {
                creditLabels[i].setVisible(false);
                remove(creditLabels[i]);
            }

        }
    }

    /**
     * gets label of the active player
     *
     * @param index ID of the active player
     * @return
     */
    public JLabel getPlayerLabel(int index) {
        return playerLabels[index];
    }

    /**
     * changes the active player's icon depending on their new rank
     *
     * @param playerID ID of the active player
     * @param rank given rank used to select the new icon
     */
    public void setPlayerIcon(int playerID, int rank) {
        playerLabels[playerID].setIcon(getIcon(playerID, rank));

    }

}
