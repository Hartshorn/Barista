package Barista.src;

import java.util.HashMap;
import static Barista.src.BaristaConstants.*;

public class CaffeLatte implements Drink, Comparable<Drink> {
    
    private HashMap<String, Double> ingredients = new HashMap<>();
    private String drinkName = CAFFE_LATTE;
    private Integer menuNumber = CAFFE_LATTE_MENU_NUMBER;
    
    public CaffeLatte()
    {
        ingredients.put(ESPRESSO, AMOUNT_TWO);
        ingredients.put(STEAMED_MILK,  AMOUNT_ONE);
        
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