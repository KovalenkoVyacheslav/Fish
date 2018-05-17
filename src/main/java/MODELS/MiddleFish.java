package MODELS;

import MODELS.ENUMS.TypeFish;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MiddleFish extends MainFish {
    private Integer counter;

    public MiddleFish() {
        super("src/main/resources/image/middle2.png", TypeFish.MIDDLE);
        counter = 0;
    }

    public MiddleFish(String path, TypeFish typeFish) {
        super(path, typeFish);
        counter = 0;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public void ChangeFishFace() {
        if(counter == 1) {
            this.setImageFish(MainFish.getView("src/main/resources/image/EatenMid.jpg"));
        }
    }
}
