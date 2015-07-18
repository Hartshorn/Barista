package Barista.src;

import java.util.TreeMap;
import java.util.HashMap;
import static Barista.src.BaristaConstants.*;

public final class BaristaMachineUtil {
    
    /*
     *  Do not allow creatiion of the Util class; use as static.
     */
    private BaristaMachineUtil() 
    {
        throw new AssertionError();
    }
    
    public static void initializeInventory(TreeMap<String, Double[]> inventory)
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
    
    
    public static void initailizeItemList(TreeMap<Drink,  Double[]> drinkList, 
                                          TreeMap<String, Double[]> inventory)
    {
      Drink[] drinks = createDrinkList();
      
      for (Drink drink : drinks) {
          updateDrinkList(drink, drinkList, inventory);
      }
    }
    
    public static void updateDrinkList(Drink drink, 
                                       TreeMap<Drink,  Double[]> drinkList, 
                                       TreeMap<String, Double[]> inventory)
    {
        drinkList.put(drink, getDrinkPriceAndAvail(drink.getIngredients(), 
                                                     drinkList, 
                                                     inventory));
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
    
    
    public static Double[] getDrinkPriceAndAvail(HashMap<String, Double> ingredients, 
                                                 TreeMap<Drink,  Double[]> drinkList, 
                                                 TreeMap<String, Double[]> inventory)
    {
        Double[] priceAndAvailability = { calculateDrinkPrice(ingredients, inventory), 
                                          isAbleToMakeDrink(ingredients,  inventory) };
        
        return priceAndAvailability;
    }
    
    
    private static Double calculateDrinkPrice(HashMap<String, Double> ingredients,
                                        TreeMap<String, Double[]> inventory)
    {
        Double totalPrice = ZERO;
        
        for (String key : ingredients.keySet()) {
        
            totalPrice += (getInventoryPrice(key, inventory) * ingredients.get(key));
        }
        return totalPrice;
    }
    
    
    private static Double isAbleToMakeDrink(HashMap<String, Double> ingredients, 
                                       TreeMap<String, Double[]> inventory)
    {
        Double hasEnough = CAN_MAKE_DRINK;
        
        for (String key : ingredients.keySet()) {
            if (getInventoryAmount(key, inventory) < ingredients.get(key)) {
                hasEnough = CAN_NOT_MAKE_DRINK;
            }
        }
        return hasEnough;
    }
    
    
    public static Double getInventoryAmount(String key, 
                                            TreeMap<String, Double[]> inventory)
    {
        return inventory.get(key)[0];
    }
    
    
    public static Double getInventoryPrice(String key, 
                                           TreeMap<String, Double[]> inventory)
    {
        return inventory.get(key)[1];
    }
    
    
    public static void updateInventoryEntry(String key, 
                                            Double amtChange, 
                                            Double prcChange, 
                                            TreeMap<String, Double[]> inventory)
    {
        Double newAmount = getInventoryAmount(key, inventory) - amtChange;
        Double newPrice  = getInventoryPrice( key, inventory) + prcChange;
        
        Double[] updatedValues = { newAmount, newPrice };
        
        inventory.put(key, updatedValues);
    }
    
    
}