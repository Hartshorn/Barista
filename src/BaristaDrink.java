package com.palmer.sample;

import java.util.HashMap;

/*
 * BaristaDrink implements Drink, Comparable<BaristaDrink>
 *
 * Author: Michael Palmer
 * 
 * 7/20/2015
 *
 * The Drink class represents a model for a item that can be produced
 * by the Baristamatic. It contains a drinkName, a menuNumber, and a 
 * recipe describing the ingredients and amounts needed. There is also
 * a flag successOrFail for use as a failsafe during creation
 * 
 * NOTE: the Drink class does not store price information - this is determined
 * at runtime dynamically, and depends on the unit cost of each ingredient
 * (which can be adjusted)
 */
public class BaristaDrink implements Drink, Comparable<BaristaDrink> {

    // recipe entry: {String ingredient: Double amountCalledFor}
    private HashMap<String, Double> recipe = new HashMap<>();
    private String drinkName;
    private Integer menuNumber;
    private boolean successOrFail;

    @Override
    public Integer getMenuNumber()
    {
        return this.menuNumber;
    }
    
    @Override
    public void setMenuNumber(Integer menuNumber)
    {
        this.menuNumber = menuNumber;
    }

    @Override
    public String getDrinkName()
    {
        return this.drinkName;
    }

    @Override
    public void setDrinkName(String drinkName)
    {
        this.drinkName = drinkName;
    }

    @Override
    public HashMap<String, Double> getRecipe()
    {
        return this.recipe;
    }

    @Override
    public void setRecipe(HashMap<String, Double> ingredients)
    {
        this.recipe = recipe;
    }

    @Override
    public boolean getSuccessOrFail()
    {
        return this.successOrFail;
    }

    @Override
    public void setSuccessOrFail(Boolean bool)
    {
        this.successOrFail = bool;
    }

    /*
     * By implementing Comparable, as well as overriding this method,
     * using this class in a TreeMap structure will allow ording by name.
     */
    @Override
    public int compareTo(BaristaDrink drink) {
        return this.drinkName.compareTo(drink.getDrinkName());
    }
}
