package game_classes;

/**
 * Created by Whiting on 7/7/2017.
 */
public enum DiceValueFields {
    ACES(1),TWOS(2),THREES(3),FOURS(4),FIVES(5),SIXES(6);
    private int numValue;
    DiceValueFields(int i) {
        this.numValue = i;
    }

    public int getNumValue(){return numValue;}
}
