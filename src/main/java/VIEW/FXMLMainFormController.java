package VIEW;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class FXMLMainFormController implements Initializable {

    Integer columnIndex;
    @FXML
    private GridPane gridPaneTop;

    @FXML
    private GridPane gridPaneBot;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        File file0 = new File("src/main/resources/image/Man.png");
        Image image0 = new Image(file0.toURI().toString());
        ImageView imageView0 = new ImageView();
        imageView0.setImage(image0);

        gridPaneBot.add(imageView0,0, 1);

        File file = new File("src/main/resources/image/large.png");
        Image image = new Image(file.toURI().toString());
        ImageView imageView = new ImageView();
        imageView.setImage(image);

        File file1 = new File("src/main/resources/image/middle.png");
        Image image1 = new Image(file1.toURI().toString());
        ImageView imageView1 = new ImageView();
        imageView1.setImage(image1);

        File file2 = new File("src/main/resources/image/small.png");
        Image image2 = new Image(file2.toURI().toString());
        ImageView imageView2 = new ImageView();
        imageView2.setImage(image2);

        Image[] im = new Image[]{image, image1, image2};
        ArrayList<ImageView> iv = new ArrayList<>();

        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < gridPaneTop.getColumnConstraints().size(); j++) {
                iv.add(new ImageView(im[new Random().nextInt(3)]));
                gridPaneTop.add(iv.get(iv.size() - 1), j, i);
            }
        }


        ObservableList<Node> nodes = gridPaneTop.getChildren();
        for(Node child : nodes) {
            child.setOnMouseClicked((EventHandler) event -> {
                columnIndex = GridPane.getColumnIndex(child);
                System.out.println(columnIndex);
//                if(GridPane.getColumnIndex(child) == 0)

            });
        }
    }
}
