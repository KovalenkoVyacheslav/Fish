package CONTROLLER;

import MODELS.ENUMS.TypeFish;
import MODELS.LargeFish;
import MODELS.MainFish;
import MODELS.MiddleFish;
import MODELS.SmallFish;
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

    ArrayList<MainFish> fishes;

public GameController()
{
    MainFish Lfish = new LargeFish(getView("src/main/resources/image/large2.png"), TypeFish.LARGE);
    MainFish Mfish = new MiddleFish(getView("src/main/resources/image/middle2.png"), TypeFish.MIDDLE);
    MainFish Sfish = new SmallFish(getView("src/main/resources/image/small2.png"), TypeFish.SMALL);

    fishes = new ArrayList<>();
    fishes.add(Lfish);
    fishes.add(Mfish);
    fishes.add(Sfish);
}

private Image getView (String path)
{
    File file = new File(path);
    Image image = new Image(file.toURI().toString());
    return image;
}


    public ImageView GetFaceImage() {
        File file0 = new File("src/main/resources/image/Man.png");
        Image image0 = new Image(file0.toURI().toString());
        ImageView imageView0 = new ImageView();
        imageView0.setImage(image0);
        return imageView0;
    }

    public void StartGame(GridPane gridPaneTop) {

        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < gridPaneTop.getColumnConstraints().size(); j++) {
                ImageView fish=new ImageView();
                fish.setImage(fishes.get(new Random().nextInt(3)).getImageFish());

                fish.setFitWidth(60);
                fish.setFitHeight(30);
                gridPaneTop.add(fish, j, i);
                GridPane.setValignment(fish, VPos.CENTER);
                GridPane.setHalignment(fish, HPos.CENTER);
            }
        }

        gridPaneTop.setGridLinesVisible(true);
    }
}
