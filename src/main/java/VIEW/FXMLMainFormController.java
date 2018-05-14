package VIEW;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLMainFormController implements Initializable {

    @FXML
    private GridPane gridPaneTop;

    @FXML
    private GridPane gridPaneBot;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("src/main/resources/image/SmallSun.png");
        Image image = new Image(file.toURI().toString());
        ImageView imageView = new ImageView();
        imageView.setImage(image);

        gridPaneTop.add(imageView, 0, 0);
    }
}
