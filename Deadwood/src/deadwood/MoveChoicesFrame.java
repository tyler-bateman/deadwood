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
public class MoveChoicesFrame extends JFrame {

    private JPanel contentPane;
    private JLabel whereToLabel;
    private JPanel scenesPanel;
    private JButton[] sceneButtons;

    public MoveChoicesFrame() {

        sceneButtons = new JButton[Board.getInstance().getSpace(TurnManager.getInstance().getActivePlayer().getLocation()).getAdjacentSpaces().size()];
        centreWindow(this);

        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

        whereToLabel = new JLabel();
        whereToLabel.setText("Where to ?");
        whereToLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        scenesPanel = new JPanel();
        scenesPanel.setLayout(new BoxLayout(scenesPanel, BoxLayout.X_AXIS));

        setJButtons();
        scenesPanel.add(Box.createHorizontalGlue());

        contentPane.add(Box.createVerticalGlue());
        contentPane.add(whereToLabel);
        contentPane.add(Box.createVerticalGlue());
        contentPane.add(scenesPanel);
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
            whereToLabel.setFont(font2.deriveFont(Font.ITALIC));
        } catch (Exception ex) {

        }
    }

    private void setJButtons() {
        final JFrame frame = this;
        for (int i = 0; i < sceneButtons.length; i++) {
            final int index = i;
            sceneButtons[i] = new JButton(Board.getInstance().getSpace(TurnManager.getInstance().getActivePlayer().getLocation()).getAdjacentSpaces().get(i).getName());
            sceneButtons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            sceneButtons[i].addActionListener((new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    /*BoardPane.getInstance().movePlayerLabelToScene(Board.getInstance().getSpace(TurnManager.getInstance().getActivePlayer().getLocation()).getAdjacentSpaces().indexOf(Board.getInstance().getSpace(TurnManager.getInstance().getActivePlayer().getLocation()).getAdjacentSpaces().get(index)));
                    InfoPanel.getInstance().setUpdateTextArea("Your have moved !\n");*/
                    Controller.getInstance().move(Board.getInstance().getSpace(TurnManager.getInstance().getActivePlayer().getLocation()).getAdjacentSpaces().get(index).getID());
                    frame.dispose();
                }
                }));
            scenesPanel.add(Box.createHorizontalGlue());
            scenesPanel.add(sceneButtons[i]);
            setFonts(sceneButtons[i]);

        }
    }
    
    

}
