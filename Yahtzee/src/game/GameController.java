package game;

import game_classes.*;
import game_classes.game_exceptions.IncompleteCardException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Whiting on 7/16/2017.
 */
public class GameController {
    private ScoreCard card;
    private List<DiceButton> dice;
    private int numberOfRolls, upperFieldCount, lowerFieldCount;
    private CardHistory previousGames;

    @FXML
    private Button rollDiceBtn;

    @FXML
    private HBox diceBox;
    @FXML
    private HBox  acesPnl;
    @FXML
    private HBox  twosPnl;
    @FXML
    private HBox  threesPnl;
    @FXML
    private HBox foursPnl;
    @FXML
    private HBox fivesPnl;
    @FXML
    private HBox sixesPnl;
    @FXML
    private HBox threeOfAKindPnl;
    @FXML
    private HBox  fourOfAKindPnl;
    @FXML
    private HBox fullHousePnl;
    @FXML
    private HBox smStraightPnl;
    @FXML
    private HBox lgStraightPnl;
    @FXML
    private HBox chancePnl;
    @FXML
    private HBox yahtzeePnl;
    @FXML
    private HBox pane;
    @FXML
    private Label userMessage;
    @FXML
    private Label bonusUpper;
    @FXML
    private Label yahtzeeBonus;
    @FXML
    private Label score;
    @FXML
    private Button newGameBtn;


    @FXML
    public void initialize(){
        numberOfRolls = 0;
        upperFieldCount = 0;
        previousGames = CardHistory.getInstance();

        newGameBtn.setOnAction(event ->{
            Stage primaryStage = (Stage) score.getScene().getWindow();
            primaryStage.setTitle("Yahtzee!");
            primaryStage.setScene(setupOverview());
            primaryStage.setMinWidth(600);
            primaryStage.setMinHeight(600);
            primaryStage.show();
        });

        rollDiceBtn.setOnMouseClicked((event) -> {
            rollDice(event);

        });

        setupFields();

        card = new ScoreCard();
        dice = Arrays.asList(new DiceButton(), new DiceButton(), new DiceButton(), new DiceButton(), new DiceButton());
        System.out.println("card was created");
        drawRoll();
    }

