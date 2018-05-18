package MODELS;

import MODELS.ENUMS.TypeFish;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LargeFish extends MiddleFish {

    public LargeFish() {
        super("src/main/resources/image/large2.png", TypeFish.LARGE, "l");
    }

    @Override
    public boolean eatFish(MainFish toEat) {
        if(toEat.getTypeFish().ordinal() == 1) {
            MiddleFish temp = (MiddleFish) toEat;
            if(temp.getCounter() == 1)
                return true;
            else {
                setCounter(getCounter() + 1);
                setT(getT().toUpperCase());
            }
        }
        if (getCounter() == 2)
            return true;
        return false;
    }

    //    @Override
//    public void ChangeFishFace() {
//        if (getCounter() == 1) {
//            this.setImageFish(MainFish.getView("src/main/resources/image/EatenBig.jpg"));
//        }
//    }
}
