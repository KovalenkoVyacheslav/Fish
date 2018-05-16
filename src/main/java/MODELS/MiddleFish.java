package MODELS;

import MODELS.ENUMS.TypeFish;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MiddleFish extends MainFish {
    private Integer counter;

    public MiddleFish() {
        super();
    }

    public MiddleFish(Image imageFish, TypeFish typeFish) {
        super(imageFish, typeFish);
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }
}
