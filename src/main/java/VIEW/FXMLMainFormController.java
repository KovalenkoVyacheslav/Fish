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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import java.net.URL;
import java.time.Period;
import java.util.ResourceBundle;

public class FXMLMainFormController implements Initializable {

    Integer columnIndex;
    GameController game;

    @FXML
    private GridPane gridPaneTop;

    @FXML
    private GridPane gridPaneBot;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        gridPaneTop.setAlignment(Pos.CENTER);
        gridPaneTop.setGridLinesVisible(true);
        game = new GameController();
        ReWriteForm();
        ChangeOnMouseClicked();
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
                    gridPaneTop.add(bg, j, i);
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
                game.setColumnIndex(columnIndex);
                if(ReWriteForm())
                    ChangeOnMouseClicked();
            });
        }
    }







//    private void MoveFace() {
//        gridPaneBot.getChildren().clear();
//        ImageView imgv = game.GetFaceImage();
//        gridPaneBot.add(imgv,columnIndex, 1);
//        GridPane.setValignment(imgv, VPos.CENTER);
//        GridPane.setHalignment(imgv, HPos.CENTER);
//    }
}
