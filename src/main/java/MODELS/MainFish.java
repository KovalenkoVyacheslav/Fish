package MODELS;

import MODELS.ENUMS.TypeFish;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

/**
 * Abstract main class
 */
public abstract class MainFish {
    private Image imageFish;
    private TypeFish typeFish;

    MainFish(String path, TypeFish typeFish) {
        this.imageFish = getView(path);
        this.typeFish = typeFish;
    }

    /**
     * Method for get ImageView to form
     * @return imageview object
     */
    public ImageView getImageViewFish() {
        ImageView view = new ImageView();
        view.setImage(imageFish);
        return view;
    }

    /**
     * Set new image for object
     * @param imageFish - new image
     */
    void setImageFish(Image imageFish) {
        this.imageFish = imageFish;
    }

    /**
     * Get current type of fish
     * @return fish type
     */
    public TypeFish getTypeFish() {
        return typeFish;
    }

    /**
     * Get ImageView using path
     * @param path - path for image
     * @return ImageView for image
     */
    public static Image getView (String path) {
        File file = new File(path);
        return new Image(file.toURI().toString());
    }
}
