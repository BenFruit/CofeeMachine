import java.util.Arrays;
import java.util.Scanner;

class Coffee{
    int waterU;
    int milkU;
    int beansU;
    int cost;
    Coffee(int waterU,int milkU,int beansU,int cost){
        this.waterU = waterU;
        this.milkU = milkU;
        this.beansU = beansU;
        this.cost = cost;
    }
    Coffee(int waterU,int beansU,int cost){
        this.waterU = waterU;
        this.beansU = beansU;
        this.cost = cost;
    }
}
public class CoffeeMachine {
    public static void main(String[] args) {
        int n;
        int totalWater, storageW = 400;
        int totalMilk, storageM = 540;
        int totalBeans, storageB = 120;
        int storageCups = 9,storageMoney = 550;
        int possibleCups = 0;
        Scanner scanner = new Scanner(System.in);

        boolean flag = true;
        do {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            String action = scanner.next();
            switch (action) {
                case ("buy"):
                    int[] result = buyCoffee(storageW, storageM, storageB,storageCups);
                    if (result != null) {
                        System.out.println("I have enough resources, making you a coffee!");
                        storageW = result[0];
                        storageM = result[1];
                        storageB = result[2];
                        storageMoney += result[3];
                        storageCups--;
                    }
                    break;
                case ("fill"):
                    int var = 0;
                    System.out.println("Write how many ml of water you want to add: ");
                    storageW = fillStorage(storageW);
                    System.out.println("Write how many ml of milk you want to add: ");
                    storageM = fillStorage(storageM);
                    System.out.println("Write how many grams of coffee beans you want to add: ");
                    storageB = fillStorage(storageB);
                    System.out.println("Write how many disposable cups of coffee you want to add: ");
                    storageCups = fillStorage(storageCups);
                    break;
                case ("take"):
                    System.out.format("I gave you $%d%n", storageMoney);
                    storageMoney = 0;
                    break;
                case ("remaining"):
                    showInfo(storageW,storageM,storageB,storageCups,storageMoney);
                    break;
                case ("exit") :
                    flag = false;
                    break;
            }
        }
        while (flag);



    }

    private static int fillStorage(int storageW) {
        Scanner scanner = new Scanner(System.in);
        int var = scanner.nextInt();
        storageW += var;
        return storageW;
    }

    private static void showInfo(int storageW, int storageM, int storageB, int storageCups, int storageMoney) {
        System.out.println("The coffee machine has:");
        System.out.format("%d ml of water%n" +
                "%d ml of milk%n" +
                "%d g of coffee beans%n"+"%d disposable cups%n"+
                "$%d of money%n", storageW, storageM, storageB, storageCups, storageMoney);
    }

    private static int[] buyCoffee(int storageW, int storageM, int storageB, int storageCups) {
        Coffee espresso = new Coffee(250,16,4);
        Coffee latte = new Coffee(350,75,20,7);
        Coffee cappuccino = new Coffee(200,100,12,6);

        int[] ans = new int[5];
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();

        switch (choice) {
            case ("1") :
                ans[0] = storageW - espresso.waterU;
                ans[1] = storageM;
                ans[2] = storageB - espresso.beansU;
                ans[3] = espresso.cost;
                ans[4] = storageCups - 1;
                if (actionCheckAnswer(ans)) return null;

                break;
            case ("2") :
                ans[0] = storageW - latte.waterU;
                ans[1] = storageM - latte.milkU;
                ans[2] = storageB - latte.beansU;
                ans[3] = latte.cost;
                ans[4] = storageCups - 1;
                if (actionCheckAnswer(ans)) return null;
                break;
            case ("3") :
                ans[0] = storageW - cappuccino.waterU;
                ans[1] = storageM - cappuccino.milkU;
                ans[2] = storageB - cappuccino.beansU;
                ans[3] = cappuccino.cost;
                ans[4] = storageCups - 1;
                if (actionCheckAnswer(ans)) return null;
                break;
            case ("back") :
                return null;
        }
        return ans;

    }

    private static boolean actionCheckAnswer(int[] ans) {
        if(ans[0] < 0){
            System.out.println("Sorry, not enough water!");
            return true;
        }
        if(ans[1] < 0){
            System.out.println("Sorry, not enough milk!");
            return true;
        }
        if( ans[2] < 0 ){
            System.out.println("Sorry, not enough coffee beans!");
            return true;
        }
        if(ans[4] < 0)
        {
            System.out.println("Sorry, not enough cups!");
            return true;
        }
        return false;
    }


    private static int getTotalAmount(int n, int waterU) {
        int totalWater;
        totalWater = waterU * n;
        return totalWater;
    }

}