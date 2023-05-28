package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class ScoreboardPanel extends JPanel implements Runnable {
    private View view;
    private JTextArea textArea;
    private int count;
    private Scoreboard scoreboard;

    public ScoreboardPanel(View view) throws HeadlessException {
        this.view = view;
        this.textArea = new JTextArea();

        JLabel scoreBoardText = new JLabel("Scoreboard");
        Font font = new Font("BOLD", Font.BOLD, 20);
        scoreBoardText.setFont(font);
        scoreBoardText.setBackground(null);
        this.add(scoreBoardText, BorderLayout.NORTH);

        textArea.setPreferredSize(new Dimension(400,1100));
        Font bigFont = new Font("Italic", Font.ITALIC, 12);
        textArea.setFont(bigFont);
        textArea.setBackground(null);

        this.textArea.setEditable(false);
        this.add(this.textArea);
        scoreboard = new Scoreboard();

        try {
            this.setTextArea();
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        this.setVisible(true);

        Thread thread = new Thread(this);
        thread.start();
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
