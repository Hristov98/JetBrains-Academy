package machine;

import java.util.Scanner;

public class CoffeeMachineInternal {
    private int currentWater;
    private int currentMilk;
    private int currentBeans;
    private int currentCups;
    private int currentMoney;
    Scanner input = new Scanner(System.in);

    CoffeeMachineInternal(int water, int milk, int beans, int cups, int money) {
        currentWater = water;
        currentMilk = milk;
        currentBeans = beans;
        currentCups = cups;
        currentMoney = money;
    }

    public void buyCoffee() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");
        String coffeeChoice = input.next();

        switch (coffeeChoice) {
            case "1":
                makeEspresso();
                break;
            case "2":
                makeLatte();
                break;
            case "3":
                makeCappuccino();
        }
    }

    private void makeEspresso() {
        boolean enoughSupplies = validateSuppliesForEspresso();

        if (enoughSupplies) {
            notifyUserForSuccessfulValidation();
            consumeSuppliesForEspresso();
        }
    }

    private boolean validateSuppliesForEspresso() {
        if (waterSuppliesAreNotAtLeast(250)) {
            System.out.println("Sorry, not enough water!");
            return false;
        } else if (beanSuppliesAreNotAtLeast(16)) {
            System.out.println("Sorry, not enough beans!");
            return false;
        } else if (thereAreNoCupsAvailable()) {
            System.out.println("Sorry, not enough cups!");
            return false;
        } else return true;
    }

    private boolean waterSuppliesAreNotAtLeast(int waterNeeded) {
        return currentWater < waterNeeded;
    }

    private boolean milkSuppliesAreNotAtLeast(int milkNeeded) {
        return currentMilk < milkNeeded;
    }

    private boolean beanSuppliesAreNotAtLeast(int beansNeeded) {
        return currentBeans < beansNeeded;
    }

    private boolean thereAreNoCupsAvailable() {
        return currentCups < 1;
    }

    private void notifyUserForSuccessfulValidation(){
        System.out.println("I have enough resources, making you a coffee!");
    }

    private void consumeSuppliesForEspresso() {
        currentWater -= 250;
        currentBeans -= 16;
        currentMoney += 4;
        currentCups--;
    }

    private void makeLatte() {
        boolean enoughSupplies = validateSuppliesForLatte();

        if (enoughSupplies) {
            notifyUserForSuccessfulValidation();
            consumeSuppliesForLatte();
        }
    }

    private boolean validateSuppliesForLatte() {
        if (waterSuppliesAreNotAtLeast(350)) {
            System.out.println("Sorry, not enough water!");
            return false;
        } else if (milkSuppliesAreNotAtLeast(75)) {
            System.out.println("Sorry, not enough milk!");
            return false;
        } else if (beanSuppliesAreNotAtLeast(20)) {
            System.out.println("Sorry, not enough beans!");
            return false;
        } else if (thereAreNoCupsAvailable()) {
            System.out.println("Sorry, not enough cups!");
            return false;
        } else return true;
    }

    private void consumeSuppliesForLatte() {
        currentWater -= 350;
        currentMilk -= 75;
        currentBeans -= 20;
        currentMoney += 7;
        currentCups--;
    }

    private void makeCappuccino() {
        boolean enoughSupplies = validateSuppliesForCappuccino();

        if (enoughSupplies) {
            notifyUserForSuccessfulValidation();
            consumeSuppliesForCappuccino();
        }
    }

    private boolean validateSuppliesForCappuccino() {
        if (waterSuppliesAreNotAtLeast(200)) {
            System.out.println("Sorry, not enough water!");
            return false;
        } else if (milkSuppliesAreNotAtLeast(100)) {
            System.out.println("Sorry, not enough milk!");
            return false;
        } else if (beanSuppliesAreNotAtLeast(12)) {
            System.out.println("Sorry, not enough beans!");
            return false;
        } else if (thereAreNoCupsAvailable()) {
            System.out.println("Sorry, not enough cups!");
            return false;
        } else return true;
    }

    private void consumeSuppliesForCappuccino() {
        currentWater -= 200;
        currentMilk -= 100;
        currentBeans -= 12;
        currentMoney += 6;
        currentCups--;
    }

    public void fillSupplies() {
        fillWater();
        fillMilk();
        fillBeans();
        fillCups();
    }

    private void fillWater() {
        System.out.println("Write how many ml of water do you want to add: ");
        int waterToAdd = input.nextInt();
        currentWater += waterToAdd;
    }

    private void fillMilk() {
        System.out.println("Write how many ml of milk do you want to add: ");
        int milkToAdd = input.nextInt();
        currentMilk += milkToAdd;
    }

    private void fillBeans() {
        System.out.println("Write how many grams of coffee beans do you want to add: ");
        int beansToAdd = input.nextInt();
        currentBeans += beansToAdd;
    }

    private void fillCups() {
        System.out.println("Write how many disposable cups of coffee do you want to add: ");
        int cupsToAdd = input.nextInt();
        currentCups += cupsToAdd;
    }

    public void printCoffeeMachineSupplies() {
        System.out.println("The coffee machine has: ");
        System.out.printf("%d of water\n", currentWater);
        System.out.printf("%d of milk\n", currentMilk);
        System.out.printf("%d of coffee beans\n", currentBeans);
        System.out.printf("%d of disposable cups\n", currentCups);
        System.out.printf("%d of money\n", currentMoney);
    }

    public void takeMoney() {
        System.out.printf("I gave you $%d\n", currentMoney);
        currentMoney = 0;
    }
}
