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

import java.awt.image.AreaAveragingScaleFilter;
import java.io.File;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameController {
    Integer columnIndex;
    MainFish eatenFish = null;

    private MainFish currentFish; //   яку ловимо
    private MainFish nextFish; // кормити

    MainFish[][] sea;

//            = new MainFish[][]{
//        {new SmallFish(), new SmallFish(),new MiddleFish() , new MiddleFish(), null, new LargeFish()},
//        {null, null,            null, null,             null, new LargeFish()},
//        {null, null,            null, null,             null, null},
//        {null, null,            null, null,             null, null},
//        {null, null,            null, null,             null, null},
//        {null, null,            null, null,             null, null},
//        {null, null,            null, null,             null, null},
//        {null, null,            null, null,             null, null}
 //   };


//    public ImageView GetFaceImage()
//    {
//        return new ImageView(MainFish.getView("src/main/resources/image/Man.png"));
//    }


    public void addNewWave ()
    {
        MainFish [] [] newSea = new MainFish[8][6];

        for(int i=1;i<8;i++)
        {
            for (int j = 0; j < 6; j++)
            {
                newSea[i][j] = sea[i-1][j];
            }
        }
        MainFish[] fishes = new MainFish[6];

        for(int i=0;i<6;i++)
        {
            if(i<2)
                fishes[i] = new SmallFish();
            else if (i>=2 && i<4)
                fishes[i] = new MiddleFish();
            else if (i>=6)
                fishes[i] = new LargeFish();
        }

        for(int i = 0; i< 6; i++)
        {
            newSea[0][i] = fishes[new Random().nextInt(fishes.length)];
        }
        this.sea = newSea;
    }


    public GameController() {
        sea = CreateSea();
    }

    public MainFish[][] getOurSea() {return  sea;}


    public MainFish[][] CreateSea()
    {
        MainFish[][] field = new MainFish[8][6];
        MainFish[] fishes = new MainFish[12];

        for(int i=0;i<12;i++)
        {
            if(i<4)
                fishes[i] = new SmallFish();
            else if (i>=4 && i<8)
                fishes[i] = new MiddleFish();
            else if (i>=8)
                fishes[i] = new LargeFish();

        }

        for(int i=0;i<8;i++)
        {
            for(int j=0;j<6;j++)
            {
                if(i<2)
                {
                    field[i][j] = fishes[new Random().nextInt(fishes.length)];
                }
                else
                    field[i][j] = null;
            }
        }
        return field;
    }


    public void setColumnIndex(Integer columnInde) {
        this.columnIndex = columnInde;
        //ChangeSea();
        ChangeSeaV2();
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
                    if(eatenFish.getTypeFish().ordinal() == 0 && temp.getTypeFish().ordinal() == 1 )
                    {
                        ((MiddleFish)temp).setCounter(((MiddleFish)temp).getCounter() + 1);
                        if(((MiddleFish)temp).getCounter()==2) {
                            sea[index][columnIndex] = null;
                        }
                        else {
                            //((MiddleFish)temp).ChangeFishFace();
                            temp.setT(temp.getT().toUpperCase());
                            sea[index][columnIndex] = temp;
                        }
                        eatenFish = null;
                    }
                    else
                    if(eatenFish.getTypeFish().ordinal() == 1 && temp.getTypeFish().ordinal() == 2)
                    {

                        ((LargeFish) temp).setCounter(((LargeFish) temp).getCounter() + 1);
                        MiddleFish mid = (MiddleFish) eatenFish;
                        if (((LargeFish) temp).getCounter() == 2 || mid.getCounter()==1)
                        {
                            sea[index][columnIndex] = null;
                        } else {
                            //((LargeFish) temp).ChangeFishFace();
                            temp.setT(temp.getT().toUpperCase());
                            sea[index][columnIndex] = temp;
                        }
                        eatenFish = null;
                    }
            }
        }
    }

    public void printSea() {
        for(int i=0;i<8;i++) {
            for(int j=0;j<6;j++) {
                if(sea[i][j] == null)
                    System.out.print(" | " + ' ' + " |");
                else
                    System.out.print(" | " + sea[i][j].hashCode() + " |");
            }
            System.out.println();
        }
    }

    private boolean SetFish(int index) {//індекс рядка
        if(currentFish == null && index != -1) {
            currentFish = sea[index][columnIndex];
            sea[index][columnIndex] = null;
            nextFish = null;
            return false;
        }
        if(index == -1) {
            //sea[index][columnIndex] = null;
            sea[index + 1][columnIndex] = currentFish;
            currentFish = null;
            nextFish = null;
            return false;
        }
        if(currentFish != null) {
            nextFish = sea[index][columnIndex];
            return true;
        }
        return false;
    }

    public void ChangeSeaV2() {

        int index = FindFish();
        if (!SetFish(index)) return;

         if (nextFish.getTypeFish().ordinal() == 1) {
            if (((MiddleFish) nextFish).eatFish(currentFish))
                sea[index][columnIndex] = null;
        } else if (nextFish.getTypeFish().ordinal() == 2) {
            if (((LargeFish) nextFish).eatFish(currentFish))
                sea[index][columnIndex] = null;
        }
        else
            sea[index + 1][columnIndex] = currentFish;
        currentFish = null;
        nextFish = null;
    }
}
