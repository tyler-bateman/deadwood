/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadwood;

import java.awt.Dimension;
import javax.swing.*;

/**
 *
 * @author Curveball
 */
public class BoardPane extends JLayeredPane {
    private ImageIcon boardIcon;
    private JLabel boardLabel;
    
    BoardPane(int height, int width, ImageIcon boardIcon) {
        
        this.boardIcon = boardIcon;
        setLayout(null);
        setPreferredSize(new Dimension(900, 1200));
        boardLabel = new JLabel();
        boardLabel.setIcon(boardIcon);
        boardLabel.setBounds(0, 0, boardIcon.getIconWidth(), boardIcon.getIconHeight());
        add(boardLabel, new Integer(0));
    }
    
}
