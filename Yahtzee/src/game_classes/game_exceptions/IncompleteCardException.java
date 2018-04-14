package game_classes.game_exceptions;

/**
 * Created by Whiting on 7/8/2017.
 */
public class IncompleteCardException extends Exception {
    @Override
    public String getMessage(){
        return "Your card is incomplete!";
    }
}
