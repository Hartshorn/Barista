package Drinks;

import java.util.HashMap;
import static Constants.BaristaConstants.*;

public class Cappuccino implements Drink, Comparable<Drink> {

    private HashMap<String, Double> ingredients = new HashMap<>();
    private String drinkName = CAPPUCCINO;
    private Integer menuNumber = CAPPUCCINO_MENU_NUMBER;

    public Cappuccino()
    {
        ingredients.put(ESPRESSO, AMOUNT_TWO);
        ingredients.put(STEAMED_MILK,  AMOUNT_ONE);
        ingredients.put(FOAMED_MILK,  AMOUNT_ONE);
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
