package MODELS;

import MODELS.ENUMS.TypeFish;

public class LargeFish extends MiddleFish {

    public LargeFish() {
        super("src/main/resources/image/bigEmpty.png", TypeFish.LARGE);
    }

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
