package VIEW;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

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

        File file = new File("src/main/resources/image/large2.png");
        Image image = new Image(file.toURI().toString());
        ImageView imageView = new ImageView();
        imageView.setImage(image);

        File file1 = new File("src/main/resources/image/middle2.png");
        Image image1 = new Image(file1.toURI().toString());
        ImageView imageView1 = new ImageView();
        imageView1.setImage(image1);

        File file2 = new File("src/main/resources/image/small2.png");
        Image image2 = new Image(file2.toURI().toString());
        ImageView imageView2 = new ImageView();
        imageView2.setImage(image2);

        Image[] im = new Image[]{image, image1, image2};
        ArrayList<ImageView> iv = new ArrayList<>();
        gridPaneTop.setAlignment(Pos.CENTER);

        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < gridPaneTop.getColumnConstraints().size(); j++) {
                iv.add(new ImageView(im[new Random().nextInt(3)]) );
                iv.get(iv.size() - 1).setFitWidth(60);
                iv.get(iv.size() - 1).setFitHeight(30);
                gridPaneTop.add(iv.get(iv.size() - 1), j, i);
                GridPane.setValignment(iv.get(iv.size() - 1), VPos.CENTER);
                GridPane.setHalignment(iv.get(iv.size() - 1), HPos.CENTER);
            }
        }

        gridPaneTop.setGridLinesVisible(true);


        ObservableList<Node> nodes = gridPaneTop.getChildren();
        for(Node child : nodes) {
            child.setOnMouseClicked((EventHandler) (Event event) -> {
                columnIndex = GridPane.getColumnIndex(child);

                gridPaneBot.getChildren().clear();
                File f = new File("src/main/resources/image/Man.png");
                Image img = new Image(file0.toURI().toString());
                ImageView imgv = new ImageView();
                imgv.setImage(img);
                gridPaneBot.add(imgv,columnIndex, 1);
                GridPane.setValignment(imgv, VPos.CENTER);
                GridPane.setHalignment(imgv, HPos.CENTER);
            });
        }
    }
}
