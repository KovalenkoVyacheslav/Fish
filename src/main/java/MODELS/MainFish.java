package MODELS;

import MODELS.ENUMS.TypeFish;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public abstract class MainFish {
    private Image imageFish;
    private TypeFish typeFish;
    private String t;

    public MainFish() {
    }

    public MainFish(String path, TypeFish typeFish) {
        this.imageFish = getView(path);
        this.typeFish = typeFish;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public MainFish(String path, TypeFish typeFish, String type) {
        this.imageFish = getView(path);
        this.typeFish = typeFish;
        this.t = type;

    }
    public ImageView getImageViewFish() {
        ImageView view = new ImageView();
        view.setImage(imageFish);
        return view;
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

    public static Image getView (String path) {
        File file = new File(path);
        Image image = new Image(file.toURI().toString());
    return image;
    }
}
