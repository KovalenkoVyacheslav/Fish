package MODELS;

import MODELS.ENUMS.TypeFish;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SmallFish extends MainFish {
    public SmallFish() {
        super("src/main/resources/image/small.png", TypeFish.SMALL, "s");
    }


//    public SmallFish(Image imageFish, TypeFish typeFish) {
//        super(imageFish, typeFish);
//    }
}
