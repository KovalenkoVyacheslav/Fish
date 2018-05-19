package VIEW;

import CONTROLLER.GameController;
import MODELS.MainFish;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
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
    //Timer time = new Timer();

    @FXML
    private SplitPane Split_main;

    @FXML
    private GridPane gridPaneTop;

    @FXML
    private GridPane gridPaneBot;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image img = MainFish.getView("src/main/resources/image/back.jpg");

        BackgroundImage myBI= new BackgroundImage(img,BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(600,400,
                false,false, false,false));

        Split_main.setBackground(new Background(myBI));

        gridPaneTop.setAlignment(Pos.CENTER);
        game = new GameController();
        ReWriteForm();
        ChangeOnMouseClicked();
        MoveFace();

//        time.schedule(new TimerTask() {
//            @Override
//            public void run() { //ПЕРЕЗАГРУЖАЕМ МЕТОД RUN В КОТОРОМ ДЕЛАЕТЕ ТО ЧТО ВАМ НАДО
//
//                        game.addNewWave();
//                        ReWriteForm();
//                    //time.cancel();
//                   // return;
//                }
//        }, 10000, 10000); //(4000 - ПОДОЖДАТЬ ПЕРЕД НАЧАЛОМ В МИЛИСЕК, ПОВТОРЯТСЯ 4 СЕКУНДЫ (1 СЕК = 1000 МИЛИСЕК))
//
    }

    private Boolean ReWriteForm() {
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
            return true;
        } catch (Exception exp) {
            return false;
        }
    }

    private void ChangeOnMouseClicked() {
        ObservableList<Node> nodes = gridPaneTop.getChildren();
        for(Node child : nodes) {
            child.setOnMouseClicked((EventHandler) (Event event) -> {
                columnIndex = GridPane.getColumnIndex(child);
                MoveFace();
                game.setColumnIndex(columnIndex);
                gridPaneTop.getChildren().clear();
                ReWriteForm();
                ChangeOnMouseClicked();
            });
        }
    }


    private void MoveFace() {
        gridPaneBot.getChildren().clear();
        ImageView imgv = game.GetFaceImage();
        gridPaneBot.add(imgv,columnIndex, 1);
        GridPane.setValignment(imgv, VPos.CENTER);
        GridPane.setHalignment(imgv, HPos.CENTER);
    }
}
