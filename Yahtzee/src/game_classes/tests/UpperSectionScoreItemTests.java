package game_classes.tests;


import game_classes.Dice;
import game_classes.DiceValueFields;
import game_classes.score_card_items.UpperSectionScoreItem;
import game_classes.game_exceptions.DiceOutOfBoundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

/**
 * Created by Whiting on 7/7/2017.
 */
public class UpperSectionScoreItemTests {

    private UpperSectionScoreItem aces, twos, threes, fours, fives, sixes;
    private Dice d1,d2,d3,d4,d5,d6;


   @BeforeEach
    public void setup() {
        aces = new UpperSectionScoreItem(DiceValueFields.ACES);
        twos = new UpperSectionScoreItem(DiceValueFields.TWOS);
        threes = new UpperSectionScoreItem(DiceValueFields.THREES);
        fours = new UpperSectionScoreItem(DiceValueFields.FOURS);
        fives = new UpperSectionScoreItem(DiceValueFields.FIVES);
        sixes = new UpperSectionScoreItem(DiceValueFields.SIXES);

        d1 = new Dice(1);
        d2 = new Dice(2);
        d3 = new Dice(3);
        d4 = new Dice(4);
        d5 = new Dice(5);
        d6 = new Dice(6);
    }

    @Test
    public void scoreByDefault(){
        Assert.assertFalse(aces.doesHaveScore());
    }
    /**
     * This test verifies the basic fields within the UpperSectionScoreItems
     */
    @Test
    public void testUpperSectionFieldCreation(){
        Assert.assertEquals(aces.getScoreItemValue(),1);
        Assert.assertEquals(aces.getFieldName(), DiceValueFields.ACES.name());

        Assert.assertEquals(twos.getScoreItemValue(),2);
        Assert.assertEquals(twos.getFieldName(), DiceValueFields.TWOS.name());

        Assert.assertEquals(threes.getScoreItemValue(),3);
        Assert.assertEquals(threes.getFieldName(), DiceValueFields.THREES.name());

        Assert.assertEquals(fours.getScoreItemValue(),4);
        Assert.assertEquals(fours.getFieldName(), DiceValueFields.FOURS.name());

        Assert.assertEquals(fives.getScoreItemValue(),5);
        Assert.assertEquals(fives.getFieldName(), DiceValueFields.FIVES.name());

        Assert.assertEquals(sixes.getScoreItemValue(),6);
        Assert.assertEquals(sixes.getFieldName(), DiceValueFields.SIXES.name());
    }

    /**
     * This tests CalculateValue function for the UpperSectionScoreItem class
     */
    @Test
    public void testCalculateValueOverrideFunction() throws DiceOutOfBoundsException {
        Assert.assertEquals(0, aces.calculateScore(d2,d2,d3,d4,d5));
        Assert.assertEquals(1, aces.calculateScore(d1, d2, d3, d4, d5));
        Assert.assertEquals(2, aces.calculateScore(d1, d1, d2, d3, d4));
        Assert.assertEquals(3, aces.calculateScore(d1, d1, d1, d3, d4));
        Assert.assertEquals(4, aces.calculateScore(d1, d1, d1, d1, d3));
        Assert.assertEquals(5, aces.calculateScore(d1, d1, d1, d1, d1));
    }

    /**
     * Since Yahtzee requires each of these to have 5 dice no more or less need to verify
     * exceptions are thrown when outside the bounds -- for developers.
     */
    @Test void testCalculateValueOverrideFunction_OneDice(){
        try{
            aces.calculateScore(d1);

            Assert.fail("Exception should have been thrown");
        }catch (Exception e){
            Assert.assertEquals("Dice total out of bounds--There must be 5 dice always", e.getMessage());
        }
    }

    /**
     * Since Yahtzee requires each of these to have 5 dice no more or less need to verify
     * exceptions are thrown when outside the bounds -- for developers.
     */
    @Test void testCalculateValueOverrideFunction_FourDice(){
        try{
            aces.calculateScore(d1, d2, d3, d4);

            Assert.fail("Exception should have been thrown");
        }catch (Exception e){
            Assert.assertEquals("Dice total out of bounds--There must be 5 dice always", e.getMessage());
        }
    }

    /**
     * Since Yahtzee requires each of these to have 5 dice no more or less need to verify
     * exceptions are thrown when outside the bounds -- for developers.
     */
    @Test void testCalculateValueOverrideFunction_SixDice(){
        try{
            aces.calculateScore(d1, d2, d3, d4, d5, d6);

            Assert.fail("Exception should have been thrown");
        }catch (Exception e){
            Assert.assertEquals("Dice total out of bounds--There must be 5 dice always", e.getMessage());
        }
    }

    /**
     * Only 6 possible dice values
     */
    @Test
    public void testDiceValueFields(){
        Assert.assertEquals(6, DiceValueFields.values().length);
    }

}
