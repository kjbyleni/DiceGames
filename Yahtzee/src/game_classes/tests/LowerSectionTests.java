package game_classes.tests;

import game_classes.*;
import game_classes.game_exceptions.DiceOutOfBoundsException;
import game_classes.score_card_items.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

/**
 * Created by Whiting on 7/8/2017.
 */
public class LowerSectionTests {

    private UpperSectionScoreItem aces, twos, threes, fours, fives, sixes;
    private Dice d1,d2,d3,d4,d5,d6;
    private ThreeOfAKind threeOfAKind;
    private FourOfAKind fourOfAKind;
    private FullHouse fullHouse;
    private Yahtzee yahtzee;
    private SmStraight smStraight;
    private LgStraight lgStraight;
    private Chance chance;

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

        threeOfAKind = new ThreeOfAKind();
        fourOfAKind = new FourOfAKind();
        fullHouse = new FullHouse();
        yahtzee = new Yahtzee();
        smStraight = new SmStraight();
        lgStraight = new LgStraight();
        chance = new Chance();
    }

    @Test
    public void scoreByDefault(){
       Assert.assertFalse(threeOfAKind.doesHaveScore());
       Assert.assertFalse(fourOfAKind.doesHaveScore());
       Assert.assertFalse(fullHouse.doesHaveScore());
       Assert.assertFalse(yahtzee.doesHaveScore());
       Assert.assertFalse(smStraight.doesHaveScore());
       Assert.assertFalse(lgStraight.doesHaveScore());
       Assert.assertFalse(chance.doesHaveScore());

       fourOfAKind.setHasScore(true);
       fullHouse.setHasScore(true);
       yahtzee.setHasScore(true);
       smStraight.setHasScore(true);
       lgStraight.setHasScore(true);
       chance.setHasScore(true);
       threeOfAKind.setHasScore(true);


        Assert.assertTrue(threeOfAKind.doesHaveScore());
        Assert.assertTrue(fourOfAKind.doesHaveScore());
        Assert.assertTrue(fullHouse.doesHaveScore());
        Assert.assertTrue(yahtzee.doesHaveScore());
        Assert.assertTrue(smStraight.doesHaveScore());
        Assert.assertTrue(lgStraight.doesHaveScore());
        Assert.assertTrue(chance.doesHaveScore());

    }

    @Test
    public void threeOfAKindType() throws DiceOutOfBoundsException {
        Assert.assertFalse(threeOfAKind.isRollScoreItemFieldType(d1, d2, d3,d4,d5));
        Assert.assertFalse(threeOfAKind.isRollScoreItemFieldType(d1, d1, d2,d2,d5));
        Assert.assertTrue(threeOfAKind.isRollScoreItemFieldType(d1, d1, d1,d2,d2));
        Assert.assertTrue(threeOfAKind.isRollScoreItemFieldType(d1, d1, d1,d1,d2));
        Assert.assertTrue(threeOfAKind.isRollScoreItemFieldType(d1, d1, d1,d3,d2));

        Assert.assertEquals(10, threeOfAKind.calculateScore(d2, d2, d2,d2,d2));
        Assert.assertEquals(16, threeOfAKind.calculateScore(d2, d2, d2,d5,d5));
        Assert.assertEquals(0, threeOfAKind.calculateScore(d2, d2, d3,d1,d5));
    }

    @Test
    public void fourOfAKindType() throws DiceOutOfBoundsException {
        Assert.assertFalse(fourOfAKind.isRollScoreItemFieldType(d1, d2, d3,d4,d5));
        Assert.assertFalse(fourOfAKind.isRollScoreItemFieldType(d1, d1, d2,d2,d5));
        Assert.assertFalse(fourOfAKind.isRollScoreItemFieldType(d1, d1, d1,d2,d2));
        Assert.assertFalse(fourOfAKind.isRollScoreItemFieldType(d1, d1, d1,d3,d2));
        Assert.assertTrue(fourOfAKind.isRollScoreItemFieldType(d1, d1, d1,d1,d2));
        Assert.assertTrue(fourOfAKind.isRollScoreItemFieldType(d2, d1, d1,d1,d1));

        Assert.assertEquals(10, fourOfAKind.calculateScore(d2, d2, d2,d2,d2));
        Assert.assertEquals(13, fourOfAKind.calculateScore(d2, d2, d2,d2,d5));
        Assert.assertEquals(0, fourOfAKind.calculateScore(d2, d2, d2,d1,d5));
    }

    @Test
    public void fullHouseType() throws DiceOutOfBoundsException {
        Assert.assertFalse(fullHouse.isRollScoreItemFieldType(d1, d2, d3,d4,d5));
        Assert.assertFalse(fullHouse.isRollScoreItemFieldType(d1, d1, d2,d2,d5));
        Assert.assertTrue(fullHouse.isRollScoreItemFieldType(d1, d1, d1,d2,d2));
        Assert.assertTrue(fullHouse.isRollScoreItemFieldType(d3, d3, d2,d3,d2));
        Assert.assertFalse(fullHouse.isRollScoreItemFieldType(d1, d1, d1,d1,d2));
        Assert.assertFalse(fullHouse.isRollScoreItemFieldType(d2, d1, d1,d1,d1));

        Assert.assertEquals(25, fullHouse.calculateScore(d2, d2, d3,d3,d3));
        Assert.assertEquals(0, fullHouse.calculateScore(d2, d3, d2,d2,d1));
        Assert.assertEquals(0, fullHouse.calculateScore(d1, d2, d3,d4,d5));
        Assert.assertEquals(25, fullHouse.calculateScore(d4, d4, d2,d2,d2));
    }

    @Test
    public void yahtzeeType() throws DiceOutOfBoundsException {
        Assert.assertFalse(yahtzee.isRollScoreItemFieldType(d1, d2, d3,d4,d5));
        Assert.assertFalse(yahtzee.isRollScoreItemFieldType(d1, d1, d2,d2,d5));
        Assert.assertFalse(yahtzee.isRollScoreItemFieldType(d1, d1, d1,d2,d2));
        Assert.assertFalse(yahtzee.isRollScoreItemFieldType(d1, d1, d1,d1,d2));
        Assert.assertTrue(yahtzee.isRollScoreItemFieldType(d1, d1, d1,d1,d1));
        Assert.assertTrue(yahtzee.isRollScoreItemFieldType(d2, d2, d2,d2,d2));

        Assert.assertEquals(50, yahtzee.calculateScore(d2, d2, d2,d2,d2));
        Assert.assertEquals(0, yahtzee.calculateScore(d1, d2, d2,d2,d2));
    }

    @Test
    public void smallStraight() throws DiceOutOfBoundsException {
        Assert.assertTrue(smStraight.isRollScoreItemFieldType(d3, d2, d3,d4,d5));
        Assert.assertTrue(smStraight.isRollScoreItemFieldType(d1, d3, d4,d5,d6));
        Assert.assertFalse(smStraight.isRollScoreItemFieldType(d1, d1, d1,d2,d2));
        Assert.assertFalse(smStraight.isRollScoreItemFieldType(d1, d1, d1,d1,d2));
        Assert.assertFalse(smStraight.isRollScoreItemFieldType(d1, d1, d1,d1,d1));
        Assert.assertFalse(smStraight.isRollScoreItemFieldType(d2, d2, d2,d2,d2));

        Assert.assertEquals(30, smStraight.calculateScore(d1, d2, d3,d4,d5));
        Assert.assertEquals(0, smStraight.calculateScore(d1, d2, d4,d5,d5));
        Assert.assertEquals(30, smStraight.calculateScore(d1, d2, d3,d4,d4));
    }

    @Test
    public void largeStraight() throws DiceOutOfBoundsException {
        Assert.assertTrue(lgStraight.isRollScoreItemFieldType(d1, d2, d3,d4,d5));
        Assert.assertTrue(lgStraight.isRollScoreItemFieldType(d2, d3, d4,d5,d6));
        Assert.assertFalse(lgStraight.isRollScoreItemFieldType(d1, d1, d1,d2,d2));
        Assert.assertFalse(lgStraight.isRollScoreItemFieldType(d1, d1, d1,d1,d2));
        Assert.assertFalse(lgStraight.isRollScoreItemFieldType(d1, d1, d1,d1,d1));
        Assert.assertFalse(lgStraight.isRollScoreItemFieldType(d2, d2, d2,d2,d2));

        Assert.assertEquals(40, lgStraight.calculateScore(d1, d2, d3,d4,d5));
        Assert.assertEquals(0, lgStraight.calculateScore(d1, d2, d4,d6,d5));
        Assert.assertEquals(40, lgStraight.calculateScore(d2, d3, d4,d5,d6));
    }

    @Test
    public void chanceType() throws DiceOutOfBoundsException {
        Assert.assertTrue(chance.isRollScoreItemFieldType(d1, d2, d3,d4,d5));
        Assert.assertTrue(chance.isRollScoreItemFieldType(d1, d1, d1,d1,d1));
        Assert.assertTrue(chance.isRollScoreItemFieldType(d2, d2, d2,d2,d2));

        Assert.assertEquals(10, chance.calculateScore(d2, d2, d2,d2,d2));
        Assert.assertEquals(15, chance.calculateScore(d1, d2, d3,d4,d5));
    }
}
