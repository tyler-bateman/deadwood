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
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author tyler
 */
public class LaunchView extends JFrame implements ActionListener {

    private JPanel launchPanel;
    private JLabel welcomeLabel;
    private JLabel promptLabel;
    private JList numOfPlayerList;
    private JButton playButton;

    public LaunchView() throws IOException {
        super("Deadwood");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        launchPanel = new JPanel();
        launchPanel.setBackground(Color.white);
        launchPanel.setLayout(new BoxLayout(launchPanel, BoxLayout.Y_AXIS));
        add(launchPanel);

        welcomeLabel = new JLabel();
        welcomeLabel.setText("Welcome to Deadwood !");

        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        launchPanel.add(welcomeLabel);
        launchPanel.add(Box.createVerticalGlue());

        promptLabel = new JLabel();
        promptLabel.setText("Please choose the number of players");
        promptLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        launchPanel.add(promptLabel);
        launchPanel.add(Box.createVerticalGlue());

        String num[] = {"2", "3", "4", "5", "6", "7", "8"};

        DefaultListModel model = new DefaultListModel();
        for (int i = 2; i < 9; i++) {
            model.addElement(i);
        }

        numOfPlayerList = new JList(model);
        numOfPlayerList.setModel(model);
        numOfPlayerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        numOfPlayerList.setLayoutOrientation(JList.VERTICAL);
        numOfPlayerList.setVisibleRowCount(0);
        JScrollPane listScroller = new JScrollPane(numOfPlayerList);
        listScroller.setAlignmentX(Component.CENTER_ALIGNMENT);

        launchPanel.add(listScroller);
        launchPanel.add(Box.createVerticalGlue());

        playButton = new JButton();
        playButton.setText("Play");
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.addActionListener(this);
        setFonts();

        launchPanel.add(playButton);
        launchPanel.add(Box.createVerticalGlue());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height / 3;
        int width = screenSize.width / 3;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(width, height));
        centreWindow(this);
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/DeadwoodIcon.PNG"));
        setIconImage(image);
        this.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        super.dispose();

        new GameView();

    }

    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
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
            welcomeLabel.setFont(font1.deriveFont(Font.ITALIC));
            promptLabel.setFont(font2);
            playButton.setFont(font1);
            numOfPlayerList.setFont(font2);
        } catch (Exception e) {

        }
    }

}
