package controller;
import view.*;
import model.*;




//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

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

public class Controller implements ActionListener, Runnable {
    private final String[][] arr = {
            {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "+", "´",},
            {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "Å", "¨", "'"},
            {"A", "S", "D", "F", "G", "H", "J", "K", "L", "Ö", "Ä"},
            {"Z", "X", "C", "V", "B", "N", "M", ",", ".", "-"}};
    private View view ;
    private ArrayList<JButton> buttonArr = new ArrayList<>();
    private JButton litButton1;
    private JButton litButton2;
    private Timer timer = new Timer(5000, this);
    private Thread thread;
    //private StartingWindow startingWindow;
    private Scoreboard scoreBoard;
    // private ScoreBoardViewer scoreBoard;

    private int keyCount;
    private GameMode2 gm2;
    private boolean gamemode;//If this is true, gamemode is fingertwister/Multiplayer, if false, gamemode is singleplayer

    public Controller(boolean gamemode) {
        this.gamemode = gamemode;
        this.view = new View(this);
    }

    public void startGame() {
        this.litButton1 = this.randomize_new_button();
        this.litButton2 = this.randomize_new_button();
        view.getCountDownPanel().startGameTimer();

        while(Objects.equals(this.litButton2.getText(), this.litButton1.getText())){
            this.litButton2 = this.randomize_new_button();
            this.litButton1 = this.randomize_new_button();
        }
    }
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


    public Timer getTimer() {
        return this.timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public void setNewScore(String name, int score) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("ScoreBoard.txt", true));
        String playerNameScore = String.format(" Name: %s Score: %d", name, score);
        bufferedWriter.write(playerNameScore);
        bufferedWriter.newLine();
        bufferedWriter.close();
    }
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

    public String[][] getArr() {
        return arr;
    }

    public ArrayList<JButton> getButtonArr() {
        return this.buttonArr;
    }

    public void setButtonArr(ArrayList<JButton> buttonArr) {
        this.buttonArr = buttonArr;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Timer) {
            this.view.setTimesUp(true);
        }

    }
    public void run() {
        this.startGame();
    }

    public Thread getThread() {
        return this.thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public void nextGame() {
        this.view = new View(this);
    }

    public JButton getLitButton1() {
        return this.litButton1;
    }

    public JButton getLitButton2() {
        return this.litButton2;
    }


    public int getKeyCount() {
        return keyCount;
    }

    public void setKeyCount(int keyCount) {
        this.keyCount = keyCount;
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

    public void setGamemode(boolean gamemode) {
        this.gamemode = gamemode;
    }
}
