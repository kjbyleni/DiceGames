package game_classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Whiting on 7/19/2017.
 */
public class CardHistory {
    //Should only include finished games
    private List<ScoreCard> finishedGames;

    private static CardHistory ourInstance = new CardHistory();

    public static CardHistory getInstance() {
        return ourInstance;
    }

    private CardHistory() {
        finishedGames = new ArrayList<>();
    }

    public void addCard(ScoreCard card){
        if(finishedGames.size()>=5){
            List<ScoreCard> temp = new ArrayList<>();
            finishedGames.remove(0);
            for (ScoreCard c:finishedGames ) {
                temp.add(c);
            }
            temp.add(card);
            finishedGames = temp;
        }else{
            finishedGames.add(card);
        }
    }

    public ScoreCard getCard(int index){
        if(index>=finishedGames.size()){
            return new ScoreCard();
        }else{
            return finishedGames.get(index);
        }
    }
}
