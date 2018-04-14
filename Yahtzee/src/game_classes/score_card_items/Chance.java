package game_classes.score_card_items;

import game_classes.Dice;

/**
 * Created by Whiting on 7/8/2017.
 */
public class Chance extends ScoreItemField {
    @Override
    public boolean isRollScoreItemFieldType(Dice... d) {
        return true;
    }
}
