package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private static int currentWater = 400;
    private static int currentMilk = 540;
    private static int currentBeans = 120;
    private static int currentCups = 9;
    private static int currentMoney = 550;

    private static void printCoffeeMachineSupplies() {
        System.out.println("The coffee machine has: ");
        System.out.printf("%d of water\n", currentWater);
        System.out.printf("%d of milk\n", currentMilk);
        System.out.printf("%d of coffee beans\n", currentBeans);
        System.out.printf("%d of disposable cups\n", currentCups);
        System.out.printf("%d of money\n", currentMoney);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit) ");
            String command = input.next();

            if ("buy".equals(command)) {
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");
                String coffeeChoice = input.next();

                if ("1".equals(coffeeChoice)) {
                    if (currentWater < 250) {
                        System.out.println("Sorry, not enough water!");
                    } else if (currentBeans < 16) {
                        System.out.println("Sorry, not enough beans!");
                    } else if (currentCups < 1) {
                        System.out.println("Sorry, not enough cups!");
                    } else {
                        System.out.println("I have enough resources, making you a coffee!");

                        currentWater -= 250;
                        currentBeans -= 16;
                        currentMoney += 4;
                        currentCups--;
                    }
                } else if ("2".equals(coffeeChoice)) {
                    if (currentWater < 350) {
                        System.out.println("Sorry, not enough water!");
                    } else if (currentMilk < 75) {
                        System.out.println("Sorry, not enough milk!");
                    } else if (currentBeans < 20) {
                        System.out.println("Sorry, not enough beans!");
                    } else if (currentCups < 1) {
                        System.out.println("Sorry, not enough cups!");
                    } else {
                        System.out.println("I have enough resources, making you a coffee!");

                        currentWater -= 350;
                        currentMilk -= 75;
                        currentBeans -= 20;
                        currentMoney += 7;
                        currentCups--;
                    }
                } else if ("3".equals(coffeeChoice)) {
                    if (currentWater < 200) {
                        System.out.println("Sorry, not enough water!");
                    } else if (currentMilk < 100) {
                        System.out.println("Sorry, not enough milk!");
                    } else if (currentBeans < 12) {
                        System.out.println("Sorry, not enough beans!");
                    } else if (currentCups < 1) {
                        System.out.println("Sorry, not enough cups!");
                    } else {
                        System.out.println("I have enough resources, making you a coffee!");

                        currentWater -= 200;
                        currentMilk -= 100;
                        currentBeans -= 12;
                        currentMoney += 6;
                        currentCups--;
                    }
                }


            } else if ("fill".equals(command)) {
                System.out.println("Write how many ml of water do you want to add: ");
                int waterToAdd = input.nextInt();
                currentWater += waterToAdd;

                System.out.println("Write how many ml of milk do you want to add: ");
                int milkToAdd = input.nextInt();
                currentMilk += milkToAdd;

                System.out.println("Write how many grams of coffee beans do you want to add: ");
                int beansToAdd = input.nextInt();
                currentBeans += beansToAdd;

                System.out.println("Write how many disposable cups of coffee do you want to add: ");
                int cupsToAdd = input.nextInt();
                currentCups += cupsToAdd;

            } else if ("take".equals(command)) {
                System.out.printf("I gave you $%d\n", currentMoney);
                currentMoney = 0;
            } else if ("remaining".equals(command)) {
                printCoffeeMachineSupplies();
            } else if ("exit".equals(command)) {
                break;
            }
        }
    }
}