    private Scene setupOverview() {
        try {
            Parent root =  FXMLLoader.load(getClass().getResource("overview.fxml"));
           return new Scene(root, 600, 600);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void commonWithUpperFields(HBox panel, String selectorID, DiceValueFields fieldName){
        if(numberOfRolls>0) {
            Dice[] actualDice = getDiceValues();
            if (card.setScore(fieldName, actualDice)) {
                Button scoreBox = (Button) panel.lookup(selectorID);
                scoreBox.setText(card.getScore(fieldName) + "");
                userMessage.setText("");
                upperFieldCount++;
                if (upperFieldCount >= 6) {
                    int total = card.getUpperTotal();
                    String message = card.getUpperBonus() ? "Bonus for left section: 35" : "Bonus for left section: 0";
                    bonusUpper.setText(message + " Total " + total);
                }
                endOfGame();
            } else {
                userMessage.setText("Score already exists!");
            }
        }else{
            userMessage.setText("You must roll the dice");
        }

    }

    private void commonWithLowerFields(HBox panel, String selectorID, SpecialValueFields fieldName){
        if(numberOfRolls>0) {
            Dice[] actualDice = getDiceValues();
            if(card.setScore(fieldName, actualDice)){
                Button scoreBox = (Button) panel.lookup(selectorID);
                scoreBox.setText(card.getScore(fieldName) + "");
                userMessage.setText("");
                lowerFieldCount++;
                endOfGame();
            }else{
                userMessage.setText("Score already there!");
            }
        }else{
            userMessage.setText("You must roll the dice");
        }
    }

    private void setupFields() {

        acesPnl.setOnMouseClicked((event) -> {
            commonWithUpperFields(acesPnl, "#acesScore", DiceValueFields.ACES);
        });

        twosPnl.setOnMouseClicked((event) -> {
            commonWithUpperFields(twosPnl, "#twosScore", DiceValueFields.TWOS);
        });

        threesPnl.setOnMouseClicked((event) -> {
            commonWithUpperFields(threesPnl, "#threesScore", DiceValueFields.THREES);
        });

        foursPnl.setOnMouseClicked((event) -> {
            commonWithUpperFields(foursPnl, "#foursScore", DiceValueFields.FOURS);
        });
        ;

        fivesPnl.setOnMouseClicked((event) -> {
            commonWithUpperFields(fivesPnl, "#fivesScore", DiceValueFields.FIVES);
        });

        sixesPnl.setOnMouseClicked((event) -> {
            commonWithUpperFields(sixesPnl, "#sixesScore", DiceValueFields.SIXES);
        });

        threeOfAKindPnl.setOnMouseClicked((event) -> {
            commonWithLowerFields(threeOfAKindPnl, "#threeOfAKindScore", SpecialValueFields.THREEOFAKIND);
        });

        fourOfAKindPnl.setOnMouseClicked((event) -> {
            commonWithLowerFields(fourOfAKindPnl, "#fourOfAKindScore", SpecialValueFields.FOUROFAKIND);
        });

        fullHousePnl.setOnMouseClicked((event) -> {
            commonWithLowerFields(fullHousePnl, "#fullHouseScore", SpecialValueFields.FULLHOUSE);
        });

        smStraightPnl.setOnMouseClicked((event) -> {
            commonWithLowerFields(smStraightPnl, "#smStraightScore", SpecialValueFields.SMSTRAIGHT);
        });

        lgStraightPnl.setOnMouseClicked((event) -> {
            commonWithLowerFields(lgStraightPnl, "#lgStraightScore", SpecialValueFields.LGSTRAIGHT);
        });

        chancePnl.setOnMouseClicked((event) -> {
            commonWithLowerFields(chancePnl, "#chanceScore", SpecialValueFields.CHANCE);
        });

        yahtzeePnl.setOnMouseClicked((event) -> {
            commonWithLowerFields(yahtzeePnl, "#yahtzeeScore", SpecialValueFields.YAHTZEE);
            if (card.getYahtzeeCount() > 0) {
                yahtzeeBonus.setText("Bonus Yahtzee: " + card.getYahtzeeCount());
            }
        });
    }

    /**
     * This method is called after a field is updated and only after it has been updated
     * Do not user this method anywhere else
     */
    private void endOfGame(){
        for (DiceButton btn:dice) {
                btn.setHold(false);
                btn.setLockDice(true);
        }
        drawRoll();
        numberOfRolls = 0;
        int fieldsEntered = upperFieldCount + lowerFieldCount;
        if(fieldsEntered >= 13){
            //End of game
            try {
                score.setText("Total for this game: " + card.getGrandTotal() + "\nClick here to start new game!");
                previousGames.addCard(card);
            } catch (IncompleteCardException e) {
                e.printStackTrace();
                userMessage.setText("Not all fields are entered.");
            }
        }
    }

    private Dice[] getDiceValues(){
        Dice[] actualDice = new Dice[5];
        for(int i = 0; i<actualDice.length; i++){
            actualDice[i] = dice.get(i).getDice();
        }
        return actualDice;
    }
    @FXML
    public void rollDice(MouseEvent mouseEvent) {
        if(numberOfRolls<3) {
            for (DiceButton die : dice) {
                die.setLockDice(false);
                die.rollDice();
                System.out.print(" " + die.getValue());
            }
            drawRoll();
            System.out.println("Roll Dice!");
        }else{
            userMessage.setText("You must choose a category.");
        }
        numberOfRolls++;
    }

    private void drawRoll(){
        diceBox.getChildren().clear();
        diceBox.getChildren().addAll(dice);
    }
}
