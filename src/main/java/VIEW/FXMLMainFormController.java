package VIEW;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
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

        File file1 = new File("src/main/resources/image/Crystall.png");
        Image image1 = new Image(file1.toURI().toString());
        ImageView imageView1 = new ImageView();
        imageView1.setImage(image1);

        File file2 = new File("src/main/resources/image/Compas.png");
        Image image2 = new Image(file2.toURI().toString());
        ImageView imageView2 = new ImageView();
        imageView2.setImage(image2);

        Image[] im = new Image[]{image, image1, image2};
        ArrayList<ImageView> iv = new ArrayList<>();

        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < gridPaneTop.getColumnConstraints().size(); j++) {
                iv.add(new ImageView(im[new Random().nextInt(3)]));
                gridPaneTop.add(iv.get(iv.size() - 1),j,i);
            }
        }
    }
}
