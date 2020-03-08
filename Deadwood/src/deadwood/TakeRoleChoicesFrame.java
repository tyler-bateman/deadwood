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
public class TakeRoleChoicesFrame extends JFrame {
    private JPanel contentPane;
    private JLabel whatRoleLabel;
    private JPanel rolesPanel;
    private JButton[] roleButtons;
        
    public TakeRoleChoicesFrame() {
        roleButtons = new JButton[3];
        centreWindow(this);

        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

        whatRoleLabel = new JLabel();
        whatRoleLabel.setText("What role?");
        whatRoleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        rolesPanel = new JPanel();
        rolesPanel.setLayout(new BoxLayout(rolesPanel, BoxLayout.X_AXIS));

        roleButtons[0] = new JButton("Role 1");
        roleButtons[0].setAlignmentX(Component.CENTER_ALIGNMENT);
        roleButtons[1] = new JButton("Role 2");
        roleButtons[1].setAlignmentX(Component.CENTER_ALIGNMENT);
        roleButtons[2] = new JButton("Role 3");
        roleButtons[2].setAlignmentX(Component.CENTER_ALIGNMENT);
        try {
            Font regularFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/resources/Spartan-Regular.ttf").openStream());
            Font boldFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/resources/Spartan-Medium.ttf").openStream());
            GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            genv.registerFont(regularFont);
            genv.registerFont(boldFont);
            Font font1 = boldFont.deriveFont(24f);
            Font font2 = regularFont.deriveFont(30f);
            roleButtons[0].setFont(font1);
            roleButtons[1].setFont(font1);
            roleButtons[2].setFont(font1);
            whatRoleLabel.setFont(font2.deriveFont(Font.ITALIC));
        } catch (Exception ex) {

        }

        rolesPanel.add(Box.createHorizontalGlue());
        rolesPanel.add(roleButtons[0]);
        rolesPanel.add(Box.createHorizontalGlue());
        rolesPanel.add(roleButtons[1]);
        rolesPanel.add(Box.createHorizontalGlue());
        rolesPanel.add(roleButtons[2]);
        rolesPanel.add(Box.createHorizontalGlue());

        contentPane.add(Box.createVerticalGlue());
        contentPane.add(whatRoleLabel);
        contentPane.add(Box.createVerticalGlue());
        contentPane.add(rolesPanel);
        contentPane.add(Box.createVerticalGlue());

        setContentPane(contentPane);
        setVisible(true);
    }

}
