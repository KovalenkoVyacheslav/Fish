package MODELS;

import MODELS.ENUMS.TypeFish;

/**
 * Class implements small fish, extends mainfish
 */
public class SmallFish extends MainFish {
    public SmallFish() {
        super("src/main/resources/image/small.png", TypeFish.SMALL);
    }
}
