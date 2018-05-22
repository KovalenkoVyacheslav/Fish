package VIEW;

import CONTROLLER.GameController;
import MODELS.MainFish;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class FXMLMainFormController implements Initializable {

    Integer columnIndex=0;
    GameController game;
    Timer time = new Timer();

    @FXML
    private SplitPane Split_main;

    @FXML
    private GridPane gridPaneTop;

    @FXML
    private GridPane gridPaneBot;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NewLevel();
        Image img = MainFish.getView("src/main/resources/image/back.jpg");

        BackgroundImage myBI= new BackgroundImage(img,BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(600,400,
                false,false, false,false));

        Split_main.setBackground(new Background(myBI));

        gridPaneTop.setAlignment(Pos.CENTER);
        game = new GameController();
        ReWriteForm();

        MoveFace();
    }

    private void NewLevel()
    {


        time.schedule(new TimerTask() {
            @Override
            public void run() {
                game.addNewWave();
                Platform.runLater(() -> ReWriteForm());
            }
        }, 60*1000, 60*1000);

    }

    public Boolean ReWriteForm() {
        if(GameOver()) {
            time.cancel();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("I have a message for You!");
            alert.setContentText("You lost :(");
            alert.showAndWait();
        }

        gridPaneTop.getChildren().clear();
        try {
            ImageView bg;
            for (int i = 0; i < gridPaneTop.getRowConstraints().size(); i++) {
                for (int j = 0; j < gridPaneTop.getColumnConstraints().size(); j++) {
                    if (game.getOurSea()[i][j] == null)
                        bg = new ImageView(MainFish.getView("src/main/resources/image/empty.png"));
                    else
                        bg = game.getOurSea()[i][j].getImageViewFish();
                    bg.setFitWidth(90);
                    bg.setFitHeight(30);
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

    private void ChangeOnMouseClicked() {
        ObservableList<Node> nodes = gridPaneTop.getChildren();
        for(Node child : nodes)
            child.setOnMouseClicked((EventHandler) (Event event) -> {
                columnIndex = GridPane.getColumnIndex(child);
                MoveFace();
                game.setColumnIndex(columnIndex);
                gridPaneTop.getChildren().clear();
                ReWriteForm();
                ChangeOnMouseClicked();
            });
    }


    private void MoveFace() {
        gridPaneBot.getChildren().clear();
        ImageView imageview = game.GetFaceImage();
        gridPaneBot.add(imageview,columnIndex, 1);
        GridPane.setValignment(imageview, VPos.CENTER);
        GridPane.setHalignment(imageview, HPos.CENTER);
    }

    public Boolean GameOver()
    {
        for(int i=0;i<6;i++) {
            if (game.getOurSea()[7][i] != null) {
                return true;
            }
        }
        return false;
    }

}
