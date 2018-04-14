package game_classes.game_exceptions;

/**
 * Created by Whiting on 7/7/2017.
 */
public class DiceOutOfBoundsException extends Exception {
    @Override
    public String getMessage(){
        return "Dice total out of bounds--There must be 5 dice always";
    }
}
