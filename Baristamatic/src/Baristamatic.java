package com.palmer.sample;

import java.util.TreeMap;
import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
import static com.palmer.sample.BaristaConstants.*;

public class Baristamatic {

    // inventory entry {String ingredient: Double[initialAmount, pricePerUnit]}
    // drinkList entry {Drink drink: Double[price, availability]}
    private static TreeMap<String, Double[]> inventory = new TreeMap<>();
    private static TreeMap<Drink,  Double[]> drinkList = new TreeMap<>();

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        initializeMachine();
        do
        {
            displayMenu();

        } while (isAbleToProcessInput());
    }

    private static void initializeMachine()
    {
          initializeInventory();
          initailizeDrinkList();
    }

    private static void initializeInventory()
    {
        inventory.put(COFFEE, COFFEE_INIT_AMT_PRC);
        inventory.put(DECAF_COFFEE, DECAF_COFFEE_INIT_AMT_PRC);
        inventory.put(SUGAR, SUGAR_INIT_AMT_PRC);
        inventory.put(CREAM, CREAM_INIT_AMT_PRC);
        inventory.put(STEAMED_MILK, STEAMED_MILK_INIT_AMT_PRC);
        inventory.put(ESPRESSO, ESPRESSO_INIT_AMT_PRC);
        inventory.put(FOAMED_MILK, FOAMED_MILK_INIT_AMT_PRC);
        inventory.put(COCOA, COCOA_INIT_AMT_PRC);
        inventory.put(WHIPPED_CREAM, WHIPPED_CREAM_INIT_AMT_PRC);
    }

    private static void initailizeDrinkList()
    {
        BaristaDrinkFactory drinkFactory = new BaristaDrinkFactory();

        for (String drinkName : DRINK_LIST) {
            Drink drink = drinkFactory.makeDrink(drinkName);
            if (drink.getSuccessOrFail()) {
                Double[] drinkPriceAndIsAvailable =
                                                getDrinkPriceAndAvail(drink);
                drinkList.put(drink, drinkPriceAndIsAvailable);
            } else {
                System.out.println(INITIALIZATION_ERROR);
            }
        }
    }

    private static void displayMenu()
    {
        System.out.println(INVENTORY);

        for (String key: inventory.keySet()) {
            System.out.printf(INVENTORY_LISTING, key,
                        getInventoryValue(key, INVENTORY_AMOUNT).intValue());
        }

        System.out.println(MENU);

        for (Drink drink : drinkList.keySet()) {
            System.out.printf(MENU_LISTING, drink.getMenuNumber(),
                                            drink.getDrinkName(),
                                            getDrinkListValue(drink,
                                                        DRINK_LIST_PRICE),
                                            getDrinkListValue(drink,
                                                        DRINK_LIST_AVAILABILITY)
                                                            == CAN_MAKE_DRINK);
        }
    }

    private static Double getInventoryValue(String key, int value)
    {
        return inventory.get(key)[value];
    }

    private static Double getDrinkListValue(Drink drink, int value)
    {
        return drinkList.get(drink)[value];
    }

    private static boolean isAbleToProcessInput()
    {
        String input = scanner.nextLine().toLowerCase().trim();

        if (isValidInput(input)) {
            boolean resultOfProcessing = processInput(input);
            return resultOfProcessing;
        } else {
            System.out.println(INVALID_SELECTION + input);
            return PROCESS_SUCCESS;
        }
    }

    private static boolean isValidInput(String input)
    {
        if (Arrays.asList(MACHINE_KEYWORDS).contains(input)) {
            return PROCESS_SUCCESS;
        } else {
            try {
                Integer inputInt = Integer.parseInt(input);
                return inputInt > 0 && inputInt <= MENU_COUNT;
            } catch (NumberFormatException nfe) {
                return PROCESS_FAILURE;
            }
        }
    }

    private static boolean processInput(String input)
    {
        if (input.equalsIgnoreCase(QUIT)) {

            return PROCESS_FAILURE;

        } else if (input.equalsIgnoreCase(RESTOCK)) {

            initializeMachine();
            return PROCESS_SUCCESS;

        } else {

            Integer drinkNumber = Integer.parseInt(input);

            if(getDrink(drinkNumber)) {

                System.out.println(DISPENSING +
                                            menuNumberResolver(drinkNumber));
                return PROCESS_SUCCESS;

            } else {
                System.out.println(OUT_OF_STOCK +
                                            menuNumberResolver(drinkNumber));
                return PROCESS_SUCCESS;
            }
        }
    }

    private static String menuNumberResolver(Integer drinkNumber)
    {
        for (Drink drink : drinkList.keySet()) {
            if (drinkNumber.equals(drink.getMenuNumber())) {
                return drink.getDrinkName();
            }
        }
        return DRINK_NOT_FOUND;
    }

    private static boolean getDrink(Integer drinkNumber)
    {
        for (Drink drink : drinkList.keySet()) {
            if (isPossibleToMake(drink, drinkNumber)) {
                makeDrink(drink);
                return PROCESS_SUCCESS;
            }
        }
        return PROCESS_FAILURE;
    }

    private static boolean isPossibleToMake(Drink drink, Integer drinkNumber)
    {
        return drinkNumber.equals(drink.getMenuNumber()) &&
        getDrinkListValue(drink, DRINK_LIST_AVAILABILITY) == CAN_MAKE_DRINK &&
        hasInventoryFor(drink);
    }

    private static boolean hasInventoryFor(Drink drink)
    {
        for (String ingredient: drink.getRecipe().keySet()) {
            if (getInventoryValue(ingredient, INVENTORY_AMOUNT) <
                                        drink.getRecipe().get(ingredient)) {
                return false;
            }
        }
        return true;
    }

    private static void makeDrink(Drink drink)
    {
        HashMap<String, Double> recipe = drink.getRecipe();

        for (String ingredient : recipe.keySet()) {
            updateInventoryEntry(ingredient, recipe.get(ingredient));
        }
        updateDrinkList(drink);
    }

    private static void updateInventoryEntry(String ingredient, Double amtChange)
    {
        Double newAmount =
                    getInventoryValue(ingredient, INVENTORY_AMOUNT) - amtChange;

        Double[] updatedValues =
                    {newAmount, getInventoryValue(ingredient, INVENTORY_PRICE)};

        inventory.put(ingredient, updatedValues);
    }

    private static void updateDrinkList(Drink drink)
    {
        Double[] drinkPriceAndIsAvailable = getDrinkPriceAndAvail(drink);

        drinkList.put(drink, drinkPriceAndIsAvailable);
    }

    private static Double[] getDrinkPriceAndAvail(Drink drink)
    {
        Double price     = getDrinkInformation(drink, CALCULATE_PRICE);
        Double available = getDrinkInformation(drink, CALCULATE_IS_AVAILABLE);

        Double[] priceAndAvailability = { price, available };

        return priceAndAvailability;
    }


    private static Double getDrinkInformation(Drink drink, int info)
    {
        if (info == CALCULATE_PRICE) {
            Double totalPrice = ZERO;

            for (String ingredient : drink.getRecipe().keySet()) {

                Double unitPrice =
                                getInventoryValue(ingredient, INVENTORY_PRICE);
                Double amount    =
                                drink.getRecipe().get(ingredient);

                totalPrice += unitPrice * amount;
            }
            return totalPrice;

        } else if (info == CALCULATE_IS_AVAILABLE) {
            for (String ingredient : drink.getRecipe().keySet()) {

                Double amountOnHand =
                                getInventoryValue(ingredient, INVENTORY_AMOUNT);
                Double amountNeeded =
                                drink.getRecipe().get(ingredient);

                if (amountOnHand < amountNeeded) {
                    return CAN_NOT_MAKE_DRINK;
                }
            }
            return CAN_MAKE_DRINK;
        } else {
            return DRINK_INFORMATION_ERROR;
        }
    }
}
