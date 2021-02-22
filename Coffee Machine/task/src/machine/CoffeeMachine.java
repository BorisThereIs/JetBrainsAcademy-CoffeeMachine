package machine;

import java.util.Scanner;

public class CoffeeMachine {

    enum MachineState {
        MAIN_MENU, SUPPLY_ADDING, CHOOSING_COFFEE, SHUTDOWN;
    }

    enum SuppliesAddingState {
        WATER, MILK, COFFEE_BEANS, CUPS, DONE;
    }

    MachineState currentMachineState;
    SuppliesAddingState currentSuppliesAddingState;

    static final int ESPRESSO_COST = 4;
    static final int LATTE_COST = 7;
    static final int CAPPUCCINO_COST = 6;

    static final int ESPRESSO_CODE = 1;
    static final int LATTE_CODE = 2;
    static final int CAPPUCCINO_CODE = 3;

    private int amountOfCups, amountOfWater, amountOfMilk, amountOfBeans;
    private int money;

    public CoffeeMachine() {
        money = 550;
        amountOfCups = 9;
        amountOfWater = 400;
        amountOfMilk = 540;
        amountOfBeans = 120;
        currentMachineState = MachineState.MAIN_MENU;
    }

    void doService(String cmd) {

        switch (currentMachineState) {
            case MAIN_MENU:
                switch (cmd) {
                    case "buy":
                        currentMachineState = MachineState.CHOOSING_COFFEE;
                        break;
                    case "fill":
                        currentMachineState = MachineState.SUPPLY_ADDING;
                        currentSuppliesAddingState = SuppliesAddingState.WATER;
                        break;
                    case "take":
                        takeMoney();
                        break;
                    case "remaining":
                        getSuppliesStatus();
                        break;
                    case "exit":
                        currentMachineState = MachineState.SHUTDOWN;
                        break;
                }
                break;
            case SUPPLY_ADDING:
                addSupplies(Integer.parseInt(cmd));
                break;
            case CHOOSING_COFFEE:
                String buyOption = cmd;
                if (buyOption.equals("back")) {
                    currentMachineState = MachineState.MAIN_MENU;
                    break;
                } else {
                    int coffeeCode = Integer.parseInt(buyOption);
                    if(isEnoughSupplies(coffeeCode)) {
                        makeCoffee(coffeeCode);
                    }
                    currentMachineState = MachineState.MAIN_MENU;
                }
                break;
        }

    }

    void printContextMessage() {
        switch (currentMachineState) {
            case MAIN_MENU:
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                break;
            case SUPPLY_ADDING:
                switch (currentSuppliesAddingState) {
                    case WATER:
                        System.out.println("Write how many ml of water do you want to add:");
                        break;
                    case MILK:
                        System.out.println("Write how many ml of milk do you want to add:");
                        break;
                    case COFFEE_BEANS:
                        System.out.println("Write how many grams of coffee beans do you want to add:");
                        break;
                    case CUPS:
                        System.out.println("Write how many disposable cups of coffee do you want to add:");
                        break;
                }
                break;
            case CHOOSING_COFFEE:
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                break;
        }
    }

    boolean isItShutdownState() {
        if(currentMachineState == MachineState.SHUTDOWN) {
            return true;
        }
        return false;
    }

    void getSuppliesStatus() {
        System.out.println("The coffee machine has:");
        System.out.println(amountOfWater + " of water");
        System.out.println(amountOfMilk + " of milk");
        System.out.println(amountOfBeans + " of coffee beans");
        System.out.println(amountOfCups + " of disposable cups");
        System.out.println(money + " of money");
    }

    boolean isEnoughSupplies(int coffeeCode) {
        boolean returnValue = false;
        if(amountOfCups == 0) {
            System.out.println("Sorry, not enough cups!");
        }
        switch (coffeeCode) {
            case ESPRESSO_CODE:
                if(amountOfWater < 250) {
                    System.out.println("Sorry, not enough water!");
                    break;
                } else if(amountOfBeans < 16) {
                    System.out.println("Sorry, not enough coffee beans!");
                    break;
                } else {
                    returnValue = true;
                    System.out.println("I have enough resources, making you a coffee!");
                }
                break;
            case LATTE_CODE:
                if(amountOfWater < 350) {
                    System.out.println("Sorry, not enough water!");
                    break;
                } else if(amountOfBeans < 20) {
                    System.out.println("Sorry, not enough coffee beans!");
                    break;
                } else if (amountOfMilk < 75) {
                    System.out.println("Sorry, not enough milk!");
                    break;
                } else {
                    returnValue = true;
                    System.out.println("I have enough resources, making you a coffee!");
                }
                break;
            case CAPPUCCINO_CODE:
                if(amountOfWater < 200) {
                    System.out.println("Sorry, not enough water!");
                    break;
                } else if(amountOfBeans < 12) {
                    System.out.println("Sorry, not enough coffee beans!");
                    break;
                } else if (amountOfMilk < 100) {
                    System.out.println("Sorry, not enough milk!");
                    break;
                } else {
                    returnValue = true;
                    System.out.println("I have enough resources, making you a coffee!");
                }
                break;
        }
        return returnValue;
    }

    void makeCoffee(int coffeeCode) {
        switch(coffeeCode) {
            case ESPRESSO_CODE:
                money += ESPRESSO_COST;
                amountOfCups -= 1;
                amountOfWater -= 250;
                amountOfBeans -= 16;
                break;
            case LATTE_CODE:
                money += LATTE_COST;
                amountOfCups -= 1;
                amountOfWater -= 350;
                amountOfBeans -= 20;
                amountOfMilk -= 75;
                break;
            case CAPPUCCINO_CODE:
                money += CAPPUCCINO_COST;
                amountOfCups -= 1;
                amountOfWater -= 200;
                amountOfBeans -= 12;
                amountOfMilk -= 100;
                break;
        }
    }

    void addSupplies(int amount) {

        switch (currentSuppliesAddingState) {
            case WATER:
                amountOfWater += amount;
                currentSuppliesAddingState = SuppliesAddingState.MILK;
                break;
            case MILK:
                amountOfMilk += amount;
                currentSuppliesAddingState = SuppliesAddingState.COFFEE_BEANS;
                break;
            case COFFEE_BEANS:
                amountOfBeans += amount;
                currentSuppliesAddingState = SuppliesAddingState.CUPS;
                break;
            case CUPS:
                amountOfCups += amount;
                currentMachineState = MachineState.MAIN_MENU;
                break;
        }
    }

    void takeMoney() {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CoffeeMachine machine = new CoffeeMachine();
        String input;
        while(!machine.isItShutdownState()) {
            machine.printContextMessage();
            input = scanner.next();
            machine.doService(input);

        }
    }
}
