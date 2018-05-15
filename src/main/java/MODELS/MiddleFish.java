package MODELS;

import MODELS.ENUMS.TypeFish;
import javafx.scene.image.Image;

public class MiddleFish extends MainFish {
    private Integer counter;

    public MiddleFish() {
        super();
    }

    public MiddleFish(Image imageFish, TypeFish typeFish, Integer counter) {
        super(imageFish, typeFish);
        this.counter = counter;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }
}
