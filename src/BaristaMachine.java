package Barista.src;

import java.util.TreeMap;
import java.util.HashMap;

public class BaristaMachine implements Machine {

    private TreeMap<String, Integer> inventory = new TreeMap<>();
    private HashMap<String, Double> ingredientsAndPrices = new HashMap<>(9);
    private final Integer INIT_VALUE = 10;
    private final Double SEVENTYFIVE = 0.75;
    private final Double TWENTYFIVE = 0.25;
    private final Double THIRTYFIVE = 0.35;
    private final Double ONETEN = 1.10;
    private final Double NINETY = 0.90;
    private final Double ONEDOLLAR = 1.00;

    public void initializeIngredientsAndPrices()
    {
      ingredientsAndPrices.put("Coffee", SEVENTYFIVE);
      ingredientsAndPrices.put("Decaf Coffee", SEVENTYFIVE);
      ingredientsAndPrices.put("Sugar", TWENTYFIVE);
      ingredientsAndPrices.put("Cream", TWENTYFIVE);
      ingredientsAndPrices.put("Steamed Milk", THIRTYFIVE);
      ingredientsAndPrices.put("Espresso", THIRTYFIVE);
      ingredientsAndPrices.put("Faomed Milk", ONETEN);
      ingredientsAndPrices.put("Cocoa", NINETY);
      ingredientsAndPrices.put("Whipped Cream", ONEDOLLAR);
    }

    @Override
    public void listInventory()
    {
      for (String key: inventory.keySet()) {
        System.out.println(key + ": " + inventory.get(key));
      }
    }

    @Override
    public void initializeInventory()
    {
      for (String ingredient : ingredientsAndPrices.keySet()) {
        inventory.add(ingredient, INIT_VALUE);
      }
    }

    @Override
    public void resetInventory()
    {
      for (String key: inventory.keySet()) {
        inventory.replace(key, INIT_VALUE);
      }
    }

    @Override
    public void decrementInventoryItemBy(String item, int howMany)
    {
      inventory.replace(item, inventory.get(item) - howMany);
    }
}
