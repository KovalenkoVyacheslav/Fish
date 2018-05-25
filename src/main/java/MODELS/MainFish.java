package MODELS;

import MODELS.ENUMS.TypeFish;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public abstract class MainFish {
    private Image imageFish;
    private TypeFish typeFish;

    MainFish(String path, TypeFish typeFish) {
        this.imageFish = getView(path);
        this.typeFish = typeFish;
    }

    public ImageView getImageViewFish() {
        ImageView view = new ImageView();
        view.setImage(imageFish);
        return view;
    }

    void setImageFish(Image imageFish) {
        this.imageFish = imageFish;
    }

    public TypeFish getTypeFish() {
        return typeFish;
    }


    public static Image getView (String path) {
        File file = new File(path);
        return new Image(file.toURI().toString());
    }
}
