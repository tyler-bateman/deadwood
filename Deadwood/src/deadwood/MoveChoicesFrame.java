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
public class MoveChoicesFrame extends JFrame {

    private JPanel contentPane;
    private JLabel whereToLabel;
    private JPanel scenesPanel;
    private JButton[] sceneButtons;

    public MoveChoicesFrame() {

        sceneButtons = new JButton[3];
        centreWindow(this);

        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

        whereToLabel = new JLabel();
        whereToLabel.setText("Where to ?");
        whereToLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        scenesPanel = new JPanel();
        scenesPanel.setLayout(new BoxLayout(scenesPanel, BoxLayout.X_AXIS));

        sceneButtons[0] = new JButton("Scene 1");
        sceneButtons[0].setAlignmentX(Component.CENTER_ALIGNMENT);
        sceneButtons[1] = new JButton("Scene 2");
        sceneButtons[1].setAlignmentX(Component.CENTER_ALIGNMENT);
        sceneButtons[2] = new JButton("Scene 3");
        sceneButtons[2].setAlignmentX(Component.CENTER_ALIGNMENT);

        scenesPanel.add(Box.createHorizontalGlue());
        scenesPanel.add(sceneButtons[0]);
        scenesPanel.add(Box.createHorizontalGlue());
        scenesPanel.add(sceneButtons[1]);
        scenesPanel.add(Box.createHorizontalGlue());
        scenesPanel.add(sceneButtons[2]);
        scenesPanel.add(Box.createHorizontalGlue());

        contentPane.add(Box.createVerticalGlue());
        contentPane.add(whereToLabel);
        contentPane.add(Box.createVerticalGlue());
        contentPane.add(scenesPanel);
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
            sceneButtons[0].setFont(font1);
            sceneButtons[1].setFont(font1);
            sceneButtons[2].setFont(font1);
            whereToLabel.setFont(font2.deriveFont(Font.ITALIC));
        } catch (Exception ex) {

        }
    }

}
