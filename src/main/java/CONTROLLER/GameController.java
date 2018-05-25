package CONTROLLER;

import MODELS.*;
import javafx.scene.image.ImageView;

import java.util.*;

/**
 * class for operating game process
 */
public class GameController {

    private Integer columnIndex;
    private MainFish currentFish;
    private MainFish nextFish;
    private MainFish[][] sea;
    private Integer scoreCounter = 0;

    /**
     * Constructor which initialize our matrix
     */
    public GameController() {
        sea = CreateSea();
    }

    /**
     * Getter of sea field
     * @return matrix of sea
     */
    public MainFish[][] getOurSea() {return  sea;}

    /**
     * Getter of scoreCounter field
     * @return scoreCounter
     */
    public Integer getScoreCounter() {
        return scoreCounter;
    }

    /**
     * function to create ImageView of our fish in visual model
     * @return ImageView with preseted picture
     */
    public ImageView GetFaceImage()
    {
        return new ImageView(MainFish.getView("src/main/resources/image/beee.gif"));
    }

    /**
     * Method to create random new wave of fishes on top of matrix
     */
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

    /**
     * Method generates an array of random digits without repeat. Needed for initialization of the field sea
     * @return array int
     */
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

    /**
     * Method generates array of fishes. Needed for initialiation of the field sea
     * @return Array of fishes
     */
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

    /**
     * Method creates fishes in 2 upper string of matrix
     * @return matrix of fishes which ready to be placed on gamefield
     */
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

    /**
     * setter for columnIndex field
     * @param columnIndex the only parameter
     */
    public void setColumnIndex(Integer columnIndex)
    {
        this.columnIndex = columnIndex;
    }

    /**
     * Method for finding the row index of fish in bottom of column
     * @return rowindex of fish or -1 if there is no fish in column
     */
    private int FindFish()
    {
        for(int i = 7; i >= 0; i--)
            if(!(sea[i][columnIndex] == null))
                return i;
        return -1;
    }

    /**
     * Method is for setting fishes on field
     * @param index - rowindex of fish
     * @return false = if fish not found in column or its first click on fish,
     * true = if fish setted on current position
     */
    private boolean SetFish(int index)
    {
        if(currentFish == null && index != -1)
        {
            currentFish = sea[index][columnIndex];
                    sea[index][columnIndex] = null;
                    nextFish = null;
                return false;
        }
        if(index == -1)
        {
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

    /**
     * Method is for changing our matrix depending on clicks on the field
     */
    public void ChangeSeaV2()
    {
        int index = FindFish();
        if (!SetFish(index)) return;

        if (nextFish.getTypeFish().ordinal() == 1)
        {
            if (((MiddleFish) nextFish).eatFish(currentFish))
            {
                sea[index][columnIndex] = null;
                scoreCounter += 20;
            }
            else if(currentFish.getTypeFish().ordinal() == 1 || currentFish.getTypeFish().ordinal() == 2)
                sea[index + 1][columnIndex] = currentFish;
        }
        else if (nextFish.getTypeFish().ordinal() == 2)
        {
            if (((LargeFish) nextFish).eatFish(currentFish))
            {
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

    /**
     * Method is for checking if there is fishes in matrix
     * @return true = if there is, false = if there is not
     */
    public boolean CheckField ()
    {
        for(int i=0;i<8;i++)
            for (int j = 0; j < 6; j++)
                if (sea[i][j] != null)
                    return true;
        return false;
    }
}
