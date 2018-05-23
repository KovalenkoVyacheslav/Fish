package MODELS;

import MODELS.ENUMS.TypeFish;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class MiddleFish extends MainFish {
    private Integer counter;

    public MiddleFish() {
        super("src/main/resources/image/midEmpty.png", TypeFish.MIDDLE, "m");
        counter = 0;
    }

    MiddleFish(String path, TypeFish typeFish, String type) {
        super(path, typeFish, type);
        counter = 0;
    }

    public Integer getCounter() {
        return counter;
    }

    void setCounter(Integer counter) {
        this.counter = counter;
    }

    public boolean eatFish(MainFish toEat) {
        if(toEat.getTypeFish().ordinal() == 0) {
            counter++;
            setT(getT().toUpperCase());
            this.setImageFish(MainFish.getView("src/main/resources/image/midFull.png"));
        }
        return counter == 2;
    }
}
