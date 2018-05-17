package CONTROLLER;

import MODELS.ENUMS.TypeFish;
import MODELS.LargeFish;
import MODELS.MainFish;
import MODELS.MiddleFish;
import MODELS.SmallFish;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameController {
    Integer columnIndex;
    MainFish eatenFish = null;

    MainFish[][] sea = new MainFish[][]{
        {new SmallFish(), new SmallFish(),new MiddleFish() , new MiddleFish(), null, new LargeFish()},
        {null, null,            null, null,             null, new LargeFish()},
        {null, null,            null, null,             null, null},
        {null, null,            null, null,             null, null},
        {null, null,            null, null,             null, null},
        {null, null,            null, null,             null, null},
        {null, null,            null, null,             null, null},
        {null, null,            null, null,             null, null}
    };



    public GameController() {
    }

    public MainFish[][] getOurSea() {return  sea;}



    public Integer getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(Integer columnInde) {
        this.columnIndex = columnInde;
        ChangeSea();
    }

    public int FindFish() {
        for(int i = 7; i >= 0; i--) {
            if(!(sea[i][columnIndex] == null)) {
                return i;
            }
        }
        return -1;
    }

    public void ChangeSea() {
        int index = FindFish();
        if(index == -1) {
            return;
        }
        else {
            if(eatenFish == null) {
                eatenFish = sea[index][columnIndex];
                sea[index][columnIndex] = null;
            }
            else {
                MainFish temp = sea[index][columnIndex];

                if(eatenFish.getTypeFish() == temp.getTypeFish() ||
                        TypeFish.valueOf(eatenFish.getTypeFish().toString()).ordinal() >
                                TypeFish.valueOf(temp.getTypeFish().toString()).ordinal())
                    return;
                else
                    if(eatenFish.getTypeFish().ordinal() == 0 && temp.getTypeFish().ordinal() == 1)
                    {
                        MiddleFish fish = (MiddleFish) temp;
                        fish.setCounter(fish.getCounter() + 1);
                        if(fish.getCounter()==2) {
                            sea[index][columnIndex] = null;
                        }
                        else {
                            fish.ChangeFishFace();
                            sea[index][columnIndex] = fish;
                        }
                        eatenFish = null;
                    }
                    else
                    if(eatenFish.getTypeFish().ordinal() == 1 && temp.getTypeFish().ordinal() == 2)
                    {
                        LargeFish fish = (LargeFish) temp;
                        fish.setCounter(fish.getCounter() + 1);
                        MiddleFish mid = (MiddleFish) eatenFish;
                        if (fish.getCounter() == 2 || mid.getCounter()==1)
                        {
                            sea[index][columnIndex] = null;
                        } else {
                            fish.ChangeFishFace();
                            sea[index][columnIndex] = fish;
                        }
                        eatenFish = null;
                    }
            }
        }
    }
}
