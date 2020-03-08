/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadwood;

import static deadwood.ActionsPanel.centreWindow;
import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import javax.swing.*;

/**
 *
 * @author Curveball
 */
public class UpgradeFrame extends JFrame {
    private JPanel contentPane;
    private JLabel whatRankLabel;
    private JPanel ranksPanel;
    private JButton[] rankButtons;
    
    public UpgradeFrame() {
        rankButtons = new JButton[3];
        centreWindow(this);

        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

        whatRankLabel = new JLabel();
        whatRankLabel.setText("What rank?");
        whatRankLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        ranksPanel = new JPanel();
        ranksPanel.setLayout(new BoxLayout(ranksPanel, BoxLayout.X_AXIS));

        rankButtons[0] = new JButton("Rank 1");
        rankButtons[0].setAlignmentX(Component.CENTER_ALIGNMENT);
        rankButtons[1] = new JButton("Rank 2");
        rankButtons[1].setAlignmentX(Component.CENTER_ALIGNMENT);
        rankButtons[2] = new JButton("Rank 3");
        rankButtons[2].setAlignmentX(Component.CENTER_ALIGNMENT);


        ranksPanel.add(Box.createHorizontalGlue());
        ranksPanel.add(rankButtons[0]);
        ranksPanel.add(Box.createHorizontalGlue());
        ranksPanel.add(rankButtons[1]);
        ranksPanel.add(Box.createHorizontalGlue());
        ranksPanel.add(rankButtons[2]);
        ranksPanel.add(Box.createHorizontalGlue());

        contentPane.add(Box.createVerticalGlue());
        contentPane.add(whatRankLabel);
        contentPane.add(Box.createVerticalGlue());
        contentPane.add(ranksPanel);
        contentPane.add(Box.createVerticalGlue());
        setFonts();
        setContentPane(contentPane);
        setVisible(true);
    }
    
    private void setFonts() {
        try {
            Font regularFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/resources/Spartan-Regular.ttf").openStream());
            Font boldFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/resources/Spartan-Medium.ttf").openStream());
            GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            genv.registerFont(regularFont);
            genv.registerFont(boldFont);
            Font font1 = boldFont.deriveFont(24f);
            Font font2 = regularFont.deriveFont(30f);
            rankButtons[0].setFont(font1);
            rankButtons[1].setFont(font1);
            rankButtons[2].setFont(font1);
            whatRankLabel.setFont(font2.deriveFont(Font.ITALIC));
        } catch (Exception ex) {

        }
    }
}
