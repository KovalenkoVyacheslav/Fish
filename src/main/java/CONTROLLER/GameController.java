package CONTROLLER;

import MODELS.LargeFish;
import MODELS.MainFish;
import MODELS.MiddleFish;
import MODELS.SmallFish;
import javafx.scene.image.ImageView;

import java.util.*;

public class GameController {
    Integer columnIndex;

    private MainFish currentFish; //   яку ловимо
    private MainFish nextFish; // кормити

    MainFish[][] sea;


    public ImageView GetFaceImage()
    {
        return new ImageView(MainFish.getView("src/main/resources/image/Man.png"));
    }

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


    public int[] randomization ()
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

        int[] randomArray = arrayList.stream().mapToInt(i -> i).toArray();
        return randomArray;
    }

    public MainFish [] craetion()
    {
        MainFish[] fishes = new MainFish[36];
        for(int i=0;i<36;i++)
        {
            if(i<10)
                fishes[i] = new SmallFish();
            else if (i>=12 && i<24)
                fishes[i] = new MiddleFish();
            else if (i>=24)
                fishes[i] = new LargeFish();
        }
        return fishes;
    }

    public MainFish[][] CreateSea()
    {
        MainFish[][] field = new MainFish[8][6];
        MainFish[] fishes = craetion();
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
            else if(currentFish.getTypeFish().ordinal() == 1 || currentFish.getTypeFish().ordinal() == 2)
                sea[index + 1][columnIndex] = currentFish;
        } else if (nextFish.getTypeFish().ordinal() == 2) {
            if (((LargeFish) nextFish).eatFish(currentFish))
                sea[index][columnIndex] = null;
            else if(currentFish.getTypeFish().ordinal() == 0 || currentFish.getTypeFish().ordinal() == 2)
                sea[index + 1][columnIndex] = currentFish;
        }
        else
            sea[index + 1][columnIndex] = currentFish;
        currentFish = null;
        nextFish = null;
    }
}
