package com.palmer.sample;

import java.util.TreeMap;
import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
import static com.palmer.sample.BaristaConstants.*;

/*
 * Baristamatic: a solution to the Barista Problem.
 *
 * Author: Michael Palmer
 *
 * 7/20/2015
 *
 */
public class Baristamatic {

    /*
     * There are two TreeMaps in this class that contain the information
     * we are updating on each iteration.
     * The inventory has a String key representing the ingredient
     * (milk, coffee, foam, etc.), whose value is the inital amount
     * and the price per unit.
     *
     * The drinkList holds a Drink key whose value is the current price
     * (based on the unit cost of items in the inventory) and the availability.
     *
     * TreMaps are used here for both the unique keys (put() works as update()),
     * and the natural ordering of comparable objects (see: Drink class);
     *
     * Ref:
     * inventory entry {String ingredient: Double[initialAmount, pricePerUnit]}
     * drinkList entry {Drink drink: Double[price, availability]}
     *
     */
    protected static TreeMap<String, Double[]> inventory = new TreeMap<>();
    protected static TreeMap<BaristaDrink, Double[]> drinkList = new TreeMap<>();

    private static Scanner scanner = new Scanner(System.in);

    /*
     * The main method is a simple loop, only used to display and prompt for
     * input from the user.
     */
    public static void main(String[] args)
    {
        initializeMachine();
        do
        {
            displayMenu();

        } while (isAbleToProcessInput());
    }


    protected static void initializeMachine()
    {
          initializeInventory();
          initailizeDrinkList();
    }


    /*
     * The initial values of the inventory are setup here, though this could
     * be extened to read from a file. All amounts and prices can be adjusted
     * in the BaristaConstants class.
     */
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


    /*
     * A drink factory is created, and fills the drinkList with items
     * listed in the DRINK_LIST of the BaristaConstants class
     */
    private static void initailizeDrinkList()
    {
        BaristaDrinkFactory drinkFactory = new BaristaDrinkFactory();

        for (String drinkName : DRINK_LIST) {
            BaristaDrink drink = drinkFactory.makeDrink(drinkName);
            if (drink.getSuccessOrFail()) {
                Double[] drinkPriceAndIsAvailable =
                                                getDrinkPriceAndAvail(drink);
                drinkList.put(drink, drinkPriceAndIsAvailable);
            } else {
                System.out.println(INITIALIZATION_ERROR);
            }
        }
    }


    /*
     * displayMenu() uses Constant String values that can be changed in the
     * BaristaConstants class.
     */
    private static void displayMenu()
    {
        System.out.println(INVENTORY);

        for (String key: inventory.keySet()) {
            System.out.printf(INVENTORY_LISTING, key,
                        getInventoryValue(key, INVENTORY_AMOUNT).intValue());
        }

        System.out.println(MENU);

        for (BaristaDrink drink : drinkList.keySet()) {
            System.out.printf(MENU_LISTING, drink.getMenuNumber(),
                                            drink.getDrinkName(),
                                            getDrinkListValue(drink,
                                                        DRINK_LIST_PRICE),
                                            getDrinkListValue(drink,
                                                        DRINK_LIST_AVAILABILITY)
                                                            == CAN_MAKE_DRINK);
        }
    }


    /*
     * getInventoryValue() simplifies the indexing of the inventory
     * using highly readable constant values.
     */
    protected static Double getInventoryValue(String key, int value)
    {
        return inventory.get(key)[value];
    }


    /*
     * getDrinkListValue() simplifies the indexing of the drinkList
     * using highly readable constant values.
     */
    protected static Double getDrinkListValue(BaristaDrink drink, int value)
    {
        return drinkList.get(drink)[value];
    }


    /*
     * isAbleToProcessInput() is an input method that passes
     * it's value to isValidInput() for validation, then returns
     * a success or failure (boolean) depending on those results.
     *
     * NOTE: the only way to break out of the MAIN method is to enter
     * the QUIT keyword ("q" or "Q"). Invalid enteries will still return
     * a success, providing a message and prompting for input again.
     */
    private static boolean isAbleToProcessInput()
    {
        String input = scanner.nextLine().toLowerCase().trim();

        if (isValidInput(input)) {
            boolean resultOfProcessing = processInput(input);
            return resultOfProcessing;
        } else {
            System.out.printf(INVALID_SELECTION, input);
            return PROCESS_SUCCESS;
        }
    }


    /*
     * isValidInput() first checks the keyword list (see BaristaConstants class)
     * then attempts to parse the input as an Integer. If successful, the
     * value is then checked to be in the MENU_COUNT range. If an exception is
     * thrown (number can not be parsed), a failure code is returned, prompting
     * the user to re-enter a value.
     */
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


