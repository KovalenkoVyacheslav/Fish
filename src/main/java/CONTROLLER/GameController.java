package CONTROLLER;

import MODELS.ENUMS.TypeFish;
import MODELS.LargeFish;
import MODELS.MainFish;
import MODELS.MiddleFish;
import MODELS.SmallFish;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameController {

//    ArrayList<MainFish> fishes;
//    public static Timer t;
    MainFish[][] sea = new MainFish[][]{
        {null, new SmallFish(), null, new MiddleFish(), null, new LargeFish()},
        {null, null,            null, null,             null, new LargeFish()},
        {null, null,            null, null,             null, null},
        {null, null,            null, null,             null, null},
        {null, null,            null, null,             null, null},
        {null, null,            null, null,             null, null},
        {null, null,            null, null,             null, null},
        {null, null,            null, null,             null, null}
    };

    Integer columnIndex;

    public GameController() {
//    MainFish Lfish = new LargeFish(getView("src/main/resources/image/large2.png"), TypeFish.LARGE);
//    MainFish Mfish = new MiddleFish(getView("src/main/resources/image/middle2.png"), TypeFish.MIDDLE);
//    MainFish Sfish = new SmallFish(getView("src/main/resources/image/small2.png"), TypeFish.SMALL);
//
//    fishes = new ArrayList<>();
//    fishes.add(Lfish);
//    fishes.add(Mfish);
//    fishes.add(Sfish);
//    t=new Timer();
    }

    public MainFish[][] getOurSea() {return  sea;}
//private Image getView (String path)
//{
//    File file = new File(path);
//    Image image = new Image(file.toURI().toString());
//    return image;
//}
//
//
//    public ImageView GetFaceImage() {
//        File file0 = new File("src/main/resources/image/Man.png");
//        Image image0 = new Image(file0.toURI().toString());
//        ImageView imageView0 = new ImageView();
//        imageView0.setImage(image0);
//        return imageView0;
//    }


//   public static void Period(GridPane gridPane) {
//       t.scheduleAtFixedRate(new TimerTask() {
//           @Override
//           public void run() {
//                addHeaderRow(gridPane);
//           }
//       }, 0, 5*1000);
//   }

//        public static void addHeaderRow(GridPane gridPane) {
//        for (Node node : gridPane.getChildren()) {
//            GridPane.setRowIndex(node, GridPane.getRowIndex(node)+1);
//            if(GridPane.getRowIndex(node)==7)
//                gridPane.getChildren().remove(node);
//        }
//        Thread s = new Thread(() -> {
//            for (int colIndex = 0; colIndex < 6; colIndex++) {
//                Label header = new Label("Header " + (colIndex + 1));
//                header.setStyle("-fx-background-color: lightgray;");
//                header.setMaxWidth(Double.POSITIVE_INFINITY);
//                gridPane.add(header, colIndex, 0);
//            }
//        });
//            try {
//                s.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//
//
//    public void StartGame(GridPane gridPaneTop) {
//
//        for(int i = 0; i < 8; i++) {
//            for(int j = 0; j < gridPaneTop.getColumnConstraints().size(); j++) {
//                if (i<2) {
//                    ImageView fish = new ImageView();
//                    fish.setImage(fishes.get(new Random().nextInt(3)).getImageFish());
//                    fish.setId(i + " " + j);
//                    fish.setFitWidth(60);
//                    fish.setFitHeight(30);
//                    gridPaneTop.add(fish, j, i);
//                    GridPane.setValignment(fish, VPos.CENTER);
//                    GridPane.setHalignment(fish, HPos.CENTER);
//                }
//                else
//                {
//                    Label header = new Label("Header "+(j));
//                    header.setStyle("-fx-background-color: lightgray;");
//                    header.setMaxWidth(Double.POSITIVE_INFINITY);
//                    gridPaneTop.add(header, j, i);
//                }
//            }
//        }
//
//
//    }


    public Integer getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(Integer columnInde) {
        this.columnIndex = columnInde;
    }

    public int FindFish() {
        for(int i = 7; i >= 0; i--) {
            if(!(sea[i][columnIndex] == null)) {
                return i;
            }
        }
        return -1;
    }
}
