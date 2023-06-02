package controller;

import view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.annotation.ElementType;
import java.util.*;
import java.util.List;

public class GameMode2 implements Runnable {

    private Controller controller;
    private View view;
    private Thread fingerTwisterThread;

    private Random random = new Random();

    private char[] keyboardChars = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '+', '´',
                                    'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'Å', '¨',
                                    'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Ö', 'Ä',
                                    'Z', 'X', 'C', 'V', 'B', 'N', 'M', ',', '.', '-'};
    private char[] randomChars1;
    private char[] randomChars2;
    private final List<JButton> player1ButtonArray = new ArrayList<>();
    private final List<JButton> player2ButtonArray = new ArrayList<>();
    private int index = 0;
    private boolean turn = true; // Player 1 = true && player 2 = false
    private boolean difficulty;
    private int intDifficulty;


    /**
     * Constructs a GameMode2 object.
     *
     * @param view the View object associated with the game mode
     * @param controller the Controller object associated with the game mode
     * @param difficulty the difficulty level of the game mode
     */
    public GameMode2(View view, Controller controller, boolean difficulty) {
        this.controller = controller;
        this.view = view;
        this.difficulty = difficulty;

        checkDifficulty();
        fingerTwisterThread = new Thread(this);
        fingerTwisterThread.start();
    }

    /**
     * Checks the difficulty level and sets the internal difficulty value accordingly.
     */
    private void checkDifficulty() {
        if (difficulty){
            intDifficulty = 3;

        }else{
            intDifficulty = 2;
        }
        randomChars1 = new char[this.intDifficulty];
        randomChars2 = new char[this.intDifficulty];
    }

    /**
     * Generates random characters for the first player.
     */
    public void runRandomizer() {
        randomizeChar(randomChars1, randomChars2, 1);
    }

    /**
     * Generates random characters for the second player.
     * The algorithm for increasing the index is only in this method for it should only be changed after the second players new char is generated
     */
    public void runRandomizer2(){
        randomizeChar(randomChars2, randomChars1, 2);
        index = (index + 1) % this.intDifficulty;
    }

    /**
     * Generates a random character and assigns it to the specified randomChar array.
     * It checks if the generated character already exists in the randomChar or comparisonArray.
     * If it exists, it generates another character. Once a unique character is generated, it
     * is added to the randomChar array and the corresponding button is stored in the player1ButtonArray
     * or player2ButtonArray.
     *
     * @param randomChar the array to store the generated character
     * @param comparisonArray the array to compare against for duplicate characters
     * @param arrayNumber the identifier for the player's button array (1 for player 1, 2 for player 2)
     */
    public void randomizeChar(char[] randomChar, char[] comparisonArray, int arrayNumber) {

        char newChar = keyboardChars[random.nextInt(keyboardChars.length)];
        boolean charExists = false;

        for (int i = 0; i < randomChar.length; i++) {

            if (newChar == randomChar[i] || newChar == comparisonArray[i]) {
                charExists = true;
                break;
            }
        }

        if (!charExists) {
            randomChar[index] = newChar;
            System.out.println("Array " + arrayNumber + ": " + Arrays.toString(randomChar));

        }
        else randomizeChar(randomChar, comparisonArray, arrayNumber);

        for (int i = 0 ; i < controller.getButtonArr().size() ; i++){

            if ((controller.getButtonArr().get(i).getText().charAt(0)) == newChar){

                if (arrayNumber == 1){
                    synchronized (player1ButtonArray){
                        player1ButtonArray.add(index, view.getController().getButtonArr().get(i));
                    }
                }
                else {
                    synchronized (player2ButtonArray){
                        player2ButtonArray.add(index, view.getController().getButtonArr().get(i));
                    }
                }
            }
        }
    }

    /**
     * Highlights the keys on the GUI based on the current player's turn.
     * It sets the foreground color of the player's text label and sets the background
     * color of the buttons in the corresponding player's button array.
     */
    public synchronized void lightUpKeys() {
        if (turn){
            view.getStartingPanel().getPlayer1Text().setForeground(Color.BLUE);
            view.getStartingPanel().getPlayer2Text().setForeground(null);
        }
        else {
            view.getStartingPanel().getPlayer2Text().setForeground(Color.GREEN);
            view.getStartingPanel().getPlayer1Text().setForeground(null);
        }

        synchronized (player1ButtonArray) {
            try {
                for (JButton button : player1ButtonArray) {
                    char c = button.getText().charAt(0);
                    if (containsChar(randomChars1, c)) {
                        button.setBackground(Color.BLUE);
                    } else button.setBackground(null);
                }
                view.getGamePanel().repaint();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        synchronized (player2ButtonArray) {
            try {
                for (JButton button : player2ButtonArray) {
                    char c = button.getText().charAt(0);
                    if (containsChar(randomChars2, c)) {
                        button.setBackground(Color.GREEN);
                    } else button.setBackground(null);
                }
                view.getGamePanel().repaint();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Checks if a character is present in the specified array.
     *
     * @param array the array to check
     * @param c the character to search for
     * @return true if the character is found, false otherwise
     */
    private boolean containsChar(char[] array, char c) {
        for (char ch : array) {
            if (ch == c) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void run() {
        runRandomizer();
        while(true){
            lightUpKeys();
        }
    }


    public char[] getRandomChars1() {
        return randomChars1;
    }
    public char[] getRandomChars2() {
        return randomChars2;
    }


    public boolean isTurn() {
        return turn;
    }
    public void setTurn(boolean turn) {
        this.turn = turn;
    }

}


