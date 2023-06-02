package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VersionPanel extends JFrame implements ActionListener {
    private JButton rgbButton;
    private JButton defaultButton;
    private boolean gameMode;
    private boolean difficulty = false;

    /**
     * Constructs a VersionPanel object.
     * @param gameMode the game mode (multiplayer or singleplayer)
     * @param difficulty the difficulty level
     */
    public VersionPanel(boolean gameMode, boolean difficulty) {
        this.gameMode = gameMode;
        this.difficulty = difficulty;
        setTitle("Home Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        rgbButton = new JButton("RGB-Version");
        rgbButton.setBounds(350,250,500,150);
        rgbButton.setFont(new Font("SansSerif", Font.PLAIN, 40));

        defaultButton = new JButton("Default-Version");
        defaultButton.setBounds(1100,250,500,150);
        defaultButton.setFont(new Font("SansSerif", Font.PLAIN, 40));

        rgbButton.addActionListener(this);
        defaultButton.addActionListener(this);

        panel.add(rgbButton);
        panel.add(defaultButton);

        this.add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == rgbButton)   {
            new Controller(gameMode, difficulty);
            this.setVisible(false);
        }
        if (e.getSource() == defaultButton){
            new Controller(gameMode, difficulty);
            this.setVisible(false);
        }
    }
}
