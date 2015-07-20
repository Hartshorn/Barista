package Util;

import java.util.TreeMap;
import java.util.HashMap;
import Drinks.*;
import static Constants.BaristaConstants.*;
import Command.BaristaCommand;

public final class BaristaMachineUtil {

    /*
     *  Do not allow creation of the Util class; use as static.
     */
    private BaristaMachineUtil()
    {
        throw new AssertionError();
    }

    public static void initializeInventory(BaristaCommand command)
    {
        command.getInventory().put(COFFEE, COFFEE_INIT_AMT_PRC);
        command.getInventory().put(DECAF_COFFEE, DECAF_COFFEE_INIT_AMT_PRC);
        command.getInventory().put(SUGAR, SUGAR_INIT_AMT_PRC);
        command.getInventory().put(CREAM, CREAM_INIT_AMT_PRC);
        command.getInventory().put(STEAMED_MILK, STEAMED_MILK_INIT_AMT_PRC);
        command.getInventory().put(ESPRESSO, ESPRESSO_INIT_AMT_PRC);
        command.getInventory().put(FOAMED_MILK, FOAMED_MILK_INIT_AMT_PRC);
        command.getInventory().put(COCOA, COCOA_INIT_AMT_PRC);
        command.getInventory().put(WHIPPED_CREAM, WHIPPED_CREAM_INIT_AMT_PRC);
    }


    public static void initailizeDrinkList(BaristaCommand command)
    {
      Drink[] drinks = createDrinkList();

      for (Drink drink : drinks) {
          updateDrinkList(drink, command);
      }
    }

    public static void updateDrinkList(Drink drink, BaristaCommand command)
    {
        Double[] drinkPriceAndIsAvailable = getDrinkPriceAndAvail(drink, command);

        command.getDrinkList().put(drink, drinkPriceAndIsAvailable);
    }


    private static Drink[] createDrinkList()
    {
        Coffee coffee             = new Coffee();
        DecafCoffee decafCoffee   = new DecafCoffee();
        CaffeLatte caffeLatte     = new CaffeLatte();
        CaffeAmericano caffeAm    = new CaffeAmericano();
        CaffeMocha caffeMocha     = new CaffeMocha();
        Cappuccino cappucino      = new Cappuccino();

        Drink[] allDrinks = { coffee, decafCoffee, caffeLatte,
                              caffeAm, caffeMocha, cappucino };

        return allDrinks;
    }


    private static Double[] getDrinkPriceAndAvail(Drink drink, BaristaCommand command)
    {
        Double price        = calculateDrinkPrice(drink, command);
        Double availability = isAbleToMakeDrink(drink, command);

        Double[] priceAndAvailability = { price, availability };

        return priceAndAvailability;
    }


    private static Double calculateDrinkPrice(Drink drink, BaristaCommand command)
    {
        Double totalPrice = ZERO;

        for (String ingredient : drink.getIngredients().keySet()) {

          Double unitPrice = command.getInventoryPrice(ingredient);
          Double amount    = drink.getIngredients().get(ingredient);

          totalPrice += unitPrice * amount;
        }
        return totalPrice;
    }


    private static Double isAbleToMakeDrink(Drink drink, BaristaCommand command)
    {
        for (String ingredient : drink.getIngredients().keySet()) {

            Double amountOnHand = command.getInventoryAmount(ingredient);
            Double amountNeeded = drink.getIngredients().get(ingredient);

            if (amountOnHand < amountNeeded) {
                return CAN_NOT_MAKE_DRINK;
            }
        }
        return CAN_MAKE_DRINK;
    }
}
