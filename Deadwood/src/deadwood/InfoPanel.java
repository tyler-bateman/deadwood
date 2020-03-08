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
import javax.swing.*;

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
        JScrollPane scrollpane = new JScrollPane(updateTextArea);
        add(scrollpane, BorderLayout.NORTH);

        playerInfoLabel = new JLabel();
        playerInfoLabel.setBackground(Color.white);
        playerInfoLabel.setOpaque(true);
        playerInfoLabel.setText("Display active player's info ");
        playerInfoLabel.setPreferredSize(new Dimension((width - boardIcon.getIconWidth()), height / 2));
        playerInfoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerInfoLabel.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        add(playerInfoLabel, BorderLayout.SOUTH);
        setFonts();
    }

    private void setFonts() {
        try {
            Font regularFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/resources/Spartan-Regular.ttf").openStream());
            Font boldFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/resources/Spartan-SemiBold.ttf").openStream());
            GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            genv.registerFont(regularFont);
            genv.registerFont(boldFont);
            //Font font1 = boldFont.deriveFont(32f);
            Font font2 = regularFont.deriveFont(18f);
            updateTextArea.setFont(font2.deriveFont(Font.ITALIC));
            playerInfoLabel.setFont(font2.deriveFont(Font.ITALIC));
            /*moveButton.setFont(font1);
            takeRoleButton.setFont(font1);
            actButton.setFont(font1);
            rehearseButton.setFont(font1);
            upgradeButton.setFont(font1);
            endTurnButton.setFont(font1);*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
