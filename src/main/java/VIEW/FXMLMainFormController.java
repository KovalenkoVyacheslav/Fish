package VIEW;

import CONTROLLER.GameController;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.net.URL;
import java.time.Period;
import java.util.ResourceBundle;

public class FXMLMainFormController implements Initializable {

    Integer columnIndex;
    Integer rowIndex;
    GameController game;
    @FXML
    private GridPane gridPaneTop;

    @FXML
    private GridPane gridPaneBot;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        gridPaneTop.setAlignment(Pos.CENTER);
        game = new GameController();
        gridPaneBot.add(game.GetFaceImage(),0, 1);
        game.StartGame(gridPaneTop);

        ObservableList<Node> nodes = gridPaneTop.getChildren();
        for(Node child : nodes) {
            child.setOnMouseClicked((EventHandler) (Event event) -> {
                columnIndex = GridPane.getColumnIndex(child);
                rowIndex = GridPane.getRowIndex(child);
                System.out.println(columnIndex);
                //RemoveFishByColumn(columnIndex);
                MoveFace();
                ImageView imgView = (ImageView)(child);
                //System.out.println(child.getId());
                GameController.addHeaderRow(gridPaneTop);
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
