package game_classes;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Created by Whiting on 7/16/2017.
 */
public class DiceButton extends Button {

    private Dice die;
    private boolean lockDice;
    public DiceButton() {
        super();
        lockDice = true;
        die = new Dice();
        this.setOnMouseClicked((event)->{
            if(!lockDice) {
                if (die.getHold()) {
                    die.setHold(false);
                    this.setStyle("-fx-border-color: black;-fx-border-width: 0px;-fx-border-style: solid;");
                } else {
                    die.setHold(true);
                    this.setStyle("-fx-border-color: blue;-fx-border-width: 2px;-fx-border-style: solid;");
                }
            }
        });
        rollDice();
    }

    public void setLockDice(boolean locked){
        this.lockDice = locked;
    }

    public void setHold(boolean hold){
        die.setHold(hold);
        if(!die.getHold()) {
            this.setStyle("-fx-border-color: black;-fx-border-width: 0px;-fx-border-style: solid;");
        }else{
            this.setStyle("-fx-border-color: blue;-fx-border-width: 2px;-fx-border-style: solid;");
        }
    }
    private void displayImage(){
        String imageLocation = "media/" + die.getValue() + ".png";
        Image image = new Image(getClass().getResource(imageLocation).toExternalForm());
        BackgroundSize size = new BackgroundSize(100, 100,true,true,true,false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        Background background = new Background(backgroundImage);
        this.setBackground(background);
        this.setPrefWidth(100);
        this.setPrefHeight(100);
    }

    public void rollDice(){
        if(!die.getHold()){
            die.rollDice();
            displayImage();
        }else{
            System.out.println("die is being held at " + die.getValue());
        }
    }

    public int getValue(){
        return die.getValue();
    }
    public Dice getDice(){return die;}
}
