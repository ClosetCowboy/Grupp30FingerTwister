package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class ScoreboardPanel extends JPanel implements Runnable {
    private View view;
    private JTextArea textArea;
    private Scoreboard scoreboard;
    private JLabel spaceBeforeScoreboard;

    public ScoreboardPanel(View view) throws HeadlessException {
        this.view = view;
        this.textArea = new JTextArea();

        textArea.setPreferredSize(new Dimension(400,1100));
        Font bigFont = new Font("BOLD", Font.BOLD, 30);
        textArea.setFont(bigFont);
        textArea.setBackground(null);

        this.textArea.setEditable(false);
        this.add(this.textArea);
        scoreboard = new Scoreboard();
        addSpaceInBetween();

        try {
            this.setTextArea();
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        this.setVisible(true);

        Thread thread = new Thread(this);
        thread.start();
    }

    private void addSpaceInBetween() {
        spaceBeforeScoreboard = new JLabel();
        spaceBeforeScoreboard.setPreferredSize(new Dimension(50,100));
        this.add(spaceBeforeScoreboard);
    }


    public void setTextArea() throws IOException {

        scoreboard = view.getController().getScoreBoard();
        String name;
        String score;
        String textfield = " ";
        for(int i=0; i<scoreboard.getScoreboard().size(); i++){
            name = scoreboard.getScoreboard().get(i).getName();
            //  System.out.println("ScoreboardPanel");
            //  System.out.println(name);
            score = String.valueOf(scoreboard.getScoreboard().get(i).getScore());
            // System.out.println(score);
            // textfield = textfield + "Name: " + name + "Score: " + score + "\n";
            textfield = textfield + String.format("%12s, %10s", name , score) + "\n";
        }
        this.textArea.setText(textfield);
        //System.out.println("TextField" + "\n" + textfield);
    }

    public void run() {

        while(true) {
            try {
                setTextArea();
                Thread.sleep(100);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }
    }
}
