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
public class TakeRoleChoicesFrame extends JFrame {

    private JPanel contentPane;
    private JLabel whatRoleLabel;
    private JPanel rolesPanel;
    private JButton[] roleButtons;

    public TakeRoleChoicesFrame() {
        roleButtons = new JButton[Board.getInstance().getScene(TurnManager.getInstance().getActivePlayer().getLocation()).getOffCardRoles().size() + Board.getInstance().getScene(TurnManager.getInstance().getActivePlayer().getLocation()).getCard().getRoles().size()];
        centreWindow(this);

        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

        whatRoleLabel = new JLabel();
        whatRoleLabel.setText("What role?");
        whatRoleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        rolesPanel = new JPanel();
        rolesPanel.setLayout(new BoxLayout(rolesPanel, BoxLayout.PAGE_AXIS));

        setJButtons();
        rolesPanel.add(Box.createVerticalGlue());

        contentPane.add(Box.createVerticalGlue());
        contentPane.add(whatRoleLabel);
        contentPane.add(Box.createVerticalGlue());
        contentPane.add(rolesPanel);
        contentPane.add(Box.createVerticalGlue());

        setContentPane(contentPane);
        setVisible(true);
    }

    private void setJButtons() {
        final JFrame frame = this;
        int i;

        for (i = 0; i < Board.getInstance().getScene(TurnManager.getInstance().getActivePlayer().getLocation()).getOffCardRoles().size(); i++) {
            final int index = i;
            final Role role = Board.getInstance().getScene(TurnManager.getInstance().getActivePlayer().getLocation()).getOffCardRoles().get(i);
            roleButtons[i] = new JButton(role.getName() + " - rank " + role.getRank());
            roleButtons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            roleButtons[i].addActionListener((new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {           
                    Controller.getInstance().takeRole(role);
                    InfoPanel.getInstance().setPlayerInfoData(TurnManager.getInstance().getActivePlayer());
                    BoardPane.getInstance().movePlayerLabel(TurnManager.getInstance().getActivePlayerID(), role.getXCoordinates()+3, role.getYCoordinates()+3);
                    InfoPanel.getInstance().setUpdateTextArea("You took a role !\n");
                    
                    frame.dispose();
                }
            }));
            rolesPanel.add(Box.createVerticalGlue());
            rolesPanel.add(roleButtons[i]);
            setFonts(roleButtons[i]);

        }

        for (int j = 0; j < Board.getInstance().getScene(TurnManager.getInstance().getActivePlayer().getLocation()).getCard().getRoles().size(); j++) {
            final int index = j;
            final Role role = Board.getInstance().getScene(TurnManager.getInstance().getActivePlayer().getLocation()).getCard().getRoles().get(j);
            roleButtons[i] = new JButton(role.getName() + " - rank " + role.getRank());
            roleButtons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            roleButtons[i].addActionListener((new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    //BoardPane.getInstance().movePlayerToStarringRole(Board.getInstance().getScene(TurnManager.getInstance().getActivePlayer().getLocation()).getCard().getRoles().get(index));
                    //InfoPanel.getInstance().setUpdateTextArea("You took a role !\n");               
                    Controller.getInstance().takeRole(role);
                    InfoPanel.getInstance().setPlayerInfoData(TurnManager.getInstance().getActivePlayer());
                    BoardPane.getInstance().movePlayerToStarringRole(TurnManager.getInstance().getActivePlayerID(),TurnManager.getInstance().getActivePlayer().getLocation(), role.getXCoordinates()+3, role.getYCoordinates()+3);
                    InfoPanel.getInstance().setUpdateTextArea("You took a role !\n");
                    
                    frame.dispose();
                }
            }));
            rolesPanel.add(Box.createVerticalGlue());
            rolesPanel.add(roleButtons[i]);
            setFonts(roleButtons[i]);
            i++;
        }
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
            whatRoleLabel.setFont(font2.deriveFont(Font.ITALIC));
        } catch (Exception ex) {

        }
    }

}
