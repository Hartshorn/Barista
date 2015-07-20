package Drinks;

import java.util.HashMap;
import static Constants.BaristaConstants.*;

public class CaffeMocha implements Drink, Comparable<Drink> {

    private HashMap<String, Double> ingredients = new HashMap<>();
    private String drinkName = CAFFE_MOCHA;
    private Integer menuNumber = CAFFE_MOCHA_MENU_NUMBER;

    public CaffeMocha()
    {
        ingredients.put(ESPRESSO, AMOUNT_ONE);
        ingredients.put(COCOA,  AMOUNT_ONE);
        ingredients.put(STEAMED_MILK,  AMOUNT_ONE);
        ingredients.put(WHIPPED_CREAM, AMOUNT_ONE);
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
