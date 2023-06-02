package controller;
import view.*;
import model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.Timer;
/**
 * The Controller class handles the logic and interactions between the View and Model in the game.
 * It implements the ActionListener and Runnable interfaces.
 */
public class Controller implements ActionListener, Runnable {
    private final String[][] arr = {
            {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "+", "´",},
            {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "Å", "¨", "'"},
            {"A", "S", "D", "F", "G", "H", "J", "K", "L", "Ö", "Ä"},
            {"Z", "X", "C", "V", "B", "N", "M", ",", ".", "-"}};
    private View view ;
    private Scoreboard scoreBoard;
    private GameMode2 gm2;
    private ArrayList<JButton> buttonArr = new ArrayList<>();
    private JButton litButton1;
    private JButton litButton2;
    private int keyCount;
    private boolean gamemode;//If this is true, gamemode is fingertwister/Multiplayer, if false, gamemode is singleplayer
    private final boolean difficulty;


    /**
     * Constructs a Controller object.
     *
     * @param gamemode   true for fingertwister/Multiplayer mode, false for singleplayer mode.
     * @param difficulty the difficulty level of the game.
     */
    public Controller(boolean gamemode, boolean difficulty) {
        this.gamemode = gamemode;
        this.view = new View(this);
        this.difficulty = difficulty;
    }

    /**
     * Starts the game by initializing the lit buttons and starting the game timer.
     * It ensures that the two lit buttons have different text values.
     */
    public void startGame() {
        this.litButton1 = this.randomize_new_button();
        this.litButton2 = this.randomize_new_button();
        view.getCountDownPanel().startGameTimer();

        while(Objects.equals(this.litButton2.getText(), this.litButton1.getText())){
            this.litButton2 = this.randomize_new_button();
            this.litButton1 = this.randomize_new_button();
        }
    }

    /**
     * Randomly selects a new button from the available buttons and returns it.
     *
     * @return the randomly selected button.
     */
    public JButton randomize_new_button() {
        JButton jButton = null;
        Random random = new Random();
        int randomInt = random.nextInt(this.arr.length - 1);
        String randomLetter = "";

        for (int i = 0 ; i < arr.length -1; i++){

            int randomInt2 = random.nextInt(this.arr[i].length - 1);
            randomLetter = this.arr[randomInt][randomInt2];
        }

        for (JButton button : buttonArr) {
            if (Objects.equals(button.getText(), randomLetter)) {
                view.getGamePanel().makeLitButton(button);
                jButton = button;
                break;
            }
        }
        return jButton;
    }

    /**
     * Writes the player's name and score to the ScoreBoard.txt file.
     *
     * @param name  the name of the player.
     * @param score the score achieved by the player.
     * @throws IOException if an I/O error occurs while writing to the file.
     */
    public void setNewScore(String name, int score) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("ScoreBoard.txt", true));
        String playerNameScore = String.format(" Name: %s Score: %d", name, score);
        bufferedWriter.write(playerNameScore);
        bufferedWriter.newLine();
        bufferedWriter.close();
    }

    /**
     * Updates the lit buttons when a button is clicked.
     *
     * @param button the button that was clicked.
     */
    public void newButton(JButton button) {
        if (button == this.litButton1) {
            this.litButton1 = this.randomize_new_button();
        }

        if (button == this.litButton2) {
            this.litButton2 = this.randomize_new_button();
        }

    }

    public void newScoreboard(){
        scoreBoard = new Scoreboard();
    }
    public void newScore(String name, int score) throws IOException {

        scoreBoard.setNewScore(name, score);
    }

    public Scoreboard getScoreBoard(){
        if(scoreBoard == null){
            newScoreboard();
        }
        return scoreBoard;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Timer) {
            this.view.setTimesUp(true);
        }

    }

    public void run() {
        this.startGame();
    }

    public String[][] getArr() {
        return arr;
    }

    public ArrayList<JButton> getButtonArr() {
        return this.buttonArr;
    }




    public GameMode2 getGm2() {
        return this.gm2;
    }
    public void setGm2(GameMode2 gm2) {
        this.gm2 = gm2;
    }

    public boolean isGamemode() {
        return gamemode;
    }

    public int getKeyCount() {
        return keyCount;
    }
    public void setKeyCount(int keyCount) {
        this.keyCount = keyCount;
    }

    public JButton getLitButton1() {
        return this.litButton1;
    }
    public JButton getLitButton2() {
        return this.litButton2;
    }

    public boolean isDifficulty() {
        return difficulty;
    }

}
