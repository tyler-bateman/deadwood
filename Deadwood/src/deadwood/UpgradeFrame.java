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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public UpgradeFrame(int currRank, int dollars, int credits, int[] dollarPrice, int[] creditPrice) {
        rankButtons = new JButton[5];
        centreWindow(this);

        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

        whatRankLabel = new JLabel();
        whatRankLabel.setText("What rank?");
        whatRankLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        ranksPanel = new JPanel();
        ranksPanel.setLayout(new BoxLayout(ranksPanel, BoxLayout.X_AXIS));

        setJButtons();

        ranksPanel.add(Box.createHorizontalGlue());

        contentPane.add(Box.createVerticalGlue());
        contentPane.add(whatRankLabel);
        contentPane.add(Box.createVerticalGlue());
        contentPane.add(ranksPanel);
        contentPane.add(Box.createVerticalGlue());
        setContentPane(contentPane);
        setVisible(true);
    }

    private void setFonts(JButton b) {
        try {
            Font regularFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/resources/Spartan-Regular.ttf").openStream());
            Font boldFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/resources/Spartan-Medium.ttf").openStream());
            GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            genv.registerFont(regularFont);
            genv.registerFont(boldFont);
            Font font1 = boldFont.deriveFont(24f);
            Font font2 = regularFont.deriveFont(30f);
            b.setFont(font1);
            whatRankLabel.setFont(font2.deriveFont(Font.ITALIC));
        } catch (Exception ex) {

        }
    }

    private void setJButtons() {
        final JFrame frame = this;
        for (int i = 0; i < rankButtons.length; i++) {
            final int index = i;
            rankButtons[i] = new JButton("Rank " + (i + 1));
            rankButtons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            rankButtons[i].addActionListener((new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    Controller.getInstance().upgrade((index + 1), CurrencyType.DOLLARS);
                    InfoPanel.getInstance().setUpdateTextArea("Your have upgraded !\n");
                    frame.dispose();
                }
            }));
            ranksPanel.add(Box.createHorizontalGlue());
            ranksPanel.add(rankButtons[i]);
            setFonts(rankButtons[i]);

        }
    }
}
