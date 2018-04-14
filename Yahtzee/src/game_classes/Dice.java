package game_classes;

import javafx.scene.control.Button;

import java.util.Random;

/**
 * Created by Whiting on 7/7/2017.
 */
public class Dice{
    private int value;
    private boolean hold;
    private Random randomGenerator;
    private final int MIN = 1, MAX = 6;

    public Dice(int val){
        randomGenerator = new Random();
        this.value = val;
        this.hold = false;
    }

    public Dice(){
        this.randomGenerator = new Random();
        this.hold=false;
        this.value = randomGenerator.nextInt(MAX) +MIN;
    }

    public int getValue(){return value;}

    public void setHold(boolean holdDice){this.hold=holdDice;}

    public boolean getHold(){return hold;}

    /**
     * Double roll dice if they are the same value
     */
    public void rollDice(){
        if(!hold){
            int prev = this.value;
            this.value = randomGenerator.nextInt(MAX) + 1;
            if(prev == this.value){
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.value = randomGenerator.nextInt(MAX) + 1;
            }
        }
    }
}
