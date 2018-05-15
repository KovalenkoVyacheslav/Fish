package CONTROLLER;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class GameController {

    public ImageView GetFaceImage() {
        File file0 = new File("src/main/resources/image/Man.png");
        Image image0 = new Image(file0.toURI().toString());
        ImageView imageView0 = new ImageView();
        imageView0.setImage(image0);
        return imageView0;
    }

    public void StartGame(GridPane gridPaneTop) {
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
    }
}
