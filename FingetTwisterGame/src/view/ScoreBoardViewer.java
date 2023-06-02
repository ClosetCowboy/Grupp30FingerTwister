package view;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import model.Scoreboard;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ScoreBoardViewer extends JFrame implements Runnable {

    /**
     * The main method creates a new instance of ScoreBoardViewer.
     *
     * @param args the command-line arguments
     */
    public static void main(String[]args){
        new ScoreBoardViewer();
    }
    private View view;
    private Scoreboard scoreboard;
    private JPanel panel;
    private JTextArea textArea;
    private int count;

    /**
     * Constructs a ScoreBoardViewer object.
     *
     * @throws HeadlessException if the graphics environment is not available
     */
    public ScoreBoardViewer() throws HeadlessException {
        this.view = view;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.panel = new JPanel();
        this.textArea = new JTextArea();
        this.textArea.setEditable(false);
        this.panel.add(this.textArea);
        this.setLocationRelativeTo(null);

        try {
            this.setTextArea();
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        this.setSize(250, 25 * this.count);
        this.add(this.panel);
        this.setVisible(true);

        Thread thread = new Thread(this);
        thread.start();
    }

    /**
     * Sets the text area with the content of the scoreboard.
     *
     * @throws IOException if an I/O error occurs while reading the scoreboard
     */
    public void setTextArea() throws IOException {
        String name;
        String score;
        String textfield = " ";
        for(int i = 0; i<scoreboard.getScoreBoard().size(); i++){
            name = scoreboard.getScoreBoard().get(i).getName();
            System.out.println("ScoreboardPanel");
            System.out.println(name);
            score = String.valueOf(scoreboard.getScoreBoard().get(i).getScore());
            System.out.println(score);
            textfield = textfield + String.format("%12s, %10s", name , score) + "\n";
        }
        this.textArea.setText(textfield);
        System.out.println("TextField" + "\n" + textfield);
    }

    /**
     * Runs a continuous loop to update the text area periodically.
     */
    public void run() {
        FileReader fileReader = null;

        while(true) {
            String str = "";
            this.count = 0;
            try {
                fileReader = new FileReader("ScoreBoard.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                for (int i = 0; i < bufferedReader.read(); ++i) {

                    str = str + bufferedReader.readLine() + "\n";
                    ++this.count;
                }

                fileReader.close();
                bufferedReader.close();
            } catch (IOException var5) {
                var5.printStackTrace();
            }
            this.setSize(250, 24 * this.count);
            this.textArea.setText(str);

        }
    }
}
