package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu {
    private View view;
    public StartMenu() {
        JFrame frame = new JFrame();
        frame.setLayout(null);

        JButton multiplayerButton = new JButton("Multiplayer");
        multiplayerButton.setBounds(240, 270, 600, 80);
        multiplayerButton.setFont(new Font("SansSerif", Font.PLAIN, 34));
        frame.add(multiplayerButton);

        JButton singlePlayerButton = new JButton("Singleplayer");
        singlePlayerButton.setBounds(240, 650, 600, 80);
        singlePlayerButton.setFont(new Font("SansSerif", Font.PLAIN, 34));
        frame.add(singlePlayerButton);

        JLabel titel = new JLabel("FingerTwister");
        Font font = new Font("SansSerif", Font.PLAIN, 100);
        titel.setFont(font);
        titel.setBounds(700, 40, 800 , 120);
        frame.add(titel);

        JLabel scoreboardTitle = new JLabel("Scoreboard");
        Font scoreboardfont = new Font("SansSerif", Font.PLAIN, 50);
        scoreboardTitle.setFont(scoreboardfont);
        scoreboardTitle.setBounds(1470, 170, 400, 50);
        frame.add(scoreboardTitle);


        JTextArea scoreboard = new JTextArea();
        scoreboard.setBounds(1400, 230, 400, 700);
        frame.add(scoreboard);





        JLabel multiPlayerDescription = new JLabel(
                "<html>In this version of Twister, you will play against one opponent.<br>"
                        + "The game will prompt you to press various keys on your keyboard that correspond to different body parts and colors.<br>"
                        + "For example, you will both be instructed to place your finger on the key glowing in your color, whoever last the longest win!<br>"
                        + "So get ready to have ur fingers twisted ");

        multiPlayerDescription.setBounds(240, 320, 600, 200);
        multiPlayerDescription.setFont(new Font("SansSerif", Font.PLAIN, 16));
        frame.add(multiPlayerDescription);

        JLabel singlePlayerDescription = new JLabel(
                "<html>With simple gameplay and a fast-paced soundtrack, Keyboard Rush is a fun and addictive game that will test your typing skills and reflexes.<br>"
                        + "So, get ready to hit the keys and try to top the leaderboard!");

        singlePlayerDescription.setBounds(240, 670, 600, 200);
        singlePlayerDescription.setFont(new Font("SansSerif", Font.PLAIN, 16));
        frame.add(singlePlayerDescription);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        multiplayerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent multiPlayerPressed) {
                frame.setVisible(false);
                new Controller(true);
                //new VersionPanel(true); ToDo Detta ska tillbaka senare!!!
            }
        });

        singlePlayerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent singlePlayerPressed) {
                frame.setVisible(false);
                new Controller(false);
                //new VersionPanel(false); ToDo Detta ska tillbaka senare!!!
            }
        });
    }
}
