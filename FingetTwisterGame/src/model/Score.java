package model;


public class Score implements Comparable{
    private final String name;
    private final int score;

    public Score(String name, int score){
        this.name = name;
        this.score = score;
    }

    @Override
    public int compareTo(Object compareScore) {
        int score = ((Score)compareScore).getScore();
        return score-this.score;

    }

    public int getScore() {
        return score;
    }
    public String getName() {
        return name;
    }
}
