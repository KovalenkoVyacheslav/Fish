package VIEW;

import CONTROLLER.GameController;
import MODELS.MainFish;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class FXMLMainFormController implements Initializable {

    private Integer columnIndex=0;
    private GameController game;
    private Timer time = new Timer();
    AnchorPane pane;

    @FXML
    private AnchorPane AnchorAp;

    @FXML
    private SplitPane Split_main;

    @FXML
    private GridPane gridPaneTop;

    @FXML
    private GridPane gridPaneBot;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NewLevel();
        Image img = MainFish.getView("src/main/resources/image/default.jpg");

        BackgroundImage myBI= new BackgroundImage(img,BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(785,688,
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

    private Boolean ReWriteForm() {
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


    private void MoveFace() {
        gridPaneBot.getChildren().clear();
        ImageView imageview = game.GetFaceImage();
        gridPaneBot.add(imageview,columnIndex, 1);
        GridPane.setValignment(imageview, VPos.CENTER);
        GridPane.setHalignment(imageview, HPos.CENTER);
    }

    private Boolean GameOver()
    {
        for(int i=0;i<6;i++) {
            if (game.getOurSea()[7][i] != null) {
                return true;
            }
        }
        return false;
    }

//    private void Animate (Node child) {
//        pane = new AnchorPane();
//        pane.setMaxHeight(300);
//        pane.setMaxWidth(600);
//        pane.setMaxWidth(600);
//        pane.setMaxHeight(300);
//        int column = columnIndex;
//        int row = 0;
//        if(game.FindFish()!=-1)
//            row = game.FindFish();
//        MainFish fish = game.getOurSea()[row][column];
//
//        Bounds bounds = child.getBoundsInParent();
//        ImageView view = fish.getImageViewFish();
//
//        view.setFitWidth(90);
//        view.setFitHeight(30);
//
//        pane.getChildren().add(view);
//        view.setX(bounds.getMinX());
//        view.setY(bounds.getMaxY());
//        pane.setOpacity(1);
//        AnchorAp.getChildren().add(pane);
//
//
//        TranslateTransition tt = new TranslateTransition(Duration.millis(5000), view);
//        tt.setByY(300f);
//        tt.play();
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//    }
}
