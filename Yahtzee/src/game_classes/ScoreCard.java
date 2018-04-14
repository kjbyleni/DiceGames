package game_classes;

import game_classes.game_exceptions.DiceOutOfBoundsException;
import game_classes.game_exceptions.IncompleteCardException;
import game_classes.score_card_items.*;

/**
 * Created by Whiting on 7/8/2017.
 */
public class ScoreCard {
    private final int YATZEE_BONUS_POINTS = 100, UPPER_TOTAL_MIN_FOR_BONUS=63;
    private final int UPPER_BONUS_POINTS = 35;

    private UpperSectionScoreItem aces, twos, threes, fours, fives, sixes;
    private ThreeOfAKind threeOfAKind;
    private FourOfAKind fourOfAKind;
    private FullHouse fullHouse;
    private Yahtzee yahtzee;
    private SmStraight smStraight;
    private LgStraight lgStraight;
    private Chance chance;
    private boolean upperBonus;
    private int yahtzeeCount;

    public ScoreCard() {
        aces = new UpperSectionScoreItem(DiceValueFields.ACES);
        twos = new UpperSectionScoreItem(DiceValueFields.TWOS);
        threes = new UpperSectionScoreItem(DiceValueFields.THREES);
        fours = new UpperSectionScoreItem(DiceValueFields.FOURS);
        fives = new UpperSectionScoreItem(DiceValueFields.FIVES);
        sixes = new UpperSectionScoreItem(DiceValueFields.SIXES);
        upperBonus = false;

        threeOfAKind = new ThreeOfAKind();
        fourOfAKind = new FourOfAKind();
        fullHouse = new FullHouse();
        yahtzee = new Yahtzee();
        smStraight = new SmStraight();
        lgStraight = new LgStraight();
        chance = new Chance();
        yahtzeeCount = 0;
    }

    public boolean getUpperBonus(){
        return upperBonus;
    }

    public int getUpperTotal(){
        int upperTotals = 0;
        upperTotals += aces.getScore();
        upperTotals += twos.getScore();
        upperTotals += threes.getScore();
        upperTotals += fours.getScore();
        upperTotals += fives.getScore();
        upperTotals += sixes.getScore();
        if(upperTotals >=UPPER_TOTAL_MIN_FOR_BONUS){
            upperTotals += UPPER_BONUS_POINTS;
            upperBonus = true;
        }
        return upperTotals;
    }

    private int getLowerTotal(){
        int lowerTotals = 0;
        lowerTotals += threeOfAKind.getScore();
        lowerTotals += fourOfAKind.getScore();
        lowerTotals += yahtzee.getScore();
        lowerTotals += smStraight.getScore();
        lowerTotals += lgStraight.getScore();
        lowerTotals += fullHouse.getScore();
        lowerTotals += chance.getScore();
        for(int i=0; i<yahtzeeCount; i++) {
            lowerTotals += YATZEE_BONUS_POINTS;
        }
        return lowerTotals;
    }

    public int getGrandTotal() throws IncompleteCardException {
        if(areAllFieldsWithValue()) {
            return getLowerTotal() + getUpperTotal();
        }else{
            throw new IncompleteCardException();
        }
    }

    public boolean areAllFieldsWithValue() {
        if (!aces.doesHaveScore()) {
            return false;
        } else if (!twos.doesHaveScore()) {
            return false;
        } else if (!threes.doesHaveScore()) {
            return false;
        } else if (!fours.doesHaveScore()) {
            return false;
        } else if (!fives.doesHaveScore()) {
            return false;
        } else if (!sixes.doesHaveScore()) {
            return false;
        } else if (!threeOfAKind.doesHaveScore()) {
            return false;
        } else if (!fourOfAKind.doesHaveScore()) {
            return false;
        } else if (!yahtzee.doesHaveScore()) {
            return false;
        } else if (!fullHouse.doesHaveScore()) {
            return false;
        } else if (!smStraight.doesHaveScore()) {
            return false;
        } else if (!lgStraight.doesHaveScore()) {
            return false;
        } else if (!chance.doesHaveScore()) {
            return false;
        } else {
            return true;
        }
    }

    public int getScore(DiceValueFields fieldName) {
        switch (fieldName) {
            case ACES:
                return aces.getScore();
            case TWOS:
                return twos.getScore();
            case THREES:
                return threes.getScore();
            case FOURS:
                return fours.getScore();
            case FIVES:
                return fives.getScore();
            case SIXES:
                return sixes.getScore();
            default:
                return 0;
        }
    }

    public int getScore(SpecialValueFields fieldName) {
        switch (fieldName) {
            case THREEOFAKIND:
                return threeOfAKind.getScore();
            case FOUROFAKIND:
                return fourOfAKind.getScore();
            case FULLHOUSE:
                return fullHouse.getScore();
            case SMSTRAIGHT:
                return smStraight.getScore();
            case LGSTRAIGHT:
                return lgStraight.getScore();
            case YAHTZEE:
                return yahtzee.getScore();
            case CHANCE:
                return chance.getScore();
            default:
                return 0;
        }
    }

    public boolean setScore(DiceValueFields fieldName, Dice... d) {
        switch (fieldName) {
            case ACES:
                return setScore(aces, d);
            case TWOS:
                return setScore(twos, d);
            case THREES:
                return setScore(threes, d);
            case FOURS:
                return setScore(fours, d);
            case FIVES:
                return setScore(fives, d);
            case SIXES:
                return setScore(sixes, d);
            default:
                return false;
        }
    }

    public boolean setScore(SpecialValueFields fieldName, Dice... d) {
        switch (fieldName) {
            case THREEOFAKIND:
                return setScore(threeOfAKind, d);
            case FOUROFAKIND:
                return setScore(fourOfAKind, d);
            case FULLHOUSE:
                return setScore(fullHouse, d);
            case SMSTRAIGHT:
                return setScore(smStraight, d);
            case LGSTRAIGHT:
                return setScore(lgStraight, d);
            case YAHTZEE:
                boolean result = yahtzee.doesHaveScore();
                if(result){
                    if(yahtzee.isRollScoreItemFieldType(d)) {
                        yahtzeeCount++;
                        return true;
                    }
                    else {
                        return false;
                    }
                }else{
                    return setScore(yahtzee, d);
                }
            case CHANCE:
                return setScore(chance, d);
            default:
                return false;
        }
    }

    public int getYahtzeeCount(){return yahtzeeCount;}


    /**
     * Pattern for setScore for upper card items
     * @return --True if score successfully set
     */
    private boolean setScore(ScoreItemField fieldItem, Dice... d){
        if (!fieldItem.doesHaveScore()) {
            try {
                fieldItem.setScore(fieldItem.calculateScore(d));
                fieldItem.setHasScore(true);
                return true;
            } catch (DiceOutOfBoundsException e) {
                return false;
            }
        }else{
            return false;
        }
    }
}
