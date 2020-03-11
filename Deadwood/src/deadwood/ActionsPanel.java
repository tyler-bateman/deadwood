/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadwood;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.*;

/**
 *
 * @author Curveball
 */
public class ActionsPanel extends JPanel implements ActionListener {

    private static ActionsPanel instance = new ActionsPanel();
    private MoveChoicesFrame moveChoicesFrame;
    private TakeRoleChoicesFrame takeRoleChoicesFrame;
    private UpgradeFrame upgradeFrame;

    private JButton moveButton;
    private JButton takeRoleButton;
    private JButton actButton;
    private JButton rehearseButton;
    private JButton upgradeButton;
    private JButton endTurnButton;

    private ActionsPanel() {

    }

    public static ActionsPanel getInstance() {
        return instance;
    }

    public void init(int height, int width, ImageIcon boardIcon) {
        instance.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        instance.setBackground(Color.white);
        instance.setPreferredSize(new Dimension(((width - boardIcon.getIconWidth()) / 2), 0));

        instance.add(Box.createRigidArea(new Dimension(height / 15, width / 15)));
        instance.setJButtons();
        instance.setFonts();
        instance.add(Box.createRigidArea(new Dimension(height / 15, width / 15)));
        instance.add(Box.createVerticalGlue());
        instance.setBorder(BorderFactory.createLineBorder(Color.black, 3));
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == moveButton) {
            moveChoicesFrame = new MoveChoicesFrame();
        }

        if (e.getSource() == actButton) {
            /*InfoPanel.getInstance().setUpdateTextArea("Your have acted !\n");
            BoardPane.getInstance().removeShotCounter(1);*/
            Controller.getInstance().act();
        }

        if (e.getSource() == takeRoleButton) {
            Controller.getInstance().takeRoleMenu();
            
        }

        if (e.getSource() == rehearseButton) {
            InfoPanel.getInstance().setUpdateTextArea("Your have rehearsed !\n");
        }

        if (e.getSource() == upgradeButton) {
            upgradeFrame = new UpgradeFrame();
        }

        if (e.getSource() == endTurnButton) {
            InfoPanel.getInstance().setUpdateTextArea("Your turn has ended.\n");
        }
    }

    private void setJButtons() {
        moveButton = new JButton();
        moveButton.setText("Move");
        moveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        moveButton.addActionListener(this);
        add(moveButton);
        add(Box.createVerticalGlue());

        takeRoleButton = new JButton();
        takeRoleButton.setText("Take Role");
        takeRoleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        takeRoleButton.addActionListener(this);
        add(takeRoleButton);
        add(Box.createVerticalGlue());

        actButton = new JButton();
        actButton.setText("Act");
        actButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        actButton.addActionListener(this);
        add(actButton);
        add(Box.createVerticalGlue());

        rehearseButton = new JButton();
        rehearseButton.setText("Rehearse");
        rehearseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        rehearseButton.addActionListener(this);
        add(rehearseButton);
        add(Box.createVerticalGlue());

        upgradeButton = new JButton();
        upgradeButton.setText("Upgrade");
        upgradeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        upgradeButton.addActionListener(this);
        add(upgradeButton);
        add(Box.createVerticalGlue());

        endTurnButton = new JButton();
        endTurnButton.setText("End Turn");
        endTurnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        endTurnButton.addActionListener(this);
        add(endTurnButton);
    }

    private void setFonts() {
        try {
            Font regularFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/resources/Spartan-Regular.ttf").openStream());
            Font boldFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/resources/Spartan-SemiBold.ttf").openStream());
            GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            genv.registerFont(regularFont);
            genv.registerFont(boldFont);
            Font font1 = boldFont.deriveFont(32f);
            moveButton.setFont(font1);
            takeRoleButton.setFont(font1);
            actButton.setFont(font1);
            rehearseButton.setFont(font1);
            upgradeButton.setFont(font1);
            endTurnButton.setFont(font1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int height = dimension.height / 3;
        int width = dimension.width / 3;
        frame.setSize(new Dimension(width, height));
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
    
    public void updateEnabledButtons(LinkedList<UseCase> useCaseList) {
        moveButton.setEnabled(false);
        takeRoleButton.setEnabled(false);
        actButton.setEnabled(false);
        rehearseButton.setEnabled(false);
        upgradeButton.setEnabled(false);
        endTurnButton.setEnabled(false);
        for(UseCase u: useCaseList) {
            switch(u) {
                case MOVE:
                    moveButton.setEnabled(true);
                    break;
                case TAKE_ROLE:
                    takeRoleButton.setEnabled(true);
                    break;
                case ACT:
                    actButton.setEnabled(true);
                    break;
                case REHEARSE:
                    rehearseButton.setEnabled(true);
                case UPGRADE:
                    upgradeButton.setEnabled(true);
                case END_TURN:
                    endTurnButton.setEnabled(true);
            }
                    
        }
    }
}
