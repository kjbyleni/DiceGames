package game;

import game_classes.CardHistory;
import game_classes.ScoreCard;
import game_classes.game_exceptions.IncompleteCardException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Whiting on 7/18/2017.
 */
public class OverviewController {
    private CardHistory previousGames;
    private int game1,game2,game3,game4,game5;
    @FXML
    private Button newGame;
    @FXML
    private Label firstGameTotal;
    @FXML
    private Label secondGameTotal;
    @FXML
    private Label thirdGameTotal;
    @FXML
    private Label fourthGameTotal;
    @FXML
    private Label fifthGameTotal;
    @FXML
    private Label averageOfFiveGames;

    @FXML
    public void initialize(){
        previousGames = CardHistory.getInstance();
        newGame.setOnAction(event ->{
            Stage primaryStage = (Stage) newGame.getScene().getWindow();
            primaryStage.setTitle("Yahtzee!");
            primaryStage.setScene(setupGame());
            primaryStage.setMinWidth(600);
            primaryStage.setMinHeight(600);
            primaryStage.show();
        });

        game1 = getTotalScoreForCard(previousGames.getCard(0));
        game2 = getTotalScoreForCard(previousGames.getCard(1));
        game3 = getTotalScoreForCard(previousGames.getCard(2));
        game4 = getTotalScoreForCard(previousGames.getCard(3));
        game5 = getTotalScoreForCard(previousGames.getCard(4));
        firstGameTotal.setText(game1 + "");
        secondGameTotal.setText(game2 + "");
        thirdGameTotal.setText(game3 + "");
        fourthGameTotal.setText(game4 + "");
        fifthGameTotal.setText(game5 + "");
        averageOfFiveGames.setText("Average: " + calcAverage());
    }

    private int calcAverage(){
        int totalGamesPlayed = 0;
        totalGamesPlayed += (game1!=0)?1:0;
        totalGamesPlayed += (game2!=0)?1:0;
        totalGamesPlayed += (game3!=0)?1:0;
        totalGamesPlayed += (game4!=0)?1:0;
        totalGamesPlayed += (game5!=0)?1:0;

        if(totalGamesPlayed == 0){
            totalGamesPlayed=1;
        }
        return ((game1 + game2 + game3 + game4 + game5)/totalGamesPlayed);
    }
    private int getTotalScoreForCard(ScoreCard card){
        if(card.areAllFieldsWithValue()){
            try {
                return card.getGrandTotal();
            } catch (IncompleteCardException e) {
                e.printStackTrace();
                return 0;
            }
        }else{
            return 0;
        }
    }
    private Scene setupGame() {
        try {
            Parent root =  FXMLLoader.load(getClass().getResource("game.fxml"));
            return new Scene(root, 600, 600);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
