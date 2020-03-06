/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadwood;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author Curveball
 */
public class GameView extends JFrame implements ActionListener {

    private JPanel jpanel1;
    private JLayeredPane boardPane;
    private JLabel boardLabel;
    private JPanel infoPanel;
    private JLabel UpdateLabel;
    private JLabel playerInfoLabel;
    private JPanel actionsPanel;

    private JButton moveButton;
    private JButton takeRoleButton;
    private JButton actButton;
    private JButton rehearseButton;
    private JButton upgradeButton;
    private JButton endTurnButton;

    public GameView() {
        super("Deadwood");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        ImageIcon boardIcon = new ImageIcon(getClass().getResource("/resources/board.jpg"));

        jpanel1 = new JPanel();
        jpanel1.setLayout(new BorderLayout());
        add(jpanel1);

        infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoPanel.setBackground(Color.red);
        infoPanel.setPreferredSize(new Dimension(((width - boardIcon.getIconWidth()) / 2), 0));

        UpdateLabel = new JLabel();
        UpdateLabel.setBackground(Color.white);
        UpdateLabel.setOpaque(true);
        UpdateLabel.setText("Display history of player choices");
        UpdateLabel.setPreferredSize(new Dimension((width - boardIcon.getIconWidth()), height / 2));
        UpdateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        UpdateLabel.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        infoPanel.add(UpdateLabel, BorderLayout.NORTH);

        playerInfoLabel = new JLabel();
        playerInfoLabel.setBackground(Color.white);
        playerInfoLabel.setOpaque(true);
        playerInfoLabel.setText("Display active player's info ");

        playerInfoLabel.setPreferredSize(new Dimension((width - boardIcon.getIconWidth()), height / 2));
        playerInfoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerInfoLabel.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        infoPanel.add(playerInfoLabel, BorderLayout.SOUTH);

        actionsPanel = new JPanel();
        actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.PAGE_AXIS));
        actionsPanel.setBackground(Color.white);
        actionsPanel.setPreferredSize(new Dimension(((width - boardIcon.getIconWidth()) / 2), 0));

        actionsPanel.add(Box.createRigidArea(new Dimension(height / 15, width / 15)));
        setJButtons();
        setFonts();
        actionsPanel.add(Box.createRigidArea(new Dimension(height / 15, width / 15)));
        actionsPanel.add(Box.createVerticalGlue());
        actionsPanel.setBorder(BorderFactory.createLineBorder(Color.black, 3));

        boardPane = new JLayeredPane();
        boardPane.setLayout(null);
        boardPane.setPreferredSize(new Dimension(900, 1200));
        boardLabel = new JLabel();
        boardLabel.setIcon(boardIcon);
        boardLabel.setBounds(0, 0, boardIcon.getIconWidth(), boardIcon.getIconHeight());
        boardPane.add(boardLabel, new Integer(0));

        jpanel1.add(actionsPanel, BorderLayout.WEST);
        jpanel1.add(boardPane, BorderLayout.CENTER);
        jpanel1.add(infoPanel, BorderLayout.EAST);

        Demo();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(jpanel1);
        this.setMaximumSize(screenSize);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/DeadwoodIcon.PNG"));
        setIconImage(image);
        this.setVisible(true);
        //this.pack();

    }

    private void Demo() {
        Board board = Board.getInstance();
        Deck deck = Deck.getInstance();

        ////DEALING CARDBACKS DEMO
        for (int i = 0; i < board.getScenes().length; i++) {
            JLabel backlabel = new JLabel();
            ImageIcon backIcon = new ImageIcon(getClass().getResource("/resources/CardBack.jpg"));
            backlabel.setIcon(backIcon);
            backlabel.setBounds(board.getScene(i).getXCoordinates(), board.getScene(i).getYCoordinates(), backIcon.getIconWidth(), backIcon.getIconHeight());
            backlabel.setOpaque(false);

            boardPane.add(backlabel, new Integer(1));

            /// DEALING CARD FACES DEMO
            JLabel cardlabel = new JLabel();
            SceneCard card = deck.getCards().pop();
            String cardImage = card.getIconID();
            ImageIcon cIcon = new ImageIcon(getClass().getResource("/resources/" + cardImage));
            cardlabel.setIcon(cIcon);
            cardlabel.setBounds(board.getScene(i).getXCoordinates(), board.getScene(i).getYCoordinates(), cIcon.getIconWidth(), cIcon.getIconHeight());
            cardlabel.setOpaque(false);

            boardPane.add(cardlabel, new Integer(2));

            /// SETTING SHOT COUNTERS
            for (int j = 0; j < board.getScene(i).getShotCountersXCoordinates().size(); j++) {
                JLabel shotCounterLabel = new JLabel();
                ImageIcon shotCounterIcon = new ImageIcon(getClass().getResource("/resources/shot.png"));
                shotCounterLabel.setIcon(shotCounterIcon);
                shotCounterLabel.setBounds(board.getScene(i).getShotCountersXCoordinates().get(j), board.getScene(i).getShotCountersYCoordinates().get(j), shotCounterIcon.getIconWidth(), shotCounterIcon.getIconHeight());
                shotCounterLabel.setOpaque(false);
                boardPane.add(shotCounterLabel, new Integer(2));
            }

            /// MOVING PLAYER ICON TO ON CARD ROLES
            for (int m = 0; m < card.getRoles().size(); m++) {
                JLabel playerLabel1 = new JLabel();
                ImageIcon playerIcon1 = new ImageIcon(getClass().getResource("/resources/r1.png"));
                playerLabel1.setIcon(playerIcon1);
                playerLabel1.setBounds(card.getRoles().get(m).getXCoordinates(), card.getRoles().get(m).getYCoordinates(), playerIcon1.getIconWidth(), playerIcon1.getIconHeight());
                playerLabel1.setOpaque(true);
                cardlabel.add(playerLabel1);

            }

            /// MOVING PLAYER TO OFF CARD ROLE
            for (int j = 0; j < board.getScene(i).getOffCardRoles().size(); j++) {
                JLabel playerLabel = new JLabel();
                ImageIcon playerIcon = new ImageIcon(getClass().getResource("/resources/b1.png"));
                playerLabel.setIcon(playerIcon);
                playerLabel.setBounds(board.getScene(i).getOffCardRoles().get(j).getXCoordinates(), board.getScene(i).getOffCardRoles().get(j).getYCoordinates(), playerIcon.getIconWidth(), playerIcon.getIconHeight());
                playerLabel.setOpaque(false);
                boardPane.add(playerLabel, new Integer(2));
            }
        }
    }

    private void setJButtons() {
        moveButton = new JButton();
        moveButton.setText("Move");
        moveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        moveButton.addActionListener(this);
        actionsPanel.add(moveButton);
        actionsPanel.add(Box.createVerticalGlue());

        takeRoleButton = new JButton();
        takeRoleButton.setText("Take Role");
        takeRoleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        actionsPanel.add(takeRoleButton);
        actionsPanel.add(Box.createVerticalGlue());

        actButton = new JButton();
        actButton.setText("Act");
        actButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        actionsPanel.add(actButton);
        actionsPanel.add(Box.createVerticalGlue());

        rehearseButton = new JButton();
        rehearseButton.setText("Rehearse");
        rehearseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        actionsPanel.add(rehearseButton);
        actionsPanel.add(Box.createVerticalGlue());

        upgradeButton = new JButton();
        upgradeButton.setText("Upgrade");
        upgradeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        actionsPanel.add(upgradeButton);
        actionsPanel.add(Box.createVerticalGlue());

        endTurnButton = new JButton();
        endTurnButton.setText("End Turn");
        endTurnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        actionsPanel.add(endTurnButton);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == moveButton) {
            JFrame moveChoicesFrame = new JFrame();
            centreWindow(moveChoicesFrame);
            
            JPanel contentPane = new JPanel();
            contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
            

            JLabel whereToLabel = new JLabel();
            whereToLabel.setText("Where To ?");
            whereToLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel scenesPanel = new JPanel();
            scenesPanel.setLayout(new BoxLayout(scenesPanel, BoxLayout.X_AXIS));

            JButton scene1Button = new JButton("Scene 1");
            scene1Button.setAlignmentX(Component.CENTER_ALIGNMENT);
            JButton scene2Button = new JButton("Scene 2");
            scene1Button.setAlignmentX(Component.CENTER_ALIGNMENT);
            JButton scene3Button = new JButton("Scene 3");
            scene1Button.setAlignmentX(Component.CENTER_ALIGNMENT);

            scenesPanel.add(Box.createHorizontalGlue());
            scenesPanel.add(scene1Button);
            scenesPanel.add(Box.createHorizontalGlue());
            scenesPanel.add(scene2Button);
            scenesPanel.add(Box.createHorizontalGlue());
            scenesPanel.add(scene3Button);
            scenesPanel.add(Box.createHorizontalGlue());
            
            contentPane.add(Box.createRigidArea(new Dimension((moveChoicesFrame.getHeight()/5),0)));
            contentPane.add(whereToLabel);
            contentPane.add(Box.createVerticalGlue());
            contentPane.add(scenesPanel);
            contentPane.add(Box.createVerticalGlue());
            
            
            
            moveChoicesFrame.setContentPane(contentPane);           
            moveChoicesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            moveChoicesFrame.setVisible(true);

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

    private void setFonts() {
        try {
            Font regularFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/resources/Spartan-Regular.ttf").openStream());
            Font boldFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/resources/Spartan-SemiBold.ttf").openStream());
            GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            genv.registerFont(regularFont);
            genv.registerFont(boldFont);
            Font font1 = boldFont.deriveFont(32f);
            Font font2 = regularFont.deriveFont(18f);
            UpdateLabel.setFont(font2.deriveFont(Font.ITALIC));
            playerInfoLabel.setFont(font2.deriveFont(Font.ITALIC));
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

}
