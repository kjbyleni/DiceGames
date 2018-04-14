package game_classes.score_card_items;

import game_classes.Dice;

/**
 * Created by Whiting on 7/8/2017.
 */
public class ThreeOfAKind extends ScoreItemField{
    protected int counterMin = 3;

    public ThreeOfAKind() { }

    @Override
    public boolean isRollScoreItemFieldType(Dice... d) {
        int counter = 0;
        for (Dice die : d) {
            int temp = die.getValue();
            for (Dice di : d) {
                if (temp == di.getValue())
                    counter++;
            }
            if (counter >= counterMin)
                return true;
            else//reset counter
                counter = 0;
        }
        return false;
    }

    public int getThreeOfAKindValue(Dice... d){
        int counter = 0;
        for (Dice die : d) {
            int temp = die.getValue();
            for (Dice di : d) {
                if (temp == di.getValue())
                    counter++;
            }
            if (counter >= counterMin)
                return temp;
            else//reset counter
                counter = 0;
        }
        return 0;
    }
}
