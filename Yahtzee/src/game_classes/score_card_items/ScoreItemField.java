package game_classes.score_card_items;

import game_classes.Dice;
import game_classes.game_exceptions.DiceOutOfBoundsException;

/**
 * Created by Whiting on 7/7/2017.
 */
public abstract class ScoreItemField {
    protected int scoreValue = 0;
    private int score;
    private boolean hasScore = false;//No score by default

    /**
     * Default calculateScore totals the sum of all dice
     * if isRollScoreItemFieldType(d) returns false calculateScore returns 0
     */
    public int calculateScore(Dice... d) throws DiceOutOfBoundsException {
        int total = 0;
        if (d.length != 5) {
            throw new DiceOutOfBoundsException();
        } else {
            if(isRollScoreItemFieldType(d)) {
                if (scoreValue == 0) {
                    for (Dice die : d) {
                        total += die.getValue();
                    }
                    return total;
                } else {
                    return scoreValue;
                }
            }else{
                return 0;
            }
        }
    }

    public abstract boolean isRollScoreItemFieldType(Dice... d);

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        hasScore = true;
    }

    public boolean doesHaveScore() {
        return hasScore;
    }

    public void setHasScore(boolean hasScore) {
        this.hasScore = hasScore;
    }
}
