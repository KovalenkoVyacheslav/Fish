package MODELS;

import MODELS.ENUMS.TypeFish;

/**
 * Class implements middle fish, extends mainfish
 */
public class MiddleFish extends MainFish {
    private Integer counter;

    public MiddleFish() {
        super("src/main/resources/image/midEmpty.png", TypeFish.MIDDLE);
        counter = 0;
    }

    MiddleFish(String path, TypeFish typeFish) {
        super(path, typeFish);
        counter = 0;
    }

    /**
     * Get counter of small fish
     * @return current eaten fish
     */
    public Integer getCounter() {
        return counter;
    }

    /**
     * Set counter
     * @param counter number of fish eaten
     */
    void setCounter(Integer counter) {
        this.counter = counter;
    }

    /**
     * Method for eating fish
     * @param toEat - eaten fish
     * @return true - if the fish exploded, in other case - false
     */
    public boolean eatFish(MainFish toEat) {
        if(toEat.getTypeFish().ordinal() == 0) {
            counter++;
            this.setImageFish(MainFish.getView("src/main/resources/image/midFull.png"));
        }
        return counter == 2;
    }
}
