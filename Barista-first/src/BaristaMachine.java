package Barista.src;

import java.util.TreeMap;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Collections;
import static Barista.src.BaristaConstants.*;

public class BaristaMachine implements Machine {

    /*
     *  TreeMap takes comparables, so it lists string entries alphabetical by
     *  by default. The classes that implement Drink implement Comparable
     *  for this reason.
     */
    private TreeMap<String, Double[]> inventory = new TreeMap<>();
    private TreeMap<Drink,  Double[]> drinkList = new TreeMap<>(Collections.reverseOrder());

    private Scanner scanner = new Scanner(System.in);


    public BaristaMachine()
    {
      BaristaMachineUtil.initializeInventory(inventory);
      BaristaMachineUtil.initailizeItemList(drinkList, inventory);
    }


    @Override
    public void displayMenu()
    {
        listInventory();
        listItemsAndAvailability();
    }


    @Override
    public void listInventory()
    {
        System.out.println("Inventory:\n");

        for (String key: inventory.keySet()) {
            System.out.printf("%s,%d\n\n", key,
                BaristaMachineUtil.getInventoryAmount(key, inventory).intValue());
        }
    }


    @Override
    public void listItemsAndAvailability()
    {
          System.out.println("Menu:\n");
          int menuCounter = 1;

          for (Drink drink : drinkList.keySet()) {

          System.out.printf("%d,%s,$%.2f,%b\n\n",
                              drink.getMenuNumber(),
                              drink.getDrinkName(),
                              drinkList.get(drink)[INDEX_OF_PRICE],
                              drinkList.get(drink)[INDEX_OF_AVAILABLITY] != CAN_NOT_MAKE_DRINK);
        }
    }


    public boolean getDrink(Integer selection)
    {
        for (Drink drink : drinkList.keySet()) {
            if (selection.equals(drink.getMenuNumber()) &&
                        drinkList.get(drink)[INDEX_OF_AVAILABLITY] != CAN_NOT_MAKE_DRINK) {

                makeDrink(drink);
                return PROCESS_SUCCESS;
            }
        }
        return PROCESS_FAILURE;
    }


    public void makeDrink(Drink drink)
    {
        HashMap<String, Double> ingredients = drink.getIngredients();

        for (String key : ingredients.keySet()) {
            BaristaMachineUtil.updateInventoryEntry(key,
                                                    ingredients.get(key),
                                                    NO_CHANGE,
                                                    inventory);
        }

        BaristaMachineUtil.updateDrinkList(drink, drinkList, inventory);
    }


    public boolean isAbleToProcessInput()
    {
        String input = scanner.nextLine().trim();

        if (BaristaMachineValidator.validateInput(input)) {

            return processInput(input);

        } else {

            System.out.println("Invalid selection: " + input);
            return PROCESS_SUCCESS;
        }
    }


    public boolean processInput(String input)
    {
        if (input.equalsIgnoreCase(QUIT)) {

            return PROCESS_FAILURE;

        } else if (input.equalsIgnoreCase(RESTOCK)) {

            BaristaMachineUtil.initializeInventory(inventory);
            return PROCESS_SUCCESS;

        } else if (input.equalsIgnoreCase(EGG)) {

            System.out.println(EGG_OUT);
            return PROCESS_SUCCESS;

        } else {

            Integer drinkNumber = Integer.parseInt(input);

            if(getDrink(drinkNumber)) {

                System.out.println("Dispensing: " + menuNumberResolver(drinkNumber));
                return PROCESS_SUCCESS;

            } else {

                System.out.println("Out of stock: " + menuNumberResolver(drinkNumber));
                return PROCESS_SUCCESS;
            }
        }
    }


    private String menuNumberResolver(Integer drinkNumber)
    {
        for (Drink drink : drinkList.keySet()) {
            if (drinkNumber.equals(drink.getMenuNumber())) {
                return drink.getDrinkName();
            }
        }
        return "Drink not found";
    }
}
