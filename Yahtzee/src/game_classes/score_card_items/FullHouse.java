package game_classes.score_card_items;

import game_classes.Dice;

/**
 * Created by Whiting on 7/8/2017.
 */
public class FullHouse extends ThreeOfAKind {

    public FullHouse(){
        scoreValue = 25;
    }

    @Override
    public boolean isRollScoreItemFieldType(Dice... d) {
        if(super.isRollScoreItemFieldType(d)) {
            int threeOfAKindValue = super.getThreeOfAKindValue(d);
            int counter = 0;
            for (Dice die : d) {
                int temp = die.getValue();
                if(temp != threeOfAKindValue) {
                    for (Dice di : d) {
                        if (temp == di.getValue())
                            counter++;
                    }
                    if (counter >= 2)
                        return true;
                    else//reset counter
                        counter = 0;
                }
            }
        }
        return false;
    }
}
