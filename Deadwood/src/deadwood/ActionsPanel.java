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
import javax.swing.*;

/**
 *
 * @author Curveball
 */
public class ActionsPanel extends JPanel implements ActionListener {
    private MoveChoicesFrame moveChoicesFrame;
    private TakeRoleChoicesFrame takeRoleChoicesFrame;
    private UpgradeFrame upgradeFrame;
    
    private JButton moveButton;
    private JButton takeRoleButton;
    private JButton actButton;
    private JButton rehearseButton;
    private JButton upgradeButton;
    private JButton endTurnButton;

    public ActionsPanel(int height, int width, ImageIcon boardIcon) {

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(Color.white);
        setPreferredSize(new Dimension(((width - boardIcon.getIconWidth()) / 2), 0));

        add(Box.createRigidArea(new Dimension(height / 15, width / 15)));
        setJButtons();
        setFonts();
        add(Box.createRigidArea(new Dimension(height / 15, width / 15)));
        add(Box.createVerticalGlue());
        setBorder(BorderFactory.createLineBorder(Color.black, 3));

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == moveButton) {
            moveChoicesFrame = new MoveChoicesFrame();

        }

        /*if (e.getSource() == actButton) {
            int end;
            updateTextArea.setText(updateTextArea.getText() + "You have acted !\n");
            try {
                if (updateTextArea.getLineCount() == 24) {
                    end = updateTextArea.getLineEndOffset(0);
                    updateTextArea.replaceRange("", 0, end);
                }
            } catch (BadLocationException ex) {
                Logger.getLogger(GameView.class.getName()).log(Level.SEVERE, null, ex);
            }

        }*/
        if (e.getSource() == takeRoleButton) {
            takeRoleChoicesFrame = new TakeRoleChoicesFrame();
        }

        /*if (e.getSource() == rehearseButton) {
            int end;
            updateTextArea.setText(updateTextArea.getText() + "You have rehearsed !\n");
            try {
                if (updateTextArea.getLineCount() == 24) {
                    end = updateTextArea.getLineEndOffset(0);
                    updateTextArea.replaceRange("", 0, end);
                }
            } catch (BadLocationException ex) {
                Logger.getLogger(GameView.class.getName()).log(Level.SEVERE, null, ex);
            }

        }*/
        if (e.getSource() == upgradeButton) {
            upgradeFrame = new UpgradeFrame();
            
        }

        if (e.getSource() == endTurnButton) {
            
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
}