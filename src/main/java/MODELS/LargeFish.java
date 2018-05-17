package MODELS;

import MODELS.ENUMS.TypeFish;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LargeFish extends MiddleFish {

    public LargeFish() {
        super("src/main/resources/image/large2.png", TypeFish.LARGE);
    }


    @Override
    public void ChangeFishFace() {
        if (getCounter() == 1) {
            this.setImageFish(MainFish.getView("src/main/resources/image/EatenBig.jpg"));
        }
    }
}
