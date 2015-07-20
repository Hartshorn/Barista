package Barista;

import java.util.HashMap;
import java.util.Scanner;
import static Constants.BaristaConstants.*;
import Util.*;
import Validator.*;
import Drinks.*;
import Command.BaristaCommand;

public class BaristaMachine implements Machine {

    private Scanner scanner = new Scanner(System.in);
    public BaristaCommand command = new BaristaCommand();


    public BaristaMachine()
    {
      initializeMachine();
    }

    private void initializeMachine()
    {
          BaristaMachineUtil.initializeInventory(command);
          BaristaMachineUtil.initailizeDrinkList(command);
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

        for (String key: command.getInventory().keySet()) {
            System.out.printf("%s,%d\n\n",
                            key, command.getInventoryAmount(key).intValue());
        }
    }


    @Override
    public void listItemsAndAvailability()
    {
          System.out.println("Menu:\n");
          int menuCounter = 1;

          for (Drink drink : command.getDrinkList().keySet()) {

          System.out.printf("%d,%s,$%.2f,%b\n\n",
                              drink.getMenuNumber(),
                              drink.getDrinkName(),
                              command.getDrinkPrice(drink),
                              command.isDrinkAvailable(drink));
        }
    }


    public boolean isAbleToProcessInput()
    {
        String input = scanner.nextLine().toLowerCase().trim();

        if (BaristaMachineValidator.isValidInput(input)) {

            boolean resultOfProcessing = processInput(input);

            return resultOfProcessing;

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

            initializeMachine();
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

    public boolean getDrink(Integer drinkNumber)
    {
        for (Drink drink : command.getDrinkList().keySet()) {
            if (drinkNumber.equals(drink.getMenuNumber()) && command.isDrinkAvailable(drink)) {

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
            command.updateInventoryEntry(key, ingredients.get(key));
        }
        BaristaMachineUtil.updateDrinkList(drink, command);
    }


    private String menuNumberResolver(Integer drinkNumber)
    {
        for (Drink drink : command.getDrinkList().keySet()) {
            if (drinkNumber.equals(drink.getMenuNumber())) {
                return drink.getDrinkName();
            }
        }
        return DRINK_NOT_FOUND;
    }
}
