package game_classes.score_card_items;


import game_classes.Dice;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Whiting on 7/8/2017.
 */
public class SmStraight extends ScoreItemField {
    protected int straightNeeded = 4;
    protected int maxStartNumber = 3;

    public SmStraight(){
        scoreValue = 30;
    }

    @Override
    public boolean isRollScoreItemFieldType(Dice... d) {
        List<Dice> dice = Arrays.asList(d);
        int straightCount=0;

        for(int i=1; i<=maxStartNumber;i++){
            if(containsValue(dice, i)){
                for(int j=i; j<(i+straightNeeded); j++){
                    if(!containsValue(dice, j)){
                       straightCount=0;
                    }else {
                        straightCount++;
                    }
                }
                if(straightCount == straightNeeded){
                    return true;
                }else{
                    straightCount = 0;
                }
            }
        }
        return false;
    }

    public boolean containsValue(List<Dice> dice, int value){
        for (Dice die:dice) {
            if(die.getValue() == value){
                return true;
            }
        }
        return false;
    }
}
