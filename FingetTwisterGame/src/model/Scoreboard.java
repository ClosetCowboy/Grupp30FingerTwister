package model;

import java.io.*;
import java.util.ArrayList;

import static java.util.Collections.*;

public class Scoreboard {
    private ArrayList<Score> scoreBoard;

    /**
     * Constructs a Scoreboard object and initializes the scoreBoard ArrayList.
     * It reads scores from a file and populates the scoreBoard with the read scores.
     */
    public Scoreboard(){
        scoreBoard = new ArrayList<>();
        String name;
        int score;
        try {
            BufferedReader bufferedReader = new BufferedReader((new FileReader("ScoreBoard.txt")));
            for(int i = 0; i < bufferedReader.read(); ++i) {
                name = bufferedReader.readLine();
                //  System.out.println("ScoreBoard");
                //   System.out.println(name);

                i++;
                score = Integer.parseInt(bufferedReader.readLine());
                //  System.out.println(score);
                Score newscore = new Score(name, score);
                scoreBoard.add(newscore);
                sortScoreBoard();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds a new score to the scoreboard and writes the updated scoreboard to the file.
     *
     * @param name the name of the player
     * @param score the score achieved by the player
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public void setNewScore(String name, int score) throws IOException {
        Score newscore = new Score(name, score);
        scoreBoard.add(newscore);
        sortScoreBoard();
        writeToFile();
    }

    /**
     * Writes the scores from the scoreboard to a file.
     *
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public void writeToFile() throws IOException {
        String playerName;
        String playerScore;

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("ScoreBoard.txt", false));
        for (Score score : scoreBoard) {
            playerName = score.getName();
            playerScore = String.valueOf(score.getScore());
            bufferedWriter.write(" " + playerName);
            bufferedWriter.newLine();
            bufferedWriter.write(playerScore);
            bufferedWriter.newLine();

        }
        bufferedWriter.close();
    }

    public ArrayList<Score> getScoreBoard(){
        return scoreBoard;
    }
    public void sortScoreBoard(){
        sort(scoreBoard);
    }
}
