package game_classes.tests;

import game_classes.Dice;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

/**
 * Created by Whiting on 7/7/2017.
 */
public class DiceTests {
    @Test
    public void testDiceRange() throws InterruptedException {
        boolean one = false, two = false, three = false, four = false, five = false, six = false;
        Dice d = new Dice();
        for(int i=0; i<100;i++){
            if(d.getValue()==1){
                one = true;
            }else if(d.getValue()==2){
                two = true;
            }else if(d.getValue()==3){
                three = true;
            }else if(d.getValue()==4){
                four = true;
            }else if(d.getValue()==5){
                five = true;
            }else if(d.getValue()==6){
                six = true;
            }else{
                Assert.fail("Random number was : " + d.getValue() + " Cannot be smaller than 0 or greater than 6");
            }
            Thread.sleep(100);
            d.rollDice();
        }
        Assert.assertTrue(one);
        Assert.assertTrue(two);
        Assert.assertTrue(three);
        Assert.assertTrue(four);
        Assert.assertTrue(five);
        Assert.assertTrue(six);
    }

    @Test
    public void testHoldValue() throws InterruptedException {
        Dice d = new Dice();
        int diceValue = d.getValue();

        //Hold should be false by default
        Assert.assertFalse(d.getHold());

        d.rollDice();
        if(d.getValue()==diceValue){
            d.rollDice();
        }
        Assert.assertNotEquals(d.getValue(), diceValue);

        d.setHold(true);
        diceValue = d.getValue();

        //Dice value should not change when setHold is true
        for(int i=0; i<10;i++){
            d.rollDice();
            Assert.assertEquals(diceValue, d.getValue());
            Thread.sleep(100);
        }


    }
}
