package game_classes.tests;

import game_classes.Dice;
import game_classes.DiceValueFields;
import game_classes.ScoreCard;
import game_classes.SpecialValueFields;
import game_classes.game_exceptions.IncompleteCardException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

/**
 * Created by Whiting on 7/8/2017.
 */
public class ScoreCardTests {
    private ScoreCard card;
    private Dice d1,d2,d3,d4,d5,d6;

    @BeforeEach
    public void setup() {
        card = new ScoreCard();
        d1 = new Dice(1);
        d2 = new Dice(2);
        d3 = new Dice(3);
        d4 = new Dice(4);
        d5 = new Dice(5);
        d6 = new Dice(6);
    }

    @Test
    public void incompleteGame(){
        try{
            card.getGrandTotal();
            Assert.fail("Incomplete card did not throw exception!");
        } catch (IncompleteCardException e) {
            Assert.assertEquals("Your card is incomplete!", e.getMessage());
        }
    }

    @Test
    public void scoreCardLowScore() throws IncompleteCardException {
        card.setScore(DiceValueFields.ACES, d1,d1,d2,d2,d3);//2 pts
        card.setScore(DiceValueFields.TWOS, d1,d1,d3,d3,d3);//0 pts
        card.setScore(DiceValueFields.THREES, d1,d1,d2,d2,d4);//0 pts
        card.setScore(DiceValueFields.FOURS, d4,d4,d4,d4,d3);//16 pts
        card.setScore(DiceValueFields.FIVES, d5,d5,d5,d5,d3);//20 pts
        card.setScore(DiceValueFields.SIXES, d6,d6,d6,d6,d3);//24 pts

        card.setScore(SpecialValueFields.THREEOFAKIND, d3,d4,d3,d3,d1);//14 pts
        card.setScore(SpecialValueFields.FOUROFAKIND, d1,d1,d1,d2,d2);//0 pts
        card.setScore(SpecialValueFields.FULLHOUSE, d2,d3,d2,d3,d2);//25 pts
        card.setScore(SpecialValueFields.SMSTRAIGHT, d1,d1,d1,d1,d1);//0 pts
        card.setScore(SpecialValueFields.LGSTRAIGHT,d1,d2,d3,d4,d5);//40 pts
        card.setScore(SpecialValueFields.YAHTZEE, d1,d2,d3,d4,d5);//0 pts
        card.setScore(SpecialValueFields.CHANCE, d1,d2,d3,d4,d5);//15 pts

        Assert.assertEquals(156, card.getGrandTotal());
    }

    @Test
    public void scoreCardHighScore() throws IncompleteCardException {
        card.setScore(DiceValueFields.ACES,d1,d1,d1,d2,d3);//2 pts
        card.setScore(DiceValueFields.TWOS,d1,d1,d3,d3,d3);//0 pts
        card.setScore(DiceValueFields.THREES,d1,d1,d2,d2,d4);//0 pts
        card.setScore(DiceValueFields.FOURS,d4,d4,d4,d4,d3);//16 pts
        card.setScore(DiceValueFields.FIVES,d5,d5,d5,d5,d3);//20 pts
        card.setScore(DiceValueFields.SIXES,d6,d6,d6,d6,d3);//24 pts
        //Bonus --> +35 pts

        card.setScore(SpecialValueFields.THREEOFAKIND, d3,d4,d3,d3,d1);//14 pts
        card.setScore(SpecialValueFields.FOUROFAKIND, d1,d1,d1,d2,d2);//0 pts
        card.setScore(SpecialValueFields.FULLHOUSE, d2,d3,d2,d3,d2);//25 pts
        card.setScore(SpecialValueFields.SMSTRAIGHT, d1,d1,d1,d1,d1);//0 pts
        card.setScore(SpecialValueFields.LGSTRAIGHT,d1,d2,d3,d4,d5);//40 pts
        card.setScore(SpecialValueFields.YAHTZEE,d1,d1,d1,d1,d1);//50 pts
        card.setScore(SpecialValueFields.YAHTZEE,d1,d1,d1,d1,d1);//Bonus
        card.setScore(SpecialValueFields.YAHTZEE,d1,d1,d1,d1,d1);//Bonus
        card.setScore(SpecialValueFields.YAHTZEE,d1,d1,d1,d1,d1);//Bonus
        card.setScore(SpecialValueFields.CHANCE, d1,d2,d3,d4,d5);//15 pts
        //Bonus --> +300 pts

        Assert.assertEquals(542, card.getGrandTotal());
    }
}
