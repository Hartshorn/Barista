package Command;

import java.util.TreeMap;
import java.util.HashMap;
import Drinks.Drink;
import static Constants.BaristaConstants.*;

public class BaristaCommand {

    private TreeMap<String, Double[]> inventory = new TreeMap<>();
    private TreeMap<Drink,  Double[]> drinkList = new TreeMap<>();

    public TreeMap<String, Double[]> getInventory()
    {
      return this.inventory;
    }

    public void setInventory(TreeMap<String, Double[]> inventory)
    {
      this.inventory = inventory;
    }

    public TreeMap<Drink, Double[]> getDrinkList()
    {
      return this.drinkList;
    }

    public void setDrinkList(TreeMap<Drink, Double[]> drinkList)
    {
      this.drinkList = drinkList;
    }

    public Double getInventoryAmount(String key)
    {
        return inventory.get(key)[0];
    }

    public Double getInventoryPrice(String key)
    {
        return inventory.get(key)[1];
    }

    public void updateInventoryEntry(String key, Double amtChange)
    {
        Double newAmount = getInventoryAmount(key) - amtChange;

        Double[] updatedValues = { newAmount, getInventoryPrice(key) };

        inventory.put(key, updatedValues);
    }

    public Double getDrinkPrice(Drink drink)
    {
        return drinkList.get(drink)[PRICE];
    }

    public boolean isDrinkAvailable(Drink drink)
    {
        return drinkList.get(drink)[IS_AVAILABLE] == CAN_MAKE_DRINK;
    }
}