    /*
     * If the input is valid, procesInput() will first look for KEYWORDS
     * and then attempt to make the drink request by menuNumber.
     */
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

                System.out.printf(DISPENSING, menuNumberResolver(drinkNumber));
                return PROCESS_SUCCESS;

            } else {
                System.out.printf(OUT_OF_STOCK, menuNumberResolver(drinkNumber));
                return PROCESS_SUCCESS;
            }
        }
    }


    /*
     * menuNumberResolver() will find a drink given a menu number
     */
    private static String menuNumberResolver(Integer drinkNumber)
    {
        for (BaristaDrink drink : drinkList.keySet()) {
            if (drinkNumber.equals(drink.getMenuNumber())) {
                return drink.getDrinkName();
            }
        }
        return DRINK_NOT_FOUND;
    }


    /*
     * getDrink() first scans the drinkList for the requested drinkNumber,
     * then checks if that drink is possible (see isPossibleToMake()). If so,
     * it then makes the drink.
     */
    private static boolean getDrink(Integer drinkNumber)
    {
        for (BaristaDrink drink : drinkList.keySet()) {
            if (isPossibleToMake(drink, drinkNumber)) {
                makeDrink(drink);
                return PROCESS_SUCCESS;
            }
        }
        return PROCESS_FAILURE;
    }


    /*
     * Convinience method to remove the three-part logic from getDrink() - uses
     * short circuit evaluation to check three conditions
     */
    private static boolean isPossibleToMake(BaristaDrink drink, Integer drinkNumber)
    {
        return
        drinkNumber.equals(drink.getMenuNumber())
        &&
        getDrinkListValue(drink, DRINK_LIST_AVAILABILITY) == CAN_MAKE_DRINK
        &&
        hasInventoryFor(drink);
    }


    /*
     * hasInventoryFor() the inventory to check stock for ingredients
     * according to the drink recipe.
     */
    private static boolean hasInventoryFor(BaristaDrink drink)
    {
        for (String ingredient: drink.getRecipe().keySet()) {
            if (getInventoryValue(ingredient, INVENTORY_AMOUNT) <
                                        drink.getRecipe().get(ingredient)) {
                return false;
            }
        }
        return true;
    }


    /*
     * makeDrink() will first update the inventory according to the number
     * and type of ingredients used, then will update the drinkList with the
     * new drink availability according to the new inventory.
     */
    private static void makeDrink(BaristaDrink drink)
    {
        HashMap<String, Double> recipe = drink.getRecipe();

        for (String ingredient : recipe.keySet()) {
            updateInventoryEntry(ingredient, recipe.get(ingredient));
        }
        updateDrinkList(drink);
    }


    /*
     * updateInventoryEntry() will reduce the amount of the inventory item
     * by the amount passed in - this will normally be the amount in the
     * drink recipe.
     *
     * NOTE: because we are using a Map (TreeMap), all keys must be unique.
     * Updating is simplified to simply "put"-ing a value already contained,
     * as it will only change what already exists, not add a new entry.
     */
    private static void updateInventoryEntry(String ingredient, Double amtChange)
    {
        Double newAmount =
                    getInventoryValue(ingredient, INVENTORY_AMOUNT) - amtChange;

        Double[] updatedValues =
                    {newAmount, getInventoryValue(ingredient, INVENTORY_PRICE)};

        inventory.put(ingredient, updatedValues);
    }


    /*
     * updateDrinkList() will refresh a drink on the drinkList - this is useful
     * when the inventory is updated, as when a drink is made.
     *
     * NOTE: because we are using a Map (TreeMap), all keys must be unique.
     * Updating is simplified to simply "put"-ing a value already contained,
     * as it will only change what already exists, not add a new entry.
     */
    private static void updateDrinkList(BaristaDrink drink)
    {
        Double[] drinkPriceAndIsAvailable = getDrinkPriceAndAvail(drink);

        drinkList.put(drink, drinkPriceAndIsAvailable);
    }


    /*
     * This method will recalculate the price and availability of a drink.
     * If the price of individual units in the inventory does not change,
     * the price of the drink will not change, however the price value is
     * being refreshed here nonetheless as a matter of completeness to account
     * for the application extension involving dynamically changing inventory
     * prices (supply and demand).
     */
    private static Double[] getDrinkPriceAndAvail(BaristaDrink drink)
    {
        Double price     = getDrinkInformation(drink, CALCULATE_PRICE);
        Double available = getDrinkInformation(drink, CALCULATE_IS_AVAILABLE);

        Double[] priceAndAvailability = { price, available };

        return priceAndAvailability;
    }


    /*
     * This method will calculate information about the drink given the
     * current state of the inventory.
     */
    protected static Double getDrinkInformation(BaristaDrink drink, int info)
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