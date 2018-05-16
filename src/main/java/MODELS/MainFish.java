package MODELS;

import MODELS.ENUMS.TypeFish;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class MainFish {
    private Image imageFish;
    private TypeFish typeFish;

    public MainFish() {
    }

    public MainFish(Image imageFish, TypeFish typeFish) {
        this.imageFish = imageFish;
        this.typeFish = typeFish;
    }

    public Image getImageFish() {
        return imageFish;
    }

    public void setImageFish(Image imageFish) {
        this.imageFish = imageFish;
    }

    public TypeFish getTypeFish() {
        return typeFish;
    }

    public void setTypeFish(TypeFish typeFish) {
        this.typeFish = typeFish;
    }
}
