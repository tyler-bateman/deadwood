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

    private JTextArea updateTextArea;
    private JLabel playerInfoLabel;

    public InfoPanel(int height, int width, ImageIcon boardIcon) {

        setLayout(new BorderLayout());
        setBackground(Color.red);
        setPreferredSize(new Dimension(((width - boardIcon.getIconWidth()) / 2), 0));

        setElements(height, width, boardIcon);
        setFonts();
        JScrollPane scrollpane = new JScrollPane(updateTextArea);
        add(scrollpane, BorderLayout.NORTH);
        add(playerInfoLabel, BorderLayout.SOUTH);

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

        playerInfoLabel = new JLabel();
        playerInfoLabel.setBackground(Color.white);
        playerInfoLabel.setOpaque(true);
        playerInfoLabel.setText("Display active player's info ");
        playerInfoLabel.setPreferredSize(new Dimension((width - boardIcon.getIconWidth()), height / 2));
        playerInfoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerInfoLabel.setBorder(BorderFactory.createLineBorder(Color.black, 3));

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
            playerInfoLabel.setFont(font2.deriveFont(Font.ITALIC));

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
}
