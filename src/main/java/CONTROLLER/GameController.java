package CONTROLLER;

import MODELS.*;
import javafx.scene.image.ImageView;

import java.util.*;

public class GameController {
    private Integer columnIndex;

    private MainFish currentFish; //   яку ловимо
    private MainFish nextFish; // кормити

    private MainFish[][] sea;

    private Integer scoreCounter = 0;

    public Integer getScoreCounter() {
        return scoreCounter;
    }

    public ImageView GetFaceImage()
    {
        return new ImageView(MainFish.getView("src/main/resources/image/beee.gif"));
    }

    public void addNewWave ()
    {
        MainFish[][] field = new MainFish[8][6];
        MainFish[] fishes = creation();
        int[] arr = randomization();

        for(int i=1;i<8;i++)
        {
            System.arraycopy(this.sea[i - 1], 0, field[i], 0, 6);
        }

        for(int i=0;i<6;i++)
        {
            field[0][i] = fishes[arr[i]];
        }

        this.sea = new MainFish[8][6];

        for(int i=0;i<8;i++)
        {
            System.arraycopy(field[i], 0, this.sea[i], 0, 6);
        }
    }

    public GameController() {
        sea = CreateSea();
        //player = new Player();
    }

    public MainFish[][] getOurSea() {return  sea;}


    private int[] randomization ()
    {
        final int N = 36;
        ArrayList<Integer> arrayList = new ArrayList<>(N);
        Random random = new Random();

        while (arrayList.size() < N) {
            int i = random.nextInt(N);
            if (!arrayList.contains(i)) {
                arrayList.add(i);
            }
        }

         return arrayList.stream().mapToInt(i -> i).toArray();
    }

    private MainFish [] creation()
    {
        MainFish[] fishes = new MainFish[36];
        for(int i=0;i<36;i++)
        {
            if(i<12)
                fishes[i] = new SmallFish();
            else if (i<24)
                fishes[i] = new MiddleFish();
            else
                fishes[i] = new LargeFish();
        }
        return fishes;
    }

    private MainFish[][] CreateSea()
    {
        MainFish[][] field = new MainFish[8][6];
        MainFish[] fishes = creation();
        int[] arr = randomization();

        for(int i=0;i<8;i++)
        {
            for(int j=0;j<6;j++)
            {
                if(i<2)
                {
                    field[i][j] = fishes[arr[j]];
                    if(i!=0)
                        field[i][j] = fishes[arr[j*4+6]];
                }
                else
                    field[i][j] = null;
            }
        }
        return field;
    }


    public void setColumnIndex(Integer columnInde) {
        this.columnIndex = columnInde;
        //ChangeSeaV2();
    }

    private int FindFish() {
        for(int i = 7; i >= 0; i--) {
            if(!(sea[i][columnIndex] == null)) {
                return i;
            }
        }
        return -1;
    }

    public void printSea() {
        for(int i=0;i<8;i++) {
            for(int j=0;j<6;j++) {
                if(sea[i][j] == null)
                    System.out.print(" | " + ' ' + " |");
                else
                    System.out.print(" | " + sea[i][j].getT() + " |");
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
            sea[index + 1][columnIndex] = currentFish;
            currentFish = null;
            nextFish = null;
            return false;
        }
        else {
            nextFish = sea[index][columnIndex];

            return true;
        }
    }

    public void ChangeSeaV2() {

        int index = FindFish();
        if (!SetFish(index)) return;

         if (nextFish.getTypeFish().ordinal() == 1) {
            if (((MiddleFish) nextFish).eatFish(currentFish)) {
                sea[index][columnIndex] = null;
                scoreCounter += 20;
            }
            else if(currentFish.getTypeFish().ordinal() == 1 || currentFish.getTypeFish().ordinal() == 2)
                sea[index + 1][columnIndex] = currentFish;
        } else if (nextFish.getTypeFish().ordinal() == 2) {
            if (((LargeFish) nextFish).eatFish(currentFish)) {
                sea[index][columnIndex] = null;
                scoreCounter += 30;
            }
            else if(currentFish.getTypeFish().ordinal() == 0 || currentFish.getTypeFish().ordinal() == 2)
                sea[index + 1][columnIndex] = currentFish;
        }
        else
            sea[index + 1][columnIndex] = currentFish;
        currentFish = null;
        nextFish = null;
    }
}
