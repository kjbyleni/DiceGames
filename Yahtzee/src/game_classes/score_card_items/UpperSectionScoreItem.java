package game_classes.score_card_items;

import game_classes.Dice;
import game_classes.DiceValueFields;
import game_classes.game_exceptions.DiceOutOfBoundsException;

/**
 * Created by Whiting on 7/7/2017.
 */
public class UpperSectionScoreItem extends ScoreItemField {
    private int scoreItemValue;
    private String fieldName;
    private int score;
    private boolean hasScore;

    public UpperSectionScoreItem(DiceValueFields fieldValue){
        this.scoreItemValue = fieldValue.getNumValue();
        this.fieldName = fieldValue.name();
        hasScore = false;
    }

    @Override
    public int calculateScore(Dice... d) throws DiceOutOfBoundsException {
        super.calculateScore(d);
        int total = 0;
        for (Dice die : d) {
            if (die.getValue() == scoreItemValue) {
                total += scoreItemValue;
            }
        }
        return total;
    }

    @Override
    public boolean isRollScoreItemFieldType(Dice... d) {
        return true;
    }

    public int getScoreItemValue(){return scoreItemValue;}

    public String getFieldName() {return fieldName; }

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
