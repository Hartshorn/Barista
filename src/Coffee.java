package Barista.src;

import java.util.HashMap;
import static Barista.src.BaristaConstants.*;

public class Coffee implements Drink, Comparable<Drink> {
    
    private HashMap<String, Double> ingredients = new HashMap<>();
    private String drinkName = COFFEE;
    private Integer menuNumber = COFFEE_MENU_NUMBER;
    
    public Coffee()
    {
        ingredients.put(COFFEE, AMOUNT_THREE);
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
        return this.drinkName;
    }
    
    @Override
    public HashMap<String, Double> getIngredients()
    {
        return this.ingredients;
    }
    
    @Override
    public int compareTo(Drink drink) {
        return -(this.drinkName.compareTo(drink.getDrinkName()));
    }
}