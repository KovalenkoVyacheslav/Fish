import CONTROLLER.GameController;

import java.util.Scanner;

public class ConsoleClass {
    public static void main(String[] args) {
        int index = 0;
        GameController game = new GameController();
        game.printSea();

        Scanner sc = new Scanner(System.in);

        do {
            ConsoleClass.clearScreen();
            System.out.print("vedit kolonky ribki: ");
            index = sc.nextInt();
            System.out.println("++++++++++++++++++++++++++++++++++++++");
            if(index == 9)
                break;
            else
                game.setColumnIndex(index);
            game.printSea();
        } while (index != 9);
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
