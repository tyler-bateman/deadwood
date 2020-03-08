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
            JFrame moveChoicesFrame = new JFrame();
            centreWindow(moveChoicesFrame);

            JPanel contentPane = new JPanel();
            contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

            JLabel whereToLabel = new JLabel();
            whereToLabel.setText("Where to ?");
            whereToLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel scenesPanel = new JPanel();
            scenesPanel.setLayout(new BoxLayout(scenesPanel, BoxLayout.X_AXIS));

            JButton scene1Button = new JButton("Scene 1");
            scene1Button.setAlignmentX(Component.CENTER_ALIGNMENT);
            JButton scene2Button = new JButton("Scene 2");
            scene1Button.setAlignmentX(Component.CENTER_ALIGNMENT);
            JButton scene3Button = new JButton("Scene 3");
            scene1Button.setAlignmentX(Component.CENTER_ALIGNMENT);
            try {
                Font regularFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/resources/Spartan-Regular.ttf").openStream());
                Font boldFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/resources/Spartan-Medium.ttf").openStream());
                GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
                genv.registerFont(regularFont);
                genv.registerFont(boldFont);
                Font font1 = boldFont.deriveFont(24f);
                Font font2 = regularFont.deriveFont(30f);
                scene1Button.setFont(font1);
                scene2Button.setFont(font1);
                scene3Button.setFont(font1);
                whereToLabel.setFont(font2.deriveFont(Font.ITALIC));
            } catch (Exception ex) {

            }

            scenesPanel.add(Box.createHorizontalGlue());
            scenesPanel.add(scene1Button);
            scenesPanel.add(Box.createHorizontalGlue());
            scenesPanel.add(scene2Button);
            scenesPanel.add(Box.createHorizontalGlue());
            scenesPanel.add(scene3Button);
            scenesPanel.add(Box.createHorizontalGlue());

            contentPane.add(Box.createVerticalGlue());
            contentPane.add(whereToLabel);
            contentPane.add(Box.createVerticalGlue());
            contentPane.add(scenesPanel);
            contentPane.add(Box.createVerticalGlue());

            moveChoicesFrame.setContentPane(contentPane);
            moveChoicesFrame.setVisible(true);

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
            JFrame roleChoicesFrame = new JFrame();
            centreWindow(roleChoicesFrame);

            JPanel contentPane = new JPanel();
            contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

            JLabel whatRoleLabel = new JLabel();
            whatRoleLabel.setText("What role?");
            whatRoleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel rolesPanel = new JPanel();
            rolesPanel.setLayout(new BoxLayout(rolesPanel, BoxLayout.X_AXIS));

            JButton role1Button = new JButton("Role 1");
            role1Button.setAlignmentX(Component.CENTER_ALIGNMENT);
            JButton role2Button = new JButton("Role 2");
            role2Button.setAlignmentX(Component.CENTER_ALIGNMENT);
            JButton role3Button = new JButton("Role 3");
            role3Button.setAlignmentX(Component.CENTER_ALIGNMENT);
            try {
                Font regularFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/resources/Spartan-Regular.ttf").openStream());
                Font boldFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/resources/Spartan-Medium.ttf").openStream());
                GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
                genv.registerFont(regularFont);
                genv.registerFont(boldFont);
                Font font1 = boldFont.deriveFont(24f);
                Font font2 = regularFont.deriveFont(30f);
                role1Button.setFont(font1);
                role2Button.setFont(font1);
                role3Button.setFont(font1);
                whatRoleLabel.setFont(font2.deriveFont(Font.ITALIC));
            } catch (Exception ex) {

            }

            rolesPanel.add(Box.createHorizontalGlue());
            rolesPanel.add(role1Button);
            rolesPanel.add(Box.createHorizontalGlue());
            rolesPanel.add(role2Button);
            rolesPanel.add(Box.createHorizontalGlue());
            rolesPanel.add(role3Button);
            rolesPanel.add(Box.createHorizontalGlue());

            contentPane.add(Box.createVerticalGlue());
            contentPane.add(whatRoleLabel);
            contentPane.add(Box.createVerticalGlue());
            contentPane.add(rolesPanel);
            contentPane.add(Box.createVerticalGlue());

            roleChoicesFrame.setContentPane(contentPane);
            roleChoicesFrame.setVisible(true);
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
            JFrame upgradeChoicesFrame = new JFrame();
            centreWindow(upgradeChoicesFrame);

            JPanel contentPane = new JPanel();
            contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

            JLabel whatRankLabel = new JLabel();
            whatRankLabel.setText("What rank?");
            whatRankLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel ranksPanel = new JPanel();
            ranksPanel.setLayout(new BoxLayout(ranksPanel, BoxLayout.X_AXIS));

            JButton rank1Button = new JButton("Rank 1");
            rank1Button.setAlignmentX(Component.CENTER_ALIGNMENT);
            JButton rank2Button = new JButton("Rank 2");
            rank2Button.setAlignmentX(Component.CENTER_ALIGNMENT);
            JButton rank3Button = new JButton("Rank 3");
            rank3Button.setAlignmentX(Component.CENTER_ALIGNMENT);
            try {
                Font regularFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/resources/Spartan-Regular.ttf").openStream());
                Font boldFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/resources/Spartan-Medium.ttf").openStream());
                GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
                genv.registerFont(regularFont);
                genv.registerFont(boldFont);
                Font font1 = boldFont.deriveFont(24f);
                Font font2 = regularFont.deriveFont(30f);
                rank1Button.setFont(font1);
                rank2Button.setFont(font1);
                rank3Button.setFont(font1);
                whatRankLabel.setFont(font2.deriveFont(Font.ITALIC));
            } catch (Exception ex) {

            }

            ranksPanel.add(Box.createHorizontalGlue());
            ranksPanel.add(rank1Button);
            ranksPanel.add(Box.createHorizontalGlue());
            ranksPanel.add(rank2Button);
            ranksPanel.add(Box.createHorizontalGlue());
            ranksPanel.add(rank3Button);
            ranksPanel.add(Box.createHorizontalGlue());

            contentPane.add(Box.createVerticalGlue());
            contentPane.add(whatRankLabel);
            contentPane.add(Box.createVerticalGlue());
            contentPane.add(ranksPanel);
            contentPane.add(Box.createVerticalGlue());

            upgradeChoicesFrame.setContentPane(contentPane);
            upgradeChoicesFrame.setVisible(true);
        }

        /*if (e.getSource() == endTurnButton) {
            int end;
            updateTextArea.setText(updateTextArea.getText() + "You ended your turn!\n");
            try {
                if (updateTextArea.getLineCount() == 24) {
                    end = updateTextArea.getLineEndOffset(0);
                    updateTextArea.replaceRange("", 0, end);
                }
            } catch (BadLocationException ex) {
                Logger.getLogger(GameView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/

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

    private void setFonts() {
        try {
            Font regularFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/resources/Spartan-Regular.ttf").openStream());
            Font boldFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/resources/Spartan-SemiBold.ttf").openStream());
            GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            genv.registerFont(regularFont);
            genv.registerFont(boldFont);
            Font font1 = boldFont.deriveFont(32f);
            /*Font font2 = regularFont.deriveFont(18f);
            /*updateTextArea.setFont(font2.deriveFont(Font.ITALIC));
            playerInfoLabel.setFont(font2.deriveFont(Font.ITALIC));*/
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

}
