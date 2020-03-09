/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadwood;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Curveball
 */
public class InfoPanel extends JPanel {

    private static InfoPanel instance = new InfoPanel();
    private JTextArea updateTextArea;
    private JPanel playerInfoDisplay;
    private JLabel playerInfoHeader;
    private JTextArea playerInfoData;

    private InfoPanel() {

    }

    public static InfoPanel getInstance() {
        return instance;
    }

    public void init(int height, int width, ImageIcon boardIcon) {
        instance.setLayout(new BorderLayout());
        instance.setBackground(Color.red);
        instance.setPreferredSize(new Dimension(((width - boardIcon.getIconWidth()) / 2), 0));
        instance.setElements(height, width, boardIcon);
        instance.setFonts();
        JScrollPane scrollpane = new JScrollPane(updateTextArea);
        instance.add(scrollpane, BorderLayout.NORTH);
        instance.add(playerInfoDisplay, BorderLayout.SOUTH);
        setPlayerInfoData();
    }

    private void setElements(int height, int width, ImageIcon boardIcon) {
        updateTextArea = new JTextArea();
        updateTextArea.setBackground(Color.white);
        updateTextArea.setOpaque(true);
        updateTextArea.setWrapStyleWord(true);
        updateTextArea.setLineWrap(true);
        updateTextArea.setText("Display history of player choices");
        updateTextArea.setPreferredSize(new Dimension((width - boardIcon.getIconWidth()), height / 2 - 50));
        updateTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        updateTextArea.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        updateTextArea.setEditable(false);

        playerInfoDisplay = new JPanel();
        playerInfoDisplay.setLayout(new BoxLayout(playerInfoDisplay, BoxLayout.PAGE_AXIS));
        playerInfoDisplay.setBackground(Color.white);
        playerInfoDisplay.setOpaque(true);
        //playerInfoLabel.setText("Player Information");
        playerInfoDisplay.setPreferredSize(new Dimension((width - boardIcon.getIconWidth()), height / 2));
        //playerInfoDisplay.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerInfoDisplay.setBorder(BorderFactory.createLineBorder(Color.black, 3));

        playerInfoHeader = new JLabel();
        playerInfoHeader.setText("Player Information");
        playerInfoHeader.setBackground(Color.white);
        playerInfoHeader.setOpaque(true);
        playerInfoHeader.setPreferredSize(new Dimension((width - boardIcon.getIconWidth()), height / 10));
        playerInfoHeader.setAlignmentX(Component.CENTER_ALIGNMENT);

        playerInfoData = new JTextArea();
        playerInfoData.setBackground(Color.white);
        playerInfoData.setOpaque(true);
        playerInfoData.setWrapStyleWord(true);
        playerInfoData.setLineWrap(true);
        playerInfoData.setPreferredSize(new Dimension((width - boardIcon.getIconWidth()), height / 2 - 50));
        playerInfoData.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerInfoData.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0));
        playerInfoData.setEditable(false);

        playerInfoDisplay.add(Box.createVerticalGlue());
        playerInfoDisplay.add(playerInfoHeader);
        playerInfoDisplay.add(Box.createVerticalGlue());
        playerInfoDisplay.add(playerInfoData);

    }

    private void setFonts() {
        try {
            Font regularFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/resources/Spartan-Regular.ttf").openStream());
            Font boldFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/resources/Spartan-SemiBold.ttf").openStream());
            GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            genv.registerFont(regularFont);
            genv.registerFont(boldFont);
            Font font2 = regularFont.deriveFont(18f);
            updateTextArea.setFont(font2.deriveFont(Font.ITALIC));
            playerInfoHeader.setFont(font2.deriveFont(Font.ITALIC));
            playerInfoData.setFont(font2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpdateTextArea(String message) {
        int end;
        updateTextArea.setText(updateTextArea.getText() + message);
        try {
            if (updateTextArea.getLineCount() == 24) {
                end = updateTextArea.getLineEndOffset(0);
                updateTextArea.replaceRange("", 0, end);
            }
        } catch (BadLocationException ex) {
            Logger.getLogger(GameView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setPlayerInfoData() {
        Player player = TurnManager.getInstance().getActivePlayer();
        playerInfoData.setText("Active player: player " + player.getID()
                + "\n\nCurrent position: " + Board.getInstance().getSpace(player.getLocation()).getName()
                + "\n\nRank: " + player.getRank()
                + "\n\nDollars: " + player.getDollars()
                + "\n\nCredits: " + player.getCredits()
                + "\n\nRehearsal chips: " + player.getRehearsalChips());
        if (player.getRole() != null) {
            playerInfoData.setText(playerInfoData.getText() + "\n\n Role: " + player.getRole().getName());
        }

    }
}
