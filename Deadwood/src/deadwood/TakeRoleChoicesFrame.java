
package deadwood;

import static deadwood.ActionsPanel.centreWindow;
import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.LinkedList;

/**
 * TakeRoleChoicesFrame displays the available roles that the player can take
 *
 * @author Curveball
 */
public class TakeRoleChoicesFrame extends JFrame {

    private JPanel contentPane;
    private JLabel whatRoleLabel;
    private JPanel rolesPanel;
    private JButton[] roleButtons;

    /**
     * Constructor for TakeRoleChoicesFrame
     *
     * @param roles
     */
    public TakeRoleChoicesFrame(LinkedList<Role> roles) {
        roleButtons = new JButton[roles.size()];
        centreWindow(this);

        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

        whatRoleLabel = new JLabel();
        whatRoleLabel.setText("What role?");
        whatRoleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        rolesPanel = new JPanel();
        rolesPanel.setLayout(new BoxLayout(rolesPanel, BoxLayout.PAGE_AXIS));

        setJButtons(roles);
        rolesPanel.add(Box.createVerticalGlue());

        contentPane.add(Box.createVerticalGlue());
        contentPane.add(whatRoleLabel);
        contentPane.add(Box.createVerticalGlue());
        contentPane.add(rolesPanel);
        contentPane.add(Box.createVerticalGlue());

        setContentPane(contentPane);
        setVisible(true);
    }

    /**
     * sets the buttons and displays the available roles on them
     *
     * @param roles list of available roles
     */
    private void setJButtons(LinkedList<Role> roles) {
        final JFrame frame = this;
        int i;

        for (i = 0; i < roles.size(); i++) {
            final int index = i;
            final Role role = roles.get(i);
            roleButtons[i] = new JButton(role.getName() + " - rank " + role.getRank());
            roleButtons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            roleButtons[i].addActionListener((new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    Controller.getInstance().takeRole(role);
                    InfoPanel.getInstance().setUpdateTextArea("You took a role !\n");

                    frame.dispose();
                }
            }));
            rolesPanel.add(Box.createVerticalGlue());
            rolesPanel.add(roleButtons[i]);
            setFonts(roleButtons[i]);

        }
    }

    /**
     * sets the Spartan fonts on all components of TakeRoleChoicesFrame
     *
     * @param b
     */
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
