package Drinks;

import java.util.HashMap;
import static Constants.BaristaConstants.*;

public class CaffeAmericano implements Drink, Comparable<Drink> {

    private HashMap<String, Double> ingredients = new HashMap<>();
    private String drinkName = CAFFE_AMERICANO;
    private Integer menuNumber = CAFFE_AMERICANO_MENU_NUMBER;

    public CaffeAmericano()
    {
        ingredients.put(ESPRESSO, AMOUNT_THREE);
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
