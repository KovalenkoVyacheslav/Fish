package VIEW;

import CONTROLLER.GameController;
import MODELS.MainFish;
import MODELS.Player;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

/**
 * class for operating vizualization of game process
 */
public class FXMLMainFormController implements Initializable {

    private Integer columnIndex=0;
    private GameController game;
    private static Player player = new Player();

    @FXML
    private SplitPane Split_main;

    @FXML
    private GridPane gridPaneTop;

    @FXML
    private GridPane gridPaneBot;

    @FXML
    private Label lblNickName;

    @FXML
    private Label lblScore;

    private int period = 30;

    /**
     * Setter of player's name
     * @param name any string
     */
    public static void SetName(String name) {
        player.setNickName(name);
    }

    /**
     * Method initialize our form
     * @param location standart parameter
     * @param resources standart parameter
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game = new GameController();
        startTimer();
        Image img = MainFish.getView("src/main/resources/image/default.jpg");
        lblNickName.setText(player.getNickName());
        lblScore.setText(String.valueOf(player.getScore()));
        BackgroundImage myBI= new BackgroundImage(img,BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, new BackgroundSize(785,700,
                false,false, false,false));

        Split_main.setBackground(new Background(myBI));

        gridPaneTop.setAlignment(Pos.CENTER);

        ReWriteForm();
        MoveFace();
    }

    /**
     * Method responsible for pushing "Try again" button - starts a new game
     * @param event event of Mouseclick on button
     */
    @FXML
    void OnActionAgain(ActionEvent event) {
        Stage stage = (Stage) Split_main.getScene().getWindow();

        SetName(player.getNickName());
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/FXMLMainForm.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert root != null;
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Method starts timer, where new waves creating, depends on how much points player has
     */
    private void startTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                game.addNewWave();
                Platform.runLater(() -> ReWriteForm());
                if(game.getScoreCounter() >= 500) {
                    period = 1;
                }
                else if(game.getScoreCounter() >= 400) {
                    period = 5;
                }
                else if(game.getScoreCounter() >= 300) {
                    period = 10;
                }
                else if(game.getScoreCounter() >= 200) {
                    period = 15;
                }
                else if(game.getScoreCounter() >= 100) {
                    period = 20;
                }
                else if(game.getScoreCounter() >= 50) {
                    period = 25;
                }
                timer.cancel();
                startTimer();
            }
        }, period * 1000, period * 1000);

        if(GameOver())
            timer.cancel();
    }

    /**
     * Method rewrites form
     * @return true = if all is good, false = if there is some exception
     */
    private Boolean ReWriteForm() {
        lblScore.setText(String.valueOf(game.getScoreCounter()));
        if(GameOver()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("I have a message for You!");
            alert.setContentText("You lost :(");
            alert.showAndWait();
        }

        gridPaneTop.getChildren().clear();
        try {
            ImageView bg;

            if(!game.CheckField())
            {
                game.addNewWave();
            }

            for (int i = 0; i < gridPaneTop.getRowConstraints().size(); i++) {
                for (int j = 0; j < gridPaneTop.getColumnConstraints().size(); j++) {
                    if (game.getOurSea()[i][j] == null) {
                        bg = new ImageView(MainFish.getView("src/main/resources/image/empty.png"));
                        bg.opacityProperty().set(0.0);
                    }

                    else
                        bg = game.getOurSea()[i][j].getImageViewFish();
                    gridPaneTop.add(bg, j, i);
                    GridPane.setValignment(bg, VPos.CENTER);
                    GridPane.setHalignment(bg, HPos.CENTER);
                }
            }

            ChangeOnMouseClicked();

            return true;
        } catch (Exception exp) {
            return false;
        }
    }

    /**
     * Method changes the events of every node in GridPane
     */
    private void ChangeOnMouseClicked() {
        ObservableList<Node> nodes = gridPaneTop.getChildren();
        for(Node child : nodes)
            child.setOnMouseClicked((EventHandler) (Event event) -> {

                columnIndex = GridPane.getColumnIndex(child);
                game.setColumnIndex(columnIndex);
                MoveFace();

               // Animate(child);

                game.ChangeSeaV2();


                gridPaneTop.getChildren().clear();
                ReWriteForm();
                ChangeOnMouseClicked();
            });
    }

    /**
     * Method is for moveng the "face" of player on bottom
     */
    private void MoveFace() {
        gridPaneBot.getChildren().clear();
        ImageView imageview = game.GetFaceImage();
        gridPaneBot.add(imageview,columnIndex, 0);
        GridPane.setValignment(imageview, VPos.CENTER);
        GridPane.setHalignment(imageview, HPos.CENTER);
    }

    /**
     * Method is for stoping the game if player lost (some fishes got bottom of the field)
     * @return true = if fishes get bottom, false = if not
     */
    private Boolean GameOver()
    {
        for(int i=0;i<6;i++) {
            if (game.getOurSea()[7][i] != null) {
                return true;
            }
        }
        return false;
    }
}
