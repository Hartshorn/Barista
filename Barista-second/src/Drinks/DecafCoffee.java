package Drinks;

import java.util.HashMap;
import static Constants.BaristaConstants.*;

public class DecafCoffee implements Drink, Comparable<Drink> {

    private HashMap<String, Double> ingredients = new HashMap<>();
    private String drinkName = DECAF_COFFEE;
    private Integer menuNumber = DECAF_COFFEE_MENU_NUMBER;

    public DecafCoffee()
    {
        ingredients.put(DECAF_COFFEE, AMOUNT_THREE);
        ingredients.put(SUGAR,  AMOUNT_ONE);
        ingredients.put(CREAM,  AMOUNT_ONE);
    }

    @Override
    public Integer getMenuNumber()
    {
        return this.menuNumber;
    }

    @Override
    public String getDrinkName()
    {
        return drinkName;
    }

    @Override
    public HashMap<String, Double> getIngredients()
    {
        return ingredients;
    }

    @Override
    public int compareTo(Drink drink) {
        return drink.getDrinkName().compareTo(this.drinkName);
    }
}
