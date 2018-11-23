package MODELS;

import MODELS.ENUMS.TypeFish;
import javafx.geometry.Bounds;

/**
 * Class implements large fish, extends middle fish
 */
public class LargeFish extends MiddleFish {

    public LargeFish() {
        super("src/main/resources/image/bigEmpty.png", TypeFish.LARGE);
    }

    /**
     * Method for eating fish
     * @param toEat - eaten fish
     * @return true - if the fish exploded, in other case - false
     */
    @Override
    public boolean eatFish(MainFish toEat) {
        if(toEat.getTypeFish().ordinal() == 1) {
            if(((MiddleFish) toEat).getCounter() == 1)
                return true;
            else {
                setCounter(getCounter() + 1);
                this.setImageFish(MainFish.getView("src/main/resources/image/bigFull.png"));
            }
        }
        return getCounter() == 2;
    }
}
