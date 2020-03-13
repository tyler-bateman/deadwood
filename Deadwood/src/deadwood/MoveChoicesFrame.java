
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
 * MoveChoicesFrame displays the available spaces that the player can move to
 *
 * @author Curveball
 */
public class MoveChoicesFrame extends JFrame {

    private JPanel contentPane;
    private JLabel whereToLabel;
    private JPanel scenesPanel;
    private JButton[] sceneButtons;

    /**
     * Constructor to initialise the buttons and the labels
     *
     * @param spaces list of spaces to be displayed by the buttons
     */
    public MoveChoicesFrame(LinkedList<Space> spaces) {

        sceneButtons = new JButton[spaces.size()];
        centreWindow(this);

        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

        whereToLabel = new JLabel();
        whereToLabel.setText("Where to ?");
        whereToLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        scenesPanel = new JPanel();
        scenesPanel.setLayout(new BoxLayout(scenesPanel, BoxLayout.X_AXIS));

        setJButtons(spaces);
        scenesPanel.add(Box.createHorizontalGlue());

        contentPane.add(Box.createVerticalGlue());
        contentPane.add(whereToLabel);
        contentPane.add(Box.createVerticalGlue());
        contentPane.add(scenesPanel);
        contentPane.add(Box.createVerticalGlue());

        setContentPane(contentPane);
        setVisible(true);
    }

    /**
     * sets the font of the given component
     *
     * @param b the button to be given the Spartan font
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
            whereToLabel.setFont(font2.deriveFont(Font.ITALIC));
        } catch (Exception ex) {

        }
    }

    /**
     * sets the buttons and displays the available spaces on them
     *
     * @param spaces the spaces to be displayed on the scene buttons
     */
    private void setJButtons(LinkedList<Space> spaces) {
        final JFrame frame = this;
        for (int i = 0; i < sceneButtons.length; i++) {
            Space s = spaces.get(i);
            final int index = i;
            sceneButtons[i] = new JButton(s.getName());
            sceneButtons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            sceneButtons[i].addActionListener((new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    Controller.getInstance().move(s.getID());
                    InfoPanel.getInstance().setUpdateTextArea("Your have moved !\n");
                    frame.dispose();
                }
            }));
            scenesPanel.add(Box.createHorizontalGlue());
            scenesPanel.add(sceneButtons[i]);
            setFonts(sceneButtons[i]);

        }
    }

}
