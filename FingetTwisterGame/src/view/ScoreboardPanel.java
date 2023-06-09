package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class ScoreboardPanel extends JPanel implements Runnable {
    private final View view;
    private Scoreboard scoreboard;
    private JTextArea textArea;
    private JLabel spaceBeforeScoreboard;

    /**
     * Constructs a ScoreboardPanel object with the specified view.
     *
     * @param view the view object
     * @throws HeadlessException if the graphics environment is not available
     */
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

    /**
     * Adds space before the scoreboard.
     */
    private void addSpaceInBetween() {
        spaceBeforeScoreboard = new JLabel();
        spaceBeforeScoreboard.setPreferredSize(new Dimension(100,100));
        this.add(spaceBeforeScoreboard);
    }

    /**
     * Sets the text area with the content of the scoreboard.
     *
     * @throws IOException if an I/O error occurs while reading the scoreboard
     */
    public void setTextArea() throws IOException {

        scoreboard = view.getController().getScoreBoard();
        String name;
        String score;
        String textfield = " ";
        for(int i = 0; i < scoreboard.getScoreBoard().size(); i++){
            name = scoreboard.getScoreBoard().get(i).getName();

            score = String.valueOf(scoreboard.getScoreBoard().get(i).getScore());

            textfield = textfield + String.format("%12s, %10s", name , score) + "\n";
        }
        this.textArea.setText(textfield);
    }

    public void run() {

        while(true) {
            try {
                setTextArea();
                Thread.sleep(100);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
